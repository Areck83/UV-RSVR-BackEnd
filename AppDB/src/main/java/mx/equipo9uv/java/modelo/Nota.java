package mx.equipo9uv.java.modelo;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Nota {
    String id;
    String nombre;
    String matricula;
    String accion;
    String fechahora;
    String salon;
    String nota;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getFechahora() {
        return fechahora;
    }

    public void setFechahora(String fechahora) {
        this.fechahora = fechahora;
    }

    public String getSalon() {
        return salon;
    }

    public void setSalon(String salon) {
        this.salon = salon;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    //Constructor que pone la hora solito
    public Nota(String id, String nombre, String matricula, String accion, String salon,
            String nota) {
        this.id = id;
        this.nombre = nombre;
        this.matricula = matricula;
        this.accion = accion;
        this.fechahora = generarHora();
        this.salon = salon;
        this.nota = nota;
    }

    //Constructor que toma la hora para crear el objeto del DAO
    public Nota(String id, String nombre, String matricula, String accion, String fechahora, String salon,
            String nota) {
        this.id = id;
        this.nombre = nombre;
        this.matricula = matricula;
        this.accion = accion;
        this.fechahora = fechahora;
        this.salon = salon;
        this.nota = nota;
    }

    public String generarHora(){ //Este metodo en realidad trae la fecha y la hora
        LocalDateTime horaActual = LocalDateTime.now();
        DateTimeFormatter horaFormateada = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String horaTransformada = horaActual.format(horaFormateada);
        return horaTransformada;
    }
}
