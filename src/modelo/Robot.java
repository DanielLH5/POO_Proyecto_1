package modelo;

import modelo.RegistroTarea;

import java.util.ArrayList;
import java.util.List;

public class Robot {
    //Atributos
    private String idProcesador;
    private int nivelBateria;
    private List<TareaRobot> tareasAsignadas;
    private List<RegistroTarea> historialTareas;

    //Constructor
    public Robot(String idProcesador, int nivelBateria) {
        this.idProcesador = idProcesador;
        this.nivelBateria = nivelBateria;
        this.tareasAsignadas = new ArrayList<>();
        this.historialTareas = new ArrayList<>();
        //asignarTareasAleatorias();
    }

    //Getters y Setters
    public String getIdProcesador() {
        return idProcesador;
    }

    public void setIdProcesador(String idProcesador) {
        this.idProcesador = idProcesador;
    }

    public int getNivelBateria() {
        return nivelBateria;
    }

    public void setNivelBateria(int nivelBateria) {
        this.nivelBateria = nivelBateria;
    }

    /*
    realizarTarea(TareaRobot tarea){}
    Realiza una tarea si tiene suficiente batería y está asignada al robot
    Reduce la batería según el consumo de la tarea
    Registra la tarea en el historial con fecha/hora
    Retorna boolean indicando si la tarea fue exitosa
    */

    /*
    recargarBateria(int cantidad){}
    Aumenta el nivel de batería en la cantidad especificada
    No puede exceder el 100%
    */

    /*
    recargarCompleto(){}
    Recarga la batería al 100%
    */

    /*
    estaEnAlerta(int umbralMinimo){}
    Verifica si el robot está en estado de alerta (batería < umbral mínimo)
    Retorna boolean - esencial para dashboard de energía
    */

    /*
    puedeRealizarTarea(TareaRobot tarea){}
    Verifica si el robot puede realizar una tarea específica
    (tiene la tarea asignada y batería suficiente)
    Retorna boolean
    */

    /*
    getConsumoTotalDiario(LocalDate fecha){}
    Calcula el consumo total de energía en una fecha específica
    Suma el consumo de todas las tareas realizadas ese día
    */

    /*
    getTareasRealizadasHoy(){}
    Retorna lista de tareas realizadas en el día actual
    Útil para estadísticas diarias
    */

    /*
    getUltimaTareaRealizada(){}
    Retorna la última tarea realizada por el robot
    Útil para seguimiento de actividad
    */

    /*
    getTareasDisponibles(){}
    Retorna lista de tareas que el robot puede realizar actualmente
    (tiene asignadas y con batería suficiente)
    */

    /*
    getTareasAsignadas(){}
    Retorna la lista de tareas asignadas al robot (inmodificable)
    */

    /*
    getHistorialTareas(){}
    Retorna el historial completo de tareas realizadas (inmodificable)
    */

    /*
    asignarTareasAleatorias(){}
    Asigna un conjunto aleatorio de tareas al robot (no modificable después)
    Se ejecuta automáticamente en el constructor
    */

    //toString
    @Override
    public String toString() {
        return "Robot{" +
                "idProcesador='" + idProcesador + '\'' +
                ", nivelBateria=" + nivelBateria +
                ", tareasAsignadas=" + tareasAsignadas +
                ", historialTareas=" + historialTareas +
                '}';
    }
}