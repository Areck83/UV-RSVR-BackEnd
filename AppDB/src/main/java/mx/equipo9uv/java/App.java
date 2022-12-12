package mx.equipo9uv.java;
import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import spark.*;
import spark.template.velocity.VelocityTemplateEngine;

import com.google.gson.*;
import java.util.UUID;

import mx.equipo9uv.java.modelo.*;

public class App {
    private static Gson gson = new Gson();
    public static void main( String[] args ){
        port(80);
        //La siguiente fraccion de codigo previene la aparicion del error del CORS
        options("/*", (request, response) -> {

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }
            return "OK";
        });
        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));
        //port(obtenerPuertoConexion()); //Se supone que se debe de conectar al puerto del ambiente.

        post("/registrarNota", (req, res) -> {
            String json = req.body();
            Nota nota = gson.fromJson(json, Nota.class);
            NotaDAO notadao = new NotaDAO();
            JsonObject respuestas = new JsonObject();
            respuestas.addProperty("status", notadao.crearNota(nota));
            return respuestas;
        });

        post("/verNotas", (req, res) -> {
            before((req2, res2) -> res.type("application/json"));
            NotaDAO notadao = new NotaDAO();
            return gson.toJson(notadao.traerNotas()); 
        });

        //Usar Velocity Finalmente
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new VelocityTemplateEngine().render(new ModelAndView(model, "vista/index.html"));
        });

        get("/registrarNota", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new VelocityTemplateEngine().render(new ModelAndView(model, "vista/nota.html"));
        });

        get("/verNotas", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new VelocityTemplateEngine().render(new ModelAndView(model, "vista/salones.html"));
        });
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
