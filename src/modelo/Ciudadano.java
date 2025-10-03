package modelo;

import java.util.ArrayList;
import java.util.List;

public class Ciudadano {
    //Atributos
    private String id;
    private String nombre;
    private Edificio edificio;
    private List<Robot> robotsAsistentes;

    //Constructor
    public Ciudadano(String id, String nombre, Edificio edificio) {
        this.id = id;
        this.nombre = nombre;
        this.edificio = edificio;
        this.robotsAsistentes = new ArrayList<>();
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

    public Edificio getEdificio() {
        return edificio;
    }

    public void setEdificio(Edificio edificio) {
        this.edificio = edificio;
    }

    public List<Robot> getRobotsAsistentes() {
        return new ArrayList<>(robotsAsistentes); // Devuelve una copia para evitar modificación externa
    }

    //Metodo para agregar un robot asistente
    public void agregarRobotAsistente(Robot robot) {
        this.robotsAsistentes.add(robot);
    }

    //Metodo para eliminar un robot asistente
    public boolean eliminarRobotAsistente(Robot robot) {
        return this.robotsAsistentes.remove(robot);
    }

    /*
    tieneRobots(){}
    Verifica si el ciudadano tiene al menos un robot asignado
    Retorna boolean - útil para KPIs de cobertura
    */

    /*
    getCantidadRobots(){}
    Retorna la cantidad total de robots que tiene el ciudadano
    */

    /*
    tieneRobotsEnAlerta(int umbralMinimo){}
    Verifica si algún robot del ciudadano está en estado de alerta (batería < umbral)
    Retorna boolean - útil para dashboard de energía
    */

    /*
    getRobotsEnAlerta(int umbralMinimo){}
    Retorna lista de robots del ciudadano que están en estado de alerta
    */

    /*
    solicitarTarea(Robot robot, TareaRobot tarea){}
    Solicita a un robot específico que realice una tarea
    Verifica que el robot pertenezca al ciudadano y tenga batería suficiente
    Retorna boolean indicando si la tarea fue ejecutada
    */

    /*
    getRobotsDisponiblesParaTarea(TareaRobot tarea){}
    Retorna lista de robots del ciudadano que pueden realizar una tarea específica
    (tienen la tarea asignada y batería suficiente)
    */

    /*
    calcularConsumoTotalDiario(){}
    Calcula el consumo total de energía de todos los robots del ciudadano en un día
    Útil para análisis de consumo energético
    */

    /*
    getRobotConMayorBateria(){}
    Retorna el robot del ciudadano con mayor nivel de batería
    Útil para priorizar tareas críticas
    */

    /*
    getRobotConMenorBateria(){}
    Retorna el robot del ciudadano con menor nivel de batería
    Útil para priorizar recargas
    */

    /*
    necesitaRecargaUrgente(int umbralCritico){}
    Verifica si algún robot del ciudadano está por debajo de un umbral crítico
    Retorna boolean - para alertas del sistema
    */

    //toString
    @Override
    public String toString() {
        return "Ciudadano{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", edificio=" + edificio +
                ", robotsAsistentes=" + robotsAsistentes +
                '}';
    }
}
