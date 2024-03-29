package next.web;

import core.db.DataBase;
import next.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/user/updateForm")
public class UpdateUserFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        User user = DataBase.findUserById(userId);

        HttpSession session = req.getSession();
        Object value = session.getAttribute("user");

        if(value != null) {
            User reqUser = (User) value;

            if(user == reqUser) {
                req.setAttribute("user", user);
                RequestDispatcher rd = req.getRequestDispatcher("/user/update.jsp");
                rd.forward(req, resp);
            }
        }
        resp.sendRedirect("/user/list");
    }
}
