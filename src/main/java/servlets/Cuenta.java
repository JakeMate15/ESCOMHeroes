/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author erikm
 */
public class Cuenta extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{
        response.setContentType("text/html;charset=UTF-8");
        Connection conexion = null;
        PreparedStatement consulta = null;
        ResultSet tabla = null;
        
        try{
            try (PrintWriter out = response.getWriter()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/escomheroes","root","root");
                //String usuario = request.getParameter("usuario");
                //String contra = request.getParameter("contrasenia");
                //String op = request.getParameter("opcion");
                
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset=\"UTF-8\">");
                out.println("<title>ESCOMHeroes</title>");
                out.println("<link rel=\"stylesheet\" href=\"estilos/estilosES.css\">");
                out.println("</head>");
                out.println("<body>");
                out.println("<header>");
                out.println("<h1>ESCOMHeroes</h1>");
                out.println("<nav>");
                out.println("<ul>");
                out.println("<li><a href=\"ESCOMHeroes.html\">Inicio</a></li>");
                out.println("<li><a href=\"Registro\">Registro/Eliminar Cuenta</a></li>");
                out.println("<li><a href=\"Cuenta\">Mi Cuenta</a></li>");
                out.println("<li><a href=\"Ranking\">Ranking</a></li>");
                out.println("</ul>");
                out.println("</nav>");
                out.println("</header>");
                out.println("<main>");
                out.println("<section>");
                out.println("<h1>Cambiar Información de Usuario</h1><br>");
                
                //out.println("Usario: " + (usuario) + "<br>");
                //out.println("Contraseña: " + (contra) + "<br>");
                //out.println("Op: " + op + "<br>");
                
                //out.println(": " + () + "<br>");
                
                out.println("<form action=\"\\Cuenta\" method=\"POST\">");
                out.println("<label for=\"usuario\">Usuario:</label>");
                out.println("<input type=\"text\" id=\"usuario\" name=\"usuario\" required><br>");

                out.println("<label for=\"contrasenia\">Contraseña:</label>");
                out.println("<input type=\"password\" id=\"contrasenia\" name=\"contrasenia\" required><br>");

                out.println("<label for=\"opcion\">Cambiar:</label>");
                out.println("<select id=\"opcion\" name=\"opcion\" required>");
                out.println("<option value=\"nombre\">Nombre</option>");
                out.println("<option value=\"apellido\">Apellido</option>");
                out.println("<option value=\"usuario\">Nombre de usuario</option>");
                out.println("<option value=\"contrasenia\">Contraseña</option>");
                out.println("</select><br>");

                out.println("<label for=\"valor\">Nuevo Valor:</label>");
                out.println("<input type=\"text\" id=\"valor\" name=\"valor\" required><br>");

                out.println("<input type=\"submit\" value=\"Guardar Cambios\">");
                out.println("</form>");

                
                /*
                if(nombreR!=null && apellidoR!=null && usuarioR!=null && contraR!=null){
                    //Iny sql
                    String consultaSql = "SELECT * FROM jugadores WHERE nombre_usuario=?";
                    consulta = conexion.prepareStatement(consultaSql);
                    consulta.setString(1, usuarioR);
                    
                    if(consulta.execute()){
                        tabla = consulta.executeQuery();
                        if(!tabla.next()){
                            consultaSql = "INSERT INTO jugadores (nombre, apellido, nombre_usuario, contrasenia, nivel, dinero) VALUES (?, ?, ?, ?, ?, ?);";
                            consulta = conexion.prepareStatement(consultaSql);
                            consulta.setString(1, nombreR);
                            consulta.setString(2, apellidoR);
                            consulta.setString(3, usuarioR);
                            consulta.setString(4, contraR);
                            consulta.setString(5, "1");
                            consulta.setString(6, "0");

                            consulta.executeUpdate();
                            out.println("Usuario agregado correctamente");

                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            redireccion.forward(request, response);
                        }
                        else{
                            out.println("Usuario no disponible");
                        }
                    }
                    else{
                        out.println("Valores no validos");
                    }
                }
                */

                out.println("</section>");
                out.println("</main>");
                out.println("<footer>");
                out.println("<p>ESCOMHeroes &copy; 2023 - Todos los derechos reservados</p>");
                out.println("</footer>");
                out.println("</body>");
                out.println("</html>");

                
                conexion.close();
            }
        }catch (ClassNotFoundException | SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al obtener la lista de jugadores: " + e.getMessage());
        } finally {
            try {
                if (tabla != null) {
                    tabla.close();
                }
                if (consulta != null) {
                    consulta.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
        doGet(request, response);
    }

}
