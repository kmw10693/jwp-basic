package next.controller;

import core.annotation.Controller;
import org.reflections.Reflections;

import java.awt.font.LineMetrics;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ControllerScanner {

    private final Map<Class<?>, Object> classMap = new HashMap<>();
    private final Reflections reflections;
    public void init() throws InstantiationException, IllegalAccessException {
        Set<Class<?>> typesAnnotatedWith = reflections.getTypesAnnotatedWith(Controller.class);

        for (Class<?> aClass : typesAnnotatedWith) {
            Object o = aClass.newInstance();
            classMap.put(aClass, o);
        }
    }

    public ControllerScanner(Object... basePackage) {
        reflections = new Reflections(basePackage);
    }

    public ControllerScanner() {
        reflections = new Reflections();
    }

    public Map<Class<?>, Object> getControllers() throws InstantiationException, IllegalAccessException {
        init();
        return classMap;
    }
}
