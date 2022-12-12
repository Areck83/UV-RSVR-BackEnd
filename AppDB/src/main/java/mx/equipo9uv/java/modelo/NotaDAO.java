package mx.equipo9uv.java.modelo;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

import com.mysql.jdbc.PreparedStatement;
//import com.mysql.jdbc.Connection; Esta madre no quiere jalar el c.getConnection
import com.mysql.jdbc.Statement;

//su objetivo es independizar la logica del negocio
//
public class NotaDAO {
    //Conectar internamente con la base de datos
    private static Conexion c = new Conexion();

    public static List<Nota> traerNotas(){
        Statement consulta = null;
        ResultSet elementos = null;
        Connection cc = null;
        List<Nota> resultado = new ArrayList<>();

        cc = c.getConnection();

        try{
            String sql = "SELECT * FROM nota";
            consulta = (Statement) cc.createStatement();
            elementos = consulta.executeQuery(sql);
            while(elementos.next()){
                Nota n = new Nota(elementos.getString("id"), elementos.getString("nombre"), elementos.getString("matricula"), elementos.getString("accion"), elementos.getString("fechahora"), elementos.getString("salon"), elementos.getString("nota"));
                resultado.add(n);
            }
        }catch(Exception e){
            System.out.println(e);
        } finally {
            if (elementos != null) {
                try {
                    elementos.close();
                } catch (SQLException sqlEx) {
                    sqlEx.printStackTrace();
                }
                elementos = null;
            }

            if (consulta != null) {
                try {
                    consulta.close();
                } catch (SQLException sqlEx) {
                    sqlEx.printStackTrace();
                }
                consulta = null;
            }
            try {
                cc.close();
                System.out.println("Closed  connection!");
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }
        }

        return resultado;
    }

    public static String crearNota(Nota n) {
        PreparedStatement consulta = null;
        Connection cc = null;
        String msj = "";

        cc = c.getConnection();

        try {
            String sql = "insert into nota (id, nombre, matricula, accion, fechahora, salon, nota) values (?,?,?,?,?,?,?)";
            consulta = (PreparedStatement) cc.prepareStatement(sql);
            consulta.setString(1, n.getId());
            consulta.setString(2, n.getNombre());
            consulta.setString(3, n .getMatricula());
            consulta.setString(4, n.getAccion());
            consulta.setString(5, n.getFechahora());
            consulta.setString(6, n.getSalon());
            consulta.setString(7, n.getNota());

            if (consulta.executeUpdate() > 0)
                msj = "La nota se agrego";
            else
                msj = "La nota valio mauser";
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (consulta != null) {
                try {
                    consulta.close();
                } catch (SQLException sqlEx) {
                    sqlEx.printStackTrace();
                }
                consulta = null;
            }
            try {
                cc.close();
                System.out.println("Closed  connection!");
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }
        }
        return msj;
    }

    public static String modificarNota(Nota n){
        PreparedStatement consulta = null;
        Connection cc = null;
        String msj = "";

        cc = c.getConnection();

        try{
            String sql = "UPDATE nota set nombre = ?, matricula = ?, accion = ?, fechahora = ?, salon = ?, nota = ? where id= ?";
            consulta = (PreparedStatement) cc.prepareStatement(sql);
            consulta.setString(1, n.getNombre());
            consulta.setString(2, n.getMatricula());
            consulta.setString(3, n.getAccion());
            consulta.setString(4, n.getFechahora());
            consulta.setString(5, n.getSalon());
            consulta.setString(6, n.getNota());
            consulta.setString(7, n.getId());

            if (consulta.executeUpdate() > 0)
                msj = "La nota se modifico";
            else
                msj = "La nota no se modifico";
        } catch (Exception e) {
                System.out.println(e);
        }finally {
            if (consulta != null) {
                try {
                    consulta.close();
                } catch (SQLException sqlEx) {
                    sqlEx.printStackTrace();
                }
                consulta = null;
            }
            try {
                cc.close();
                System.out.println("Closed  connection!");
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }
        }
        return msj;
    }

    public static String eliminarNota(Nota n){
        PreparedStatement consulta = null;
        Connection cc = null;
        String msj = "";

        cc = c.getConnection();

        try{
            String sql = "DELETE FROM nota WHERE id= ?";
            consulta = (PreparedStatement) cc.prepareStatement(sql);
            consulta.setString(1, n.getId());

            if (consulta.executeUpdate() > 0)
                msj = "Se arrugo la nota y se tiro en la cesta";
            else
                msj = "Error: La nota no alcanzo el bote de basura";
        } catch (Exception e) {
                System.out.println(e);
        }finally {
            if (consulta != null) {
                try {
                    consulta.close();
                } catch (SQLException sqlEx) {
                    sqlEx.printStackTrace();
                }
                consulta = null;
            }
            try {
                cc.close();
                System.out.println("Closed  connection!");
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }
        }
        return msj;
    }
}
