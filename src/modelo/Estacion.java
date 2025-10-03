package modelo;

import java.util.ArrayList;
import java.util.List;

public class Estacion {
    //Atributos
    private String id;
    private String descripcion;
    private String ubicacion;
    private int capacidadMaxima;
    private EstadoEstacion estado;
    private List<Robot> robotsCargando;
    private List<Dron> dronesCargando;
    private List<RegistroRecarga> historialRecargas;

    //Constructor
    public Estacion(String id, String descripcion, String ubicacion, int capacidadMaxima, EstadoEstacion estado) {
        this.id = id;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.capacidadMaxima = capacidadMaxima;
        this.estado = estado;
        this.robotsCargando = new ArrayList<>();
        this.dronesCargando = new ArrayList<>();
        this.historialRecargas = new ArrayList<>();
    }

    //Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public EstadoEstacion getEstado() {
        return estado;
    }

    public void setEstado(EstadoEstacion estado) {
        this.estado = estado;
    }

    public List<Robot> getRobotsCargando() {
        return new ArrayList<>(robotsCargando);
    }

    public List<Dron> getDronesCargando() {
        return new ArrayList<>(dronesCargando);
    }

    public List<RegistroRecarga> getHistorialRecargas() {
        return new ArrayList<>(historialRecargas);
    }

    /*
    atenderRobot(Robot robot){}
    Atiende un robot para recarga
    Verifica que la estación esté disponible y tenga capacidad
    Agrega el robot a la lista de cargando
    Retorna boolean indicando si fue exitosa la operación
    */

    /*
    atenderDrone(Drone drone){}
    Atiende un drone para recarga
    Verifica que la estación esté disponible y tenga capacidad
    Agrega el drone a la lista de cargando
    Retorna boolean indicando si fue exitosa la operación
    */

    /*
    finalizarRecargaRobot(Robot robot){}
    Finaliza la recarga de un robot y lo remueve de la lista
    Registra la recarga en el historial con fecha/hora
    Retorna boolean indicando si fue exitosa la operación
    */

    /*
    finalizarRecargaDrone(Drone drone){}
    Finaliza la recarga de un drone y lo remueve de la lista
    Registra la recarga en el historial con fecha/hora
    Retorna boolean indicando si fue exitosa la operación
    */

    /*
    tieneCapacidadDisponible(){}
    Verifica si la estación tiene capacidad para atender más dispositivos
    Retorna boolean - (robotsCargando + dronesCargando) < capacidadMaxima
    */

    /*
    getCantidadDispositivosAtendiendo(){}
    Retorna la cantidad actual de dispositivos siendo atendidos
    Calcula: robotsCargando.size() + dronesCargando.size()
    */

    /*
    getPorcentajeOcupacion(){}
    Calcula el porcentaje de ocupación actual de la estación
    Retorna double (dispositivosAtendiendo / capacidadMaxima * 100)
    */

    /*
    estaDisponible(){}
    Verifica si la estación está disponible para uso
    Retorna boolean (estado == DISPONIBLE)
    */

    /*
    getTotalRecargasAtendidas(){}
    Retorna el total de recargas atendidas (tamaño del historial)
    Para estadísticas del dashboard
    */

    /*
    puedeAtenderRobot(Robot robot, int umbralMinimo){}
    Verifica si puede atender un robot específico
    (estado disponible, capacidad, y robot necesita recarga)
    */

    /*
    puedeAtenderDrone(Drone drone, int umbralMinimo){}
    Verifica si puede atender un drone específico
    (estado disponible, capacidad, y drone necesita recarga)
    */

    //toString
    @Override
    public String toString() {
        return "Estacion{" +
                "id='" + id + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", capacidadMaxima=" + capacidadMaxima +
                ", estado=" + estado +
                ", robotsCargando=" + robotsCargando.size() +
                ", dronesCargando=" + dronesCargando.size() +
                ", historialRecargas=" + historialRecargas.size() +
                '}';
    }
}