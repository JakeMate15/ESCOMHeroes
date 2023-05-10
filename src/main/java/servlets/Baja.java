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
public class Baja extends HttpServlet {
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
                String usuarioE = request.getParameter("usuario_eliminar");
                String contraE = request.getParameter("contrasena_eliminar");

                Usuario u = new Usuario();
                u.setUsuario(usuarioE);
                u.setContrasena(contraE);

                int bandera = gB.borrar(u);
                if(bandera>0){
                    response.sendRedirect("ESCOMHeroes.html");  
                }
                else{
                    out.println("Datos incorrectos");  
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println(e.toString());
                    }
                    response.sendRedirect("ESCOMHeroes.html");
                }
            }
    }

}
