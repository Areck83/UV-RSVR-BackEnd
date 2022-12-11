package mx.equipo9uv;

public class App {
    public static void main( String[] args ){
        port(obtenerPuertoConexion()); //Se supone que se debe de conectar al puerto del ambiente.
    }

    //Esta funcion obtiene un puerto del ambiente. Sirve si no se encuentra en la 
    static int obtenerPuertoConexion(){
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT")!=null){
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //Se supone que es el puerto default
    }
}
