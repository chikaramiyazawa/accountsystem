package controllers.drawer;

import java.io.IOException;

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
 * Servlet implementation class DrawerServlet
 */
@WebServlet("/drawer")
public class DrawerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DrawerServlet() {
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

            Credit c = em.find(Credit.class, (Integer)(request.getSession().getAttribute("id")));

            c.setNumbers((Account)request.getSession().getAttribute("login_accounts"));

            try{
            int cash = Integer.parseInt(request.getParameter("cash"));
            if(cash == 0){
                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("credits", c);
                request.getSession().setAttribute("errors", "引き出し金額が0円です。");
                em.close();

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/credits/deposit.jsp");
                rd.forward(request,response);

            }
            c.setCash(cash);
            int remainder = Integer.parseInt(request.getParameter("remainder"));
            if(remainder < cash){
                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("credits", c);
                request.getSession().setAttribute("errors", "残高が不足しているため引き出せません");
                em.close();

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/credits/deposit.jsp");
                rd.forward(request,response);
            }
            remainder = remainder - cash;
            c.setRemainder(remainder);

            } catch (NumberFormatException e) {
                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("credits", c);
                request.getSession().setAttribute("errors", "数字で入力してください");
                em.close();

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/credits/deposit.jsp");
                 rd.forward(request,response);

            }




        request.setAttribute("_token", request.getSession().getId());
        request.setAttribute("credits", c);



            em.getTransaction().begin();
            em.getTransaction().commit();

            request.getSession().setAttribute("flush", "更新が完了しました。");

            em.close();
            response.sendRedirect(request.getContextPath() + "/toppage");


        }
        }
}



