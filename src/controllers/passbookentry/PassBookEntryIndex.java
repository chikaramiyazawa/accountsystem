package controllers.passbookentry;

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
 * Servlet implementation class PassBookEntryIndex
 */
@WebServlet("/passbookentry")
public class PassBookEntryIndex extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PassBookEntryIndex() {
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
        int page;

        try{
            page = Integer.parseInt(request.getParameter("page"));
        }catch(Exception e){
            page = 1;
        }


        List<Credit>credits = em.createNamedQuery("getMyAccountPass" ,Credit.class)
                .setParameter("numbers" , login_accounts)
                .setFirstResult(15 * (page - 1))
                .setMaxResults(15)
                .getResultList();

        long numbers_count = (long)em.createNamedQuery("getMyAccountCount" , Long.class)
                .setParameter("numbers", login_accounts)
                .getSingleResult();
        em.close();





        request.setAttribute("credits", credits);
        request.setAttribute("numbers_count", numbers_count);


        if(request.getSession().getAttribute("flush") != null){
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/passbook/passbook_entry.jsp");

        rd.forward(request, response);
    }

}