package servlets;

import java.io.*;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "Ranking", urlPatterns = {"/Ranking"})
public class Ranking extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{
        response.setContentType("text/html;charset=UTF-8");
        Connection conexion = null;
        Statement consulta = null;
        ResultSet tabla = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/escomheroes","root","root");
            consulta = conexion.createStatement();
            tabla = consulta.executeQuery("SELECT * FROM jugadores ORDER BY nivel DESC");
            
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset=\"UTF-8\">");
                out.println("<title>ESCOMHeroes - Ranking de Jugadores</title>");
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
                out.println("<h2>Ranking de Jugadores</h2>");
                out.println("<table>");
                out.println("<thead>");
                out.println("<tr>");
                out.println("<th>Nombre de Usuario</th>");
                out.println("<th>Nivel</th>");
                out.println("<th>Dinero</th>");
                out.println("</tr>");
                out.println("</thead>");
                out.println("<tbody>");
                
                
                while (tabla.next()) {
                    out.println("<tr>");
                    out.println("<td>" + tabla.getString("nombre_usuario") + "</td>");
                    out.println("<td>" + tabla.getString("nivel") + "</td>");
                    out.println("<td>" + tabla.getString("dinero") + "</td>");
                    out.println("</tr>");
                }
                
                out.println("</tbody>");
                out.println("</table>");
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
