/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Eman
 */
public class RegServlet extends HttpServlet {

    HttpSession session;
    User user;
    public static ArrayList<User> arrUser = new ArrayList<>();
    //public static ArrayList<User> onlineUser = new ArrayList<>();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        user = new User();
        user.setUserName(request.getParameter("userName"));
        user.setfName(request.getParameter("fName"));
        user.setlName(request.getParameter("lName"));
        user.setPassword(request.getParameter("pass"));
        user.setEmail(request.getParameter("email"));
        System.out.println(request.getParameter("userName"));

        arrUser.add(user);
        response.setContentType("application/json");
        Gson gson = new Gson();
        response.getWriter().write(gson.toJson(arrUser.get(arrUser.size() - 1)));
        response.getWriter().close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        String userName = request.getParameter("name");
        String pass = request.getParameter("pass");
        System.out.println(userName);
        boolean flag = false;
        for (int i = 0; i < arrUser.size(); i++) {
            if (arrUser.get(i).getUserName().equals(userName)) {
                session = request.getSession(true);
                System.out.println("succsess");
                flag = true;
            }
        }
        if (flag) {
            HttpSession session = request.getSession(true);
            session.setAttribute("userName", userName);
            //onlineUser.add(user);
            session.setMaxInactiveInterval(30); // 30 seconds
            response.sendRedirect("index.html");

        }
        if (!flag) {
            System.out.println("fail");
            response.getWriter().write("faild");

        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
