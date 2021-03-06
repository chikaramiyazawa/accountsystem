package controllers.drawer;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Credit;
import utils.DBUtil;

/**
 * Servlet implementation class DrawerEditServlet
 */
@WebServlet("/drawer/edit")
public class DrawerEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DrawerEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        EntityManager em = DBUtil.createEntityManager();

        Credit c = em.find(Credit.class, Integer.parseInt(request.getParameter("id")));

        em.close();



            request.setAttribute("credits", c);
            request.setAttribute("_token", request.getSession().getId());
            request.getSession().setAttribute("id", c.getId());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/credits/drawer.jsp");
        rd.forward(request, response);
    }

}
