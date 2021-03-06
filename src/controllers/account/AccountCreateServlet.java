package controllers.account;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.validators.AccountValidator;
import models.Account;
import utils.DBUtil;

/**
 * Servlet implementation class AccountCreateServlet
 */
@WebServlet("/account/create")
public class AccountCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountCreateServlet() {
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

            Account a = new Account();

            a.setNumbers(request.getParameter("numbers"));
            a.setPassword(request.getParameter("password"));
            a.setAdmin_flag(Integer.parseInt(request.getParameter("admin_flag")));
            a.setPayment(0);

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            a.setCreated_at(currentTime);
            a.setUpdated_at(currentTime);


            List<String> errors = AccountValidator.validate(a, true, true);
            if(errors.size() >0){
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("accounts", a);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/accounts/new.jsp");
                rd.forward(request,response);
            }else{
                em.getTransaction().begin();
                em.persist(a);
                em.getTransaction().commit();
                request.getSession().setAttribute("flush", "登録が完了しました。");
                em.close();

                response.sendRedirect(request.getContextPath() + "/account/index");
            }
        }
    }

}
