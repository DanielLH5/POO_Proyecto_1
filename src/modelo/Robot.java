package modelo;

import modelo.RegistroTarea;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Robot {
    //Atributos
    private String idProcesador;
    private int nivelBateria;
    private List<TareaRobot> tareasAsignadas;
    private List<RegistroTarea> historialTareas;
    private boolean enAlerta;

    //Constructor
    public Robot(String idProcesador, int nivelBateria) {
        this.idProcesador = idProcesador;
        this.nivelBateria = nivelBateria;
        this.tareasAsignadas = new ArrayList<>();
        this.historialTareas = new ArrayList<>();
        this.enAlerta = false;
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
    public boolean realizarTarea(TareaRobot tarea) {
        // Verificar si el robot puede realizar la tarea
        if (!tareasAsignadas.contains(tarea)) {
            System.out.println("Robot " + idProcesador + " no puede realizar la tarea: " + tarea.getDescripcion());
            return false;
        }

        // Verificar batería suficiente
        if (nivelBateria < tarea.getConsumoEnergetico()) {
            System.out.println("Batería insuficiente. Se necesita " + tarea.getConsumoEnergetico() + "%, pero tiene " + nivelBateria + "%");
            return false;
        }

        // Realizar la tarea y consumir energía
        nivelBateria -= tarea.getConsumoEnergetico();

        // Registrar en el historial
        RegistroTarea registro = new RegistroTarea(tarea, LocalDateTime.now());
        historialTareas.add(registro);

        // Verificar estado de alerta después de consumir energía
        estaEnAlerta(15); // Usar umbral por defecto por ahora

        System.out.println("Tarea realizada: " + tarea.getDescripcion() +
                " - Consumo: " + tarea.getConsumoEnergetico() + "%" +
                " - Batería restante: " + nivelBateria + "%");

        return true;
    }

    /*
    recargarBateria(int cantidad){}
    Aumenta el nivel de batería en la cantidad especificada
    No puede exceder el 100%
    */
    public void recargarBateria(int cantidad){
        int nuevaBateria = nivelBateria + cantidad;
        if (nuevaBateria > 100) {
            nivelBateria = 100;
        } else {
            nivelBateria = nuevaBateria;
        }
        // Si se recargó por encima del umbral, quitar alerta
        if (nivelBateria >= 15) { // Umbral por defecto
            enAlerta = false;
        }
    }

    /*
    recargarCompleto(){}
    Recarga la batería al 100%
    */
    public void recargaCompleto(){
        nivelBateria = 100;
        enAlerta = false;
    }

    /*
    estaEnAlerta(int umbralMinimo){}
    Verifica si el robot está en estado de alerta (batería < umbral mínimo)
    Retorna boolean - esencial para dashboard de energía
    */
    public boolean estaEnAlerta(int umbralMinimo) {
        this.enAlerta = nivelBateria < umbralMinimo;
        return enAlerta;
    }

    /*
    puedeRealizarTarea(TareaRobot tarea){}
    Verifica si el robot puede realizar una tarea específica
    (tiene la tarea asignada y batería suficiente)
    Retorna boolean
    */
    public boolean puedeRealizarTarea(TareaRobot tarea) {
        return tareasAsignadas.contains(tarea) && nivelBateria >= tarea.getConsumoEnergetico();
    }

    /*
    getConsumoTotalDiario(LocalDate fecha){}
    Calcula el consumo total de energía en una fecha específica
    Suma el consumo de todas las tareas realizadas ese día
    */
    public int getConsumoTotalDiario(LocalDate fecha) {
        int consumoTotal = 0;

        for (RegistroTarea registro : historialTareas) {
            if (registro.getFechaHora().toLocalDate().equals(fecha)) {
                consumoTotal += registro.getTarea().getConsumoEnergetico();
            }
        }
        return consumoTotal;
    }

    /*
    getTareasRealizadasHoy(){}
    Retorna lista de tareas realizadas en el día actual
    Útil para estadísticas diarias
    */
    public List<RegistroTarea> getTareasRealizadasHoy() {
        List<RegistroTarea> tareasHoy = new ArrayList<>();
        LocalDateTime hoy = LocalDateTime.now();
        for (RegistroTarea registro : historialTareas) {
            if (registro.getFechaHora().toLocalDate().equals(hoy.toLocalDate())) {
                tareasHoy.add(registro);
            }
        }
        return tareasHoy;
    }

    /*
    getUltimaTareaRealizada(){}
    Retorna la última tarea realizada por el robot
    Útil para seguimiento de actividad
    */
    public RegistroTarea getUltimaTareaRealizada() {
        if (historialTareas.isEmpty()) {
            return null;
        }
        return historialTareas.getLast(); // también se puede usar get(historialTareas.size() - 1);
    }

    /*
    getTareasDisponibles(){}
    Retorna lista de tareas que el robot puede realizar actualmente
    (tiene asignadas y con batería suficiente)
    */
    public List<TareaRobot> getTareasDisponibles() {
        List<TareaRobot> disponibles = new ArrayList<>();
        for (TareaRobot tarea : tareasAsignadas) {
            if (nivelBateria >= tarea.getConsumoEnergetico()) {
                disponibles.add(tarea);
            }
        }
        return disponibles;
    }

    /*
    getTareasAsignadas(){}
    Retorna la lista de tareas asignadas al robot (inmodificable)
    */
    public List<TareaRobot> getTareasAsignadas() {
        return new ArrayList<>(tareasAsignadas); //Se para una copia mediante new
    }

    /*
    getHistorialTareas(){}
    Retorna el historial completo de tareas realizadas (inmodificable)
    */
    public List<RegistroTarea> getHistorialTareas() {
        return new ArrayList<>(historialTareas);
    }

    /*
    asignarTareasAleatorias(){}
    Asigna un conjunto aleatorio de tareas al robot (no modificable después)
    Se ejecuta automáticamente en el constructor
    */
    private void asignarTareasAleatorias() {
        TareaRobot[] todasTareas = TareaRobot.values();
        Random random = new Random();

        // Asignar al menos 2 tareas, máximo todas
        int cantidadTareas = 2 + random.nextInt(todasTareas.length - 1);

        for (int i = 0; i < cantidadTareas; i++) {
            TareaRobot tareaAleatoria = todasTareas[random.nextInt(todasTareas.length)];
            if (!tareasAsignadas.contains(tareaAleatoria)) {
                tareasAsignadas.add(tareaAleatoria);
            }
        }
    }

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