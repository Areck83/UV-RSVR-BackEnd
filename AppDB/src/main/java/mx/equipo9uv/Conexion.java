package mx.equipo9uv;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
// import com.mysql.jdbc.Connection;

public class Conexion {
    private static ProcessBuilder processBuilder = new ProcessBuilder();
    //Si es local el string lleva en la primera seccion "localhost", pero en serv externo debe cambiarse
    private static String url ="jdbc:mysql://127.0.0.1:3306/uvrsrvr";
    private static String driverName = "com.mysql.jdbc.Driver";
    //Estas variables del entorno donde se ejecute deben ser configuradas en la plataforma del despliegue
    private static String username = processBuilder.environment().get("USERDB");
    private static String password = processBuilder.environment().get("PASSDB");;
    //Variable de conexion
    private static Connection connection = null;

    public static Connection getConnection(){
        try {
            Class.forName(driverName);
            connection = (Connection) DriverManager.getConnection(url, username, password);
            System.out.println("Conexion exitosa con la base de datos");
        } catch (SQLException e){
            System.out.println("Error con la consulta" + e);
        } catch (ClassNotFoundException e) {
            System.out.println("Se ha generado un problema con el driver");
        }
        return connection;
    }
}
