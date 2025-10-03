package modelo;

import java.util.ArrayList;
import java.util.List;

public class Edificio {
    //Atributos
    private String id;
    private String nombre;
    private String ubicacion;
    private int capacidadMaxima;
    private List<Ciudadano> residentes;

    //Constructor
    public Edificio(String id, String nombre, String ubicacion, int capacidadMaxima) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.capacidadMaxima = capacidadMaxima;
        this.residentes = new ArrayList<Ciudadano>();
    }

    //Getters y Setters
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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public List<Ciudadano> getResidentes() {
        return residentes;
    }

    public void setResidentes(List<Ciudadano> residentes) {
        this.residentes = residentes;
    }

    //Métodos adicionales
    /*
    agregarResidente(Ciudadano ciudadano){}
    Verifica si hay capacidad disponible antes de agregar un residente al edificio
    Retorna boolean indicando si fue exitosa la operación
    */

    /*
    removerResidente(Ciudadano ciudadano){}
    Remueve un residente del edificio (para cuando se muden)
    Retorna boolean indicando si fue exitosa la operación
    */

    /*
    tieneCapacidadDisponible(){}
    Verifica si el edificio tiene capacidad para más residentes
    Retorna boolean true si capacidadActual < capacidadMaxima
    */

    /*
    getCantidadResidentes(){}
    Retorna la cantidad actual de residentes en el edificio
    */

    /*
    getPorcentajeOcupacion(){}
    Calcula el porcentaje de ocupación del edificio
    Retorna double con el porcentaje (residentes/capacidadMaxima * 100)
    */

    /*
    getCiudadanosConRobot(){}
    Retorna lista de ciudadanos que tienen al menos 1 robot asignado
    Útil para el dashboard
    */

    /*
    getCiudadanosSinRobot(){}
    Retorna lista de ciudadanos que no tienen robots asignados
    Útil para el dashboard
    */

    /*
    getTotalRobotsEnEdificio(){}
    Calcula el total de robots que tienen todos los residentes del edificio
    */

    /*
    getRobotsEnAlerta(int umbralMinimo){}
    Retorna lista de robots en el edificio que están por debajo del umbral de batería
    Útil para el dashboard de energía
    */

    /*
    getCantidadRobotsEnAlerta(int umbralMinimo){}
    Retorna la cantidad de robots en alerta en el edificio
    */

    /*
    getRelacionRobotsPorCiudadano(){}
    Calcula la relación robots/ciudadano en el edificio
    Retorna double (totalRobots / totalResidentes)
    */

    //toString
    @Override
    public String toString() {
        return "Edificio{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", capacidadMaxima=" + capacidadMaxima +
                ", residentes=" + residentes +
                '}';
    }
}
