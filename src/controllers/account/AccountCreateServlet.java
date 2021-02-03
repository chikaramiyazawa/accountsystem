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
import utils.EncryptUtil;

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
            a.setPassword(
                    EncryptUtil.getPasswordEncrypt(
                            request.getParameter("password"),
                            (String)this.getServletContext().getAttribute("pepper")
                            )
                    );
            a.setAdmin_flag(Integer.parseInt(request.getParameter("admin_flag")));
            try{
                int remainder = Integer.parseInt(request.getParameter("remainder"));
                if(remainder == 0 || remainder < 1000){
                    request.setAttribute("_token", request.getSession().getId());
                    request.setAttribute("accounts", a);
                    request.getSession().setAttribute("errors", "1000円からご入金ください");
                    em.close();

                    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/account/new.jsp");
                    rd.forward(request,response);

                }
                a.setRemainder(remainder);
            } catch (NumberFormatException e) {
                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("accounts", a);
                request.getSession().setAttribute("errors", "数字で入力してください");
                em.close();

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/account/new.jsp");
                rd.forward(request,response);

            }

            a.setRemainder(Integer.parseInt(request.getParameter("remainder")));
            a.setCash(0);
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            a.setCreated_at(currentTime);
            a.setUpdated_at(currentTime);


            List<String> errors = AccountValidator.validate(a, true, true);
            if(errors.size() >0){
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("accounts", a);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/account/new.jsp");
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
