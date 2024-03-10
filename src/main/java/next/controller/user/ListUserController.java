package next.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.Controller;
import next.controller.JspView;
import next.controller.ModelAndView;
import next.controller.UserSessionUtils;
import next.dao.UserDao;

public class ListUserController implements Controller {
    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if (!UserSessionUtils.isLogined(req.getSession())) {
            return new ModelAndView(new JspView("redirect:/users/loginForm"));
        }

        UserDao userDao = new UserDao();
        return new ModelAndView(new JspView("/user/list.jsp"), "users", userDao.findAll());
    }
}
