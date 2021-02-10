package controllers.drawer;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Account;
import models.Credit;
import utils.DBUtil;

/**
 * Servlet implementation class DrawerIndexServlet
 */
@WebServlet("/drawer/index")
public class DrawerIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DrawerIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        EntityManager em = DBUtil.createEntityManager();

        Account login_accounts =(Account)request.getSession().getAttribute("login_accounts");


        List<Credit>credits = em.createNamedQuery("getMyAccountCredit" ,Credit.class)
                .setParameter("numbers" , login_accounts)
                .getResultList();



        em.close();

        request.setAttribute("credits", credits);


        if(request.getSession().getAttribute("flush") != null){
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/credits/drawer_index.jsp");

        rd.forward(request, response);
    }

}
