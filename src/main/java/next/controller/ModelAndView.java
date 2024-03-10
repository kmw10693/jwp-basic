package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ModelAndView {
    private View view;

    private final Map<String, Object> modelView = new HashMap<>();

    public ModelAndView(View viewName, String modelName, Object model) {
        view = viewName;
        modelView.put(modelName, model);
    }

    public ModelAndView(View viewName) {
        view = viewName;
    }

    public ModelAndView(View viewName, String modelName, Object model, String modelName2, Object model2) {
        modelView.put(modelName, model);
        modelView.put(modelName2, model2);
        view = viewName;
    }


}
