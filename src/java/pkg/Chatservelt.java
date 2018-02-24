package pkg;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Eman
 */

import com.google.gson.Gson;
import com.sun.javafx.geom.Vec2d;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Vector;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sun.security.jgss.spi.MechanismFactory;

@WebServlet(name = "Chatservelt", urlPatterns = {"/Chatservelt"})

public class Chatservelt extends HttpServlet {

    String name, message;
    public static Vector<MessageDTO> vector = new Vector<>();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);
        if (session != null) {
            name = (String) session.getAttribute("userName");
            message = request.getParameter("myMessage");

            MessageDTO messageDTO = new MessageDTO();
            messageDTO.setName(name);
            messageDTO.setMessage(message);
            System.out.println(message);
            vector.add(messageDTO);
        } else {
            out.print("Please login first");
            request.getRequestDispatcher("Login.html").include(request, response);
        }
        out.close();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        Gson gson = new Gson();
        response.getWriter().write(gson.toJson(vector));
        response.getWriter().close();
    }

}
