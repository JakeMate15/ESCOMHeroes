/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auxiliares;

import java.sql.*;

/**
 *
 * @author erikm
 */
public class GestionBD {
    Connection con;
    ResultSet tabla;
    PreparedStatement consulta;
    
    public GestionBD(){
        con = null;
        tabla = null;
        consulta = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/escomheroes","root","root");
        }catch(ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
    }
    
    public int alta(Usuario u){
        int resultado = 0;
        try{
            String sqlCon = "SELECT * FROM jugadores WHERE usuario=?";
            consulta = con.prepareStatement(sqlCon);
            consulta.setString(1, u.getUsuario());
                    
            tabla = consulta.executeQuery();
            if(!tabla.next()){
                sqlCon = "INSERT INTO jugadores (nombre, apellido, usuario, correo, contrasena, nivel, dinero) VALUES (?, ?, ?, ?, ?, ?, ?);";

                consulta = con.prepareStatement(sqlCon);
                consulta.setString(1,u.getNombre());
                consulta.setString(2,u.getApellido());
                consulta.setString(3,u.getUsuario());
                consulta.setString(4,u.getCorreo());
                consulta.setString(5,u.getContrasena());
                consulta.setString(6,"1");
                consulta.setString(7,"0");
                resultado = consulta.executeUpdate();
            }  
        }catch(SQLException e){
            System.out.println(e);
        }
        return resultado;
    }
    
    public int actualizar(Usuario actual, String op, String valor){
        int resultado = 0;
        try{
            String sqlCon = "SELECT id_jugador FROM jugadores WHERE usuario=? AND contrasena=?";
            consulta = con.prepareStatement(sqlCon);
            consulta.setString(1, actual.getUsuario());
            consulta.setString(2, actual.getContrasena());
            
            tabla = consulta.executeQuery();
            if(tabla.next()){
                sqlCon = "UPDATE jugadores SET " + op + "=? WHERE id_jugador=?;";
                String id = tabla.getString("id_jugador");
                consulta = con.prepareStatement(sqlCon);
                consulta.setString(1,valor);
                consulta.setString(2,id);
                //System.out.println(consulta.toString());


                resultado = consulta.executeUpdate();
            }
              
        }catch(SQLException e){
            System.out.println(e);
        }
        return resultado;
    }
    
    public int borrar(Usuario u){
        int resultado = 0;
        try{
            String sqlCon = "SELECT id_jugador FROM jugadores WHERE usuario=? AND contrasena=?";
            consulta = con.prepareStatement(sqlCon);
            consulta.setString(1, u.getUsuario());
            consulta.setString(2, u.getContrasena());
            
            if(consulta.execute()){
                tabla = consulta.executeQuery();
                
                if(tabla.next()){
                    sqlCon = "DELETE FROM jugadores WHERE id_jugador=?;";
                    String id = tabla.getString("id_jugador");
                    consulta = con.prepareStatement(sqlCon );
                    consulta.setString(1,id);

                    resultado = consulta.executeUpdate();
                }
                    
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return resultado;
    }

    public ResultSet rank(){
        ResultSet tabla = null;
        try{
            String sqlCon = "SELECT * FROM jugadores ORDER BY nivel DESC";
            consulta = con.prepareStatement(sqlCon);
            tabla = consulta.executeQuery();
            con.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return tabla;
    }
    
    public void cierreCon(){
        try{
            con.close();
            tabla.close();
            consulta.close();
        }catch(SQLException e){
            System.out.println(e);
        }
    }
}
