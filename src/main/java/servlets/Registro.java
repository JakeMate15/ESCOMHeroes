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

/**
 *
 * @author erikm
 */
@WebServlet(name = "Registro", urlPatterns = {"/Registro"})
public class Registro extends HttpServlet {
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{
        response.setContentType("text/html;charset=UTF-8");
        Connection conexion = null;
        PreparedStatement consulta = null;
        ResultSet tabla = null;
        
        try{
            try (PrintWriter out = response.getWriter()) {
                String er = "^[a-zA-Z]+$";
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/escomheroes","root","root");
                String nombreR = request.getParameter("nombreRegistro");
                String apellidoR = request.getParameter("apellidoRegistro");
                String usuarioR = request.getParameter("usuarioRegistro");
                String contraR = request.getParameter("contraRegistro");
                
                
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
                out.println("<h1>Registro y eliminación de cuenta</h1><br>");
                out.println("Ingresa solo letras del alfabeto latin");
                out.println("<h2>Registro de usuario</h2>");
                
                /*
                out.println("Nombre: " + (nombreR) + "<br>");
                out.println("Apellido: " + (apellidoR) + "<br>");
                out.println("Usario: " + (usuarioR) + "<br>");
                out.println("Contraseña: " + (contraR) + "<br>");
                */
                
                //out.println(": " + () + "<br>");
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
                        }
                        else{
                            out.println("Usuario no disponible");
                        }
                    }
                    else{
                        out.println("Caracteres no validos");
                    }
                }
                
                out.println("<form>");
                out.println("<label for=\"nombre\">Nombre:</label>");
                out.println("<input type=\"text\" id=\"nombreRegistro\" name=\"nombreRegistro\" required><br>");
                out.println("<label for=\"apellido\">Apellido:</label>");
                out.println("<input type=\"text\" id=\"apellidoRegistro\" name=\"apellidoRegistro\" required><br>");
                out.println("<label for=\"usuario\">Usuario:</label>");
                out.println("<input type=\"text\" id=\"usuarioRegistro\" name=\"usuarioRegistro\" required><br>");
                out.println("<label for=\"contrasena\">Contraseña:</label>");
                out.println("<input type=\"text\" id=\"contraRegistro\" name=\"contraRegistro\" required><br>");
                out.println("<input type=\"submit\" value=\"Registrarse\">");
                out.println("</form>");
                out.println("<h2>Eliminación de usuario</h2>");
                out.println("<form>");
                out.println("<label for=\"usuario_eliminar\">Usuario:</label>");
                out.println("<input type=\"text\" id=\"usuario_eliminar\" name=\"usuario_eliminar\" required><br>");
                out.println("<label for=\"contrasena_eliminar\">Contraseña:</label>");
                out.println("<input type=\"password\" id=\"contrasena_eliminar\" name=\"contrasena_eliminar\" required><br>");
                out.println("<input type=\"submit\" value=\"Eliminar cuenta\">");
                out.println("</form>");
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
