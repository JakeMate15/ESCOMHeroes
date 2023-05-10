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
import java.sql.*;

/**
 *
 * @author erikm
 */
public class Ranking extends HttpServlet {
    Connection conexion;
    Statement consulta;
    ResultSet tabla;
    
    @Override
    public void init() 
        throws ServletException {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/escomheroes","root","root");
            consulta = conexion.createStatement();
            tabla = null;
        }catch(ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
    }
    
    @Override
    public void destroy(){ 
        try{
            conexion.close();
            consulta.close();
            tabla.close();
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{
            response.setContentType("text/html;charset=UTF-8");
            
            
            try {
                String sqlCon = "SELECT * FROM jugadores ORDER BY nivel DESC";
                tabla = consulta.executeQuery(sqlCon);
                
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
                    out.println("<li><a href=\"Registro.html\">Registro/Eliminar Cuenta</a></li>");
                    out.println("<li><a href=\"Cuenta.html\">Mi Cuenta</a></li>");
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
                        out.println("<td>" + tabla.getString("usuario") + "</td>");
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
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
    }

}
