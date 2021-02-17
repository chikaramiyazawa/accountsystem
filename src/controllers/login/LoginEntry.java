package controllers.login;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Account;
import utils.DBUtil;

/**
 * Servlet implementation class LoginEntry
 */
@WebServlet("/login/entry")
public class LoginEntry extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginEntry() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        request.setAttribute("_token", request.getSession().getId());
        request.setAttribute("hasError", false);
        if(request.getSession().getAttribute("flush") != null){
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/login/login_entry.jsp");
        rd.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        Boolean check_result = false;

        String numbers = request.getParameter("numbers");
        String password = request.getParameter("password");

        Account a = null;



        if(numbers != null && !numbers.equals("") && password != null && !password.equals("")){
            EntityManager em = DBUtil.createEntityManager();


            try{

                a = em.createNamedQuery("checkNumberAndPassword",Account.class)
                        .setParameter("numbers", numbers)
                        .setParameter("password", password)
                        .getSingleResult();


            }catch(NoResultException ex){}

            em.close();

            if(a != null){
                check_result = true;
            }

        }
        if(!check_result){
            request.setAttribute("_token", request.getSession().getId());
            request.setAttribute("hasError", true);
            request.setAttribute("numbers", numbers);


            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/login/login_entry.jsp");
            rd.forward(request, response);

    }else{
        request.getSession().setAttribute("login_accounts", a);

        request.getSession().setAttribute("flush","ログインしました。");
        response.sendRedirect(request.getContextPath() + "/passbookentry");

    }
    }
    }

