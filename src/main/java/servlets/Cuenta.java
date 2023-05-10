/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auxiliares.GestionBD;
import auxiliares.Usuario;

/**
 *
 * @author erikm
 */
public class Cuenta extends HttpServlet {
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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()){
            String usuario = request.getParameter("usuario");
            String contrasenia = request.getParameter("contrasenia");
            String opcion = request.getParameter("opcion");
            String valor = request.getParameter("valor");

            Usuario actual = new Usuario();
            actual.setUsuario(usuario);
            actual.setContrasena(contrasenia);
            
            /* 
            out.println("<br><p>"+usuario+"</p>");
            out.println("<br><p>"+contrasenia+"</p>");
            out.println("<br><p>"+opcion+"</p>");
            out.println("<br><p>"+valor+"</p>");
            */

            
            int bandera = gB.actualizar(actual, opcion, valor);
            if(bandera>0){
                out.print("<p>Se han actualizado los datos</p>");
                request.getRequestDispatcher("ESCOMHeroes.html").include(request, response);  
            }
            else{
                out.println("Datos incorrectos");  
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e.toString());
                }
                response.sendRedirect("Cuenta.html");
            }
        

        }                
    }


}
