/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 *
 * @author erikm
 */
@WebServlet(name = "pruebaConexion", urlPatterns = {"/pruebaConexion"})
public class pruebaConexion extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Connection conexion = null;
        Statement consulta = null;
        ResultSet tabla = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/escomheroes","root","root");
            consulta = conexion.createStatement();
            tabla = consulta.executeQuery("SELECT * FROM jugadores");
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Lista de jugadores</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Lista de jugadores</h1>");
                out.println("<table>");
                out.println("<tr>");
                out.println("<th>ID</th>");
                out.println("<th>Nombre</th>");
                out.println("<th>Apellido</th>");
                out.println("<th>Nombre de usuario</th>");
                out.println("<th>Puntos de experiencia</th>");
                out.println("<th>Nivel</th>");
                out.println("<th>Puntuación</th>");
                out.println("<th>Última fecha de inicio de sesión</th>");
                out.println("<th>Correo electrónico</th>");
                out.println("</tr>");
                while (tabla.next()) {
                    out.println("<tr>");
                    out.println("<td>" + tabla.getInt("id_jugador") + "</td>");
                    out.println("<td>" + tabla.getString("nombre") + "</td>");
                    out.println("<td>" + tabla.getString("apellido") + "</td>");
                    out.println("<td>" + tabla.getString("nombre_usuario") + "</td>");
                    out.println("<td>" + tabla.getInt("puntos_experiencia") + "</td>");
                    out.println("<td>" + tabla.getInt("nivel") + "</td>");
                    out.println("<td>" + tabla.getInt("puntuacion") + "</td>");
                    out.println("<td>" + tabla.getDate("ultima_fecha_inicio_sesion") + "</td>");
                    out.println("<td>" + tabla.getString("correo_electronico") + "</td>");
                    out.println("</tr>");
                }
                out.println("</table>");
                out.println("</body>");
                out.println("</html>");
            }
        } catch (ClassNotFoundException | SQLException e) {
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


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
