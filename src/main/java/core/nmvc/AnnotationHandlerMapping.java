package next.controller;

import core.annotation.RequestMapping;
import core.annotation.RequestMethod;
import core.nmvc.HandlerExecution;
import core.nmvc.HandlerKey;
import core.nmvc.HandlerMapping;
import org.reflections.ReflectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AnnotationHandlerMapping implements HandlerMapping {

    private final Map<HandlerKey, HandlerExecution> map = new HashMap<>();

    private Object[] basePackage;

    public void initialize() throws InstantiationException, IllegalAccessException {
        ControllerScanner controllerScanner = new ControllerScanner(basePackage);
        Map<Class<?>, Object> classMap = controllerScanner.getControllers();

        for (Class<?> clazz : classMap.keySet()) {
            Set<Method> allMethods = ReflectionUtils.getAllMethods(clazz, ReflectionUtils.withAnnotation(RequestMapping.class));
            for (Method allMethod : allMethods) {
                RequestMapping annotation = allMethod.getAnnotation(RequestMapping.class);
                map.put(createHandlerKey(annotation), new HandlerExecution(classMap.get(allMethod.getDeclaringClass()), allMethod));
            }
        }
    }

    private HandlerKey createHandlerKey(RequestMapping rm) {
        return new HandlerKey(rm.value(), rm.method());
    }

    public HandlerExecution getHandler(HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        RequestMethod rm = RequestMethod.valueOf(request.getMethod().toUpperCase());
        return map.get(new HandlerKey(requestUri, rm));
    }

    public AnnotationHandlerMapping(Object... basePackage) {
        this.basePackage = basePackage;
    }
}
