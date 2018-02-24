/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg;

import com.google.gson.Gson;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static pkg.RegServlet.onlineUser;

/**
 *
 * @author Eman
 */
public class OnlineUser extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        HttpSession session = request.getSession(false);
    
        String name = (String) session.getAttribute("userName");
        
        for (int i = 0; i < onlineUser.size(); i++) {
            System.out.println(onlineUser.get(i).getUserName());
            if (onlineUser.get(i).getUserName().equals(name)) {
                onlineUser.remove(i);
                session.removeAttribute(name);
            }
        }
        Gson gson = new Gson();
        System.out.println(onlineUser.size());
        response.getWriter().write(gson.toJson(onlineUser));
        response.getWriter().close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        Gson gson = new Gson();
        System.out.println(onlineUser.size());
        response.getWriter().write(gson.toJson(onlineUser));
        response.getWriter().close();
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
