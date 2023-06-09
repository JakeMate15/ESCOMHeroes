/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import auxiliares.*;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author erikm
 */

public class Registro extends HttpServlet {
    GestionBD gB;
    
    @Override
    public void init() 
        throws ServletException {
        gB = new GestionBD();
    }
    
    @Override
    public void destroy(){ 
        gB.cierreCon();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
            response.setContentType("text/html;charset=UTF-8");

            try (PrintWriter out = response.getWriter()) {
                String nombreR = request.getParameter("nombreRegistro");
                String apellidoR = request.getParameter("apellidoRegistro");
                String usuarioR = request.getParameter("usuarioRegistro");
                String contraR = request.getParameter("contraRegistro");
                String correoR = request.getParameter("correoRegistro");

                Usuario u = new Usuario();
                u.setNombre(nombreR);
                u.setApellido(apellidoR);
                u.setUsuario(usuarioR);
                u.setContrasena(contraR);
                u.setCorreo(correoR);

                int bandera = gB.alta(u);
                if(bandera>0){
                    out.print("<p>Usuario Registrado</p>");
                    request.getRequestDispatcher("ESCOMHeroes.html").include(request, response);  
                }
                else{
                    out.println("Pruebe con otro usuario");  
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println(e.toString());
                    }
                    response.sendRedirect("Registro.html");
                }
            }
    }
}