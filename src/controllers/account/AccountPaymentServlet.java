package controllers.account;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Account;
import utils.DBUtil;

/**
 * Servlet implementation class AccountPaymentServlet
 */
@WebServlet("/account/payment")
public class AccountPaymentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountPaymentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())){
            EntityManager em = DBUtil.createEntityManager();

            Account a = em.find(Account.class, (Integer)(request.getSession().getAttribute("id")));

            a.setNumbers(request.getParameter("numbers"));
            a.setPassword(request.getParameter("password"));

            a.setAdmin_flag(Integer.parseInt(request.getParameter("admin_flag")));
            a.setPayment(1);

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            a.setCreated_at(currentTime);
            a.setUpdated_at(currentTime);



                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("accounts", a);



                em.getTransaction().begin();
                em.getTransaction().commit();
                request.getSession().setAttribute("login_accounts", a);
                em.close();

                response.sendRedirect(request.getContextPath() + "/credit/new");
            }

        }
}
