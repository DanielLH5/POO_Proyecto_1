package modelo;

import java.time.LocalDateTime;
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
    public boolean atenderRobot(Robot robot) {
        if (estaDisponible() && tieneCapacidadDisponible()) {
            robotsCargando.add(robot);
            return true;
        }
        return false;
    }

    /*
    atenderDrone(Drone drone){}
    Atiende un drone para recarga
    Verifica que la estación esté disponible y tenga capacidad
    Agrega el drone a la lista de cargando
    Retorna boolean indicando si fue exitosa la operación
    */

    public boolean atenderDrone(Dron drone) {
        if (estaDisponible() && tieneCapacidadDisponible()) {
            dronesCargando.add(drone);
            return true;
        }
        return false;
    }

    /*
    finalizarRecargaRobot(Robot robot){}
    Finaliza la recarga de un robot y lo remueve de la lista
    Registra la recarga en el historial con fecha/hora
    Retorna boolean indicando si fue exitosa la operación
    */

    public boolean finalizarRecargaRobot(Robot robot) {
        if (robotsCargando.remove(robot)) {
            int i = 100 - (robot.getNivelBateria());
            RegistroRecarga registro = new RegistroRecarga(
                    robot.getIdProcesador(),                      // idDispositivo
                    "ROBOT",                                      // tipoDispositivo
                    LocalDateTime.now(),                          // fechaHora
                    robot.getNivelBateria(),                      // nivelBateriaInicial
                    100                                           // nivelBateriaFinal
            );
            robot.setNivelBateria(100);
            historialRecargas.add(registro);
            return true;
        }
        return false;
    }

    /*
    finalizarRecargaDrone(Drone drone){}
    Finaliza la recarga de un drone y lo remueve de la lista
    Registra la recarga en el historial con fecha/hora
    Retorna boolean indicando si fue exitosa la operación
    */

    public boolean finalizarRecargaDrone(Dron drone) {
        if (dronesCargando.remove(drone)) {
            RegistroRecarga registro = new RegistroRecarga(
                    drone.getIdProcesador(),            // idDispositivo
                    "DRONE",                            // tipoDispositivo
                    LocalDateTime.now(),                // fechaHora
                    drone.getNivelBateria(),            // nivelEnergiaInicial
                    100                                 // nivelEnergiaFinal
            );
            historialRecargas.add(registro);
            return true;
        }
        return false;
    }

    /*
    tieneCapacidadDisponible(){}
    Verifica si la estación tiene capacidad para atender más dispositivos
    Retorna boolean - (robotsCargando + dronesCargando) < capacidadMaxima
    */

    public boolean tieneCapacidadDisponible() {
        return getCantidadDispositivosAtendiendo() < capacidadMaxima;
    }

    /*
    getCantidadDispositivosAtendiendo(){}
    Retorna la cantidad actual de dispositivos siendo atendidos
    Calcula: robotsCargando.size() + dronesCargando.size()
    */

    public int getCantidadDispositivosAtendiendo() {
        return robotsCargando.size() + dronesCargando.size();
    }

    /*
    getPorcentajeOcupacion(){}
    Calcula el porcentaje de ocupación actual de la estación
    Retorna double (dispositivosAtendiendo / capacidadMaxima * 100)
    */

    public double getPorcentajeOcupacion() {
        if (capacidadMaxima == 0) return 0.0;
        return (getCantidadDispositivosAtendiendo() / (double) capacidadMaxima) * 100;
    }

    /*
    estaDisponible(){}
    Verifica si la estación está disponible para uso
    Retorna boolean (estado == DISPONIBLE)
    */

    public boolean estaDisponible() {
        return estado == EstadoEstacion.DISPONIBLE;
    }

    /*
    getTotalRecargasAtendidas(){}
    Retorna el total de recargas atendidas (tamaño del historial)
    Para estadísticas del dashboard
    */

    public int getTotalRecargasAtendidas() {
        return historialRecargas.size();
    }

    /*
    puedeAtenderRobot(Robot robot, int umbralMinimo){}
    Verifica si puede atender un robot específico
    (estado disponible, capacidad, y robot necesita recarga)
    */

    public boolean puedeAtenderRobot(Robot robot, int umbralMinimo) {
        return estaDisponible() &&
                tieneCapacidadDisponible() &&
                robot.getNivelBateria() < umbralMinimo;
    }

    /*
    puedeAtenderDrone(Drone drone, int umbralMinimo){}
    Verifica si puede atender un drone específico
    (estado disponible, capacidad, y drone necesita recarga)
    */

    public boolean puedeAtenderDrone(Dron drone, int umbralMinimo) {
        return estaDisponible() &&
                tieneCapacidadDisponible() &&
                drone.getNivelBateria() < umbralMinimo;
    }

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