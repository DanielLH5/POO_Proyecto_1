package logica;

import modelo.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GestorEstaciones {
    private ArrayList<Estacion> estaciones;

    public GestorEstaciones(){
        estaciones = new ArrayList<>();
    }

    public boolean crearEstaciones(String id, String nombre, String ubicacion, int capacidadAtencion, EstadoEstacion estado) {
        for (Estacion estacion : estaciones) {
            if (estacion.getId().equals(id)) {
                System.out.println("Error: Ya existe un edificio con ID: " + id);
                return false;
            }
        }

        Estacion nuevaEstacion = new Estacion(id, nombre, ubicacion, capacidadAtencion, estado);
        estaciones.add(nuevaEstacion);
        System.out.println("Estacion creada exitosamente: " + nombre);
        return true;
    }

    /*
    Crea múltiples estaciones de energía de forma automática
    param cantidad - número de estaciones a crear (debe estar entre 5 y 8)
    return boolean indicando si la creación fue exitosa
    */
    public boolean crearEstacionesEnBloque(int cantidad) {
        // Validar que la cantidad esté entre 5 y 8
        if (cantidad < 5 || cantidad > 8) {
            System.out.println("Error: La cantidad de estaciones debe estar entre 5 y 8");
            return false;
        }

        // Generar las estaciones automáticamente
        for (int i = 0; i < cantidad; i++) {
            // Generar ID único
            String id = "EST" + String.format("%03d", estaciones.size() + 1);
            // Generar nombre automático
            String nombre = "Estación de Energía " + (i + 1);
            // Generar ubicación automática
            String ubicacion = generarUbicacionAutomatica(i);
            // Generar capacidad aleatoria entre 5-10
            int capacidadAtencion = 5 + (int)(Math.random() * 6); // 5-10
            // Estado inicial: DISPONIBLE
            EstadoEstacion estado = EstadoEstacion.DISPONIBLE;
            // Verificar si el ID ya existe (aunque es poco probable con nuestro formato)
            boolean idExiste = false;
            for (Estacion estacion : estaciones) {
                if (estacion.getId().equals(id)) {
                    idExiste = true;
                    break;
                }
            }

            if (idExiste) {
                System.out.println("Error: Ya existe una estación con ID: " + id);
                return false;
            }

            // Crear y agregar la estación
            Estacion nuevaEstacion = new Estacion(id, nombre, ubicacion, capacidadAtencion, estado);
            estaciones.add(nuevaEstacion);
            System.out.println("Estación creada exitosamente: " + nombre + " (ID: " + id + ")");
        }

        System.out.println("Se crearon " + cantidad + " estaciones de energía exitosamente");
        return true;
    }

    /*
    Genera una ubicación automática basada en el índice
    param indice - índice de la estación
    return String con la ubicación generada
    */
    private String generarUbicacionAutomatica(int indice) {
        String[] calles = {"Principal", "Central", "Norte", "Sur", "Este", "Oeste"};
        String[] avenidas = {"Avenida A", "Avenida B", "Avenida C", "Avenida D"};

        String calle = calles[indice % calles.length];
        String avenida = avenidas[indice % avenidas.length];

        return calle + ", " + avenida;
    }

    /*
    buscarEstacion(String id){}
    Busca una estación por su ID único
    Retorna la Estacion encontrada o null si no existe
    */
    public Estacion buscarEstacion(String id) {
        for (Estacion estacion : estaciones) {
            if (estacion.getId().equals(id)) {
                return estacion;
            }
        }
        System.out.println("No se encontró estación con ID: " + id);
        return null;
    }

    /*
    actualizarEstadoEstacion(String idEstacion, EstadoEstacion nuevoEstado){}
    Actualiza el estado de una estación específica (disponible, en mal estado, en mantenimiento)
    Retorna boolean indicando si fue exitosa la actualización
    */
    public boolean actualizarEstadoEstacion(String idEstacion, EstadoEstacion nuevoEstado) {
        Estacion estacion = buscarEstacion(idEstacion);
        if (estacion == null) {
            System.out.println("Error: No se encontró estación con ID: " + idEstacion);
            return false;
        }

        estacion.setEstado(nuevoEstado);
        System.out.println("Estado de estación " + idEstacion + " actualizado a: " + nuevoEstado);
        return true;
    }

    /*
    obtenerEstacionesDisponibles(){}
    Retorna lista de estaciones que están disponibles para uso
    Útil para asignar dispositivos que necesitan recarga
    */
    public List<Estacion> obtenerEstacionesDisponibles() {
        List<Estacion> disponibles = new ArrayList<>();
        for (Estacion estacion : estaciones) {
            if (estacion.estaDisponible() && estacion.tieneCapacidadDisponible()) {
                disponibles.add(estacion);
            }
        }
        System.out.println("Estaciones disponibles: " + disponibles.size());
        return disponibles;
    }

    /*
    obtenerEstacionesPorEstado(EstadoEstacion estado){}
    Retorna lista de estaciones filtradas por estado específico
    Útil para reportes del dashboard
    */
    public List<Estacion> obtenerEstacionesPorEstado(EstadoEstacion estado) {
        List<Estacion> estacionesPorEstado = new ArrayList<>();
        for (Estacion estacion : estaciones) {
            if (estacion.getEstado() == estado) {
                estacionesPorEstado.add(estacion);
            }
        }
        System.out.println("Estaciones con estado " + estado + ": " + estacionesPorEstado.size());
        return estacionesPorEstado;
    }

    /*
    atenderRobot(String idEstacion, Robot robot){}
    Asigna un robot a una estación para recarga
    Verifica que la estación esté disponible y tenga capacidad
    Retorna boolean indicando si fue exitosa la operación
    */
    public boolean atenderRobot(String idEstacion, Robot robot) {
        Estacion estacion = buscarEstacion(idEstacion);
        if (estacion == null) {
            System.out.println("Error: No se encontró estación con ID: " + idEstacion);
            return false;
        }

        boolean exito = estacion.atenderRobot(robot);
        if (exito) {
            System.out.println("Robot " + robot.getIdProcesador() + " atendido en estación " + idEstacion);
        } else {
            System.out.println("No se pudo atender robot " + robot.getIdProcesador() + " en estación " + idEstacion);
        }
        return exito;
    }

    /*
    atenderDron(String idEstacion, Dron dron){}
    Asigna un dron a una estación para recarga
    Verifica que la estación esté disponible y tenga capacidad
    Retorna boolean indicando si fue exitosa la operación
    */
    public boolean atenderDron(String idEstacion, Dron dron) {
        Estacion estacion = buscarEstacion(idEstacion);
        if (estacion == null) {
            System.out.println("Error: No se encontró estación con ID: " + idEstacion);
            return false;
        }

        boolean exito = estacion.atenderDrone(dron);
        if (exito) {
            System.out.println("Dron " + dron.getIdProcesador() + " atendido en estación " + idEstacion);
        } else {
            System.out.println("No se pudo atender dron " + dron.getIdProcesador() + " en estación " + idEstacion);
        }
        return exito;
    }

    /*
    finalizarRecargaRobot(String idEstacion, String idRobot){}
    Finaliza la recarga de un robot en una estación específica
    Registra la recarga en el historial con fecha/hora y niveles de batería
    Retorna boolean indicando si fue exitosa la operación
    */
    public boolean finalizarRecargaRobot(String idEstacion, String idRobot) {
        Estacion estacion = buscarEstacion(idEstacion);
        if (estacion == null) {
            System.out.println("Error: No se encontró estación con ID: " + idEstacion);
            return false;
        }

        // Buscar el robot en la lista de robots cargando
        for (Robot robot : estacion.getRobotsCargando()) {
            if (robot.getIdProcesador().equals(idRobot)) {
                boolean exito = estacion.finalizarRecargaRobot(robot);
                if (exito) {
                    System.out.println("Recarga de robot " + idRobot + " finalizada en estación " + idEstacion);
                }
                return exito;
            }
        }

        System.out.println("Error: No se encontró robot " + idRobot + " en estación " + idEstacion);
        return false;
    }

    /*
    finalizarRecargaDron(String idEstacion, String idDron){}
    Finaliza la recarga de un dron en una estación específica
    Registra la recarga en el historial con fecha/hora y niveles de batería
    Retorna boolean indicando si fue exitosa la operación
    */
    public boolean finalizarRecargaDron(String idEstacion, String idDron) {
        Estacion estacion = buscarEstacion(idEstacion);
        if (estacion == null) {
            System.out.println("Error: No se encontró estación con ID: " + idEstacion);
            return false;
        }

        // Buscar el dron en la lista de drones cargando
        for (Dron dron : estacion.getDronesCargando()) {
            if (dron.getIdProcesador().equals(idDron)) {
                boolean exito = estacion.finalizarRecargaDrone(dron);
                if (exito) {
                    System.out.println("Recarga de dron " + idDron + " finalizada en estación " + idEstacion);
                }
                return exito;
            }
        }

        System.out.println("Error: No se encontró dron " + idDron + " en estación " + idEstacion);
        return false;
    }

    /*
    obtenerEstacionesConCapacidad(){}
    Retorna lista de estaciones que tienen capacidad disponible
    Útil para asignar nuevos dispositivos a recargar
    */
    public List<Estacion> obtenerEstacionesConCapacidad() {
        List<Estacion> conCapacidad = new ArrayList<>();
        for (Estacion estacion : estaciones) {
            if (estacion.tieneCapacidadDisponible()) {
                conCapacidad.add(estacion);
            }
        }
        System.out.println("Estaciones con capacidad disponible: " + conCapacidad.size());
        return conCapacidad;
    }

    /*
    calcularOcupacionPromedio(){}
    Calcula el porcentaje de ocupación promedio de todas las estaciones
    Retorna double con el promedio
    */
    public double calcularOcupacionPromedio() {
        if (estaciones.isEmpty()) {
            return 0.0;
        }

        double sumaOcupacion = 0.0;
        for (Estacion estacion : estaciones) {
            sumaOcupacion += estacion.getPorcentajeOcupacion();
        }

        double promedio = sumaOcupacion / estaciones.size();
        System.out.println("Ocupación promedio del sistema: " + String.format("%.2f", promedio) + "%");
        return promedio;
    }

    /*
    obtenerEstadisticasEstaciones(){}
    Retorna estadísticas detalladas de todas las estaciones
    Incluye: total estaciones, disponibles, en mantenimiento, ocupación promedio, etc.
    */
    public String obtenerEstadisticasEstaciones() {
        StringBuilder estadisticas = new StringBuilder();
        estadisticas.append("=== ESTADÍSTICAS DE ESTACIONES ===\n");
        estadisticas.append("Total estaciones: ").append(getTotalEstaciones()).append("\n");
        estadisticas.append("Estaciones disponibles: ").append(getCantidadEstacionesDisponibles()).append("\n");
        estadisticas.append("Estaciones en mantenimiento: ").append(getCantidadEstacionesEnMantenimiento()).append("\n");
        estadisticas.append("Estaciones en mal estado: ").append(getCantidadEstacionesEnMalEstado()).append("\n");
        estadisticas.append("Capacidad total del sistema: ").append(getCapacidadTotalSistema()).append("\n");
        estadisticas.append("Ocupación instantánea: ").append(String.format("%.2f", getOcupacionInstantaneaSistema())).append("%\n");
        estadisticas.append("Total recargas atendidas: ").append(obtenerTotalRecargasAtendidas()).append("\n");

        return estadisticas.toString();
    }

    /*
    obtenerEstacionesConAltaOcupacion(double porcentajeMinimo){}
    Retorna lista de estaciones con ocupación mayor o igual al porcentaje especificado
    Útil para identificar estaciones sobresaturadas
    */
    public List<Estacion> obtenerEstacionesConAltaOcupacion(double porcentajeMinimo) {
        List<Estacion> altaOcupacion = new ArrayList<>();
        for (Estacion estacion : estaciones) {
            if (estacion.getPorcentajeOcupacion() >= porcentajeMinimo) {
                altaOcupacion.add(estacion);
            }
        }
        System.out.println("Estaciones con ocupación ≥ " + porcentajeMinimo + "%: " + altaOcupacion.size());
        return altaOcupacion;
    }

    /*
    obtenerTotalRecargasAtendidas(){}
    Calcula el total de recargas atendidas por todas las estaciones
    Retorna int con la suma de todos los registros de recarga
    */
    public int obtenerTotalRecargasAtendidas() {
        int total = 0;
        for (Estacion estacion : estaciones) {
            total += estacion.getTotalRecargasAtendidas();
        }
        return total;
    }

    /*
    obtenerRecargasRecientes(int horas){}
    Retorna lista de recargas realizadas en las últimas N horas
    Para estadísticas de actividad reciente
    */
    public List<RegistroRecarga> obtenerRecargasRecientes(int horas) {
        List<RegistroRecarga> recargasRecientes = new ArrayList<>();
        LocalDateTime limite = LocalDateTime.now().minusHours(horas);

        for (Estacion estacion : estaciones) {
            for (RegistroRecarga registro : estacion.getHistorialRecargas()) {
                if (registro.getFechaHora().isAfter(limite)) {
                    recargasRecientes.add(registro);
                }
            }
        }

        System.out.println("Recargas en las últimas " + horas + " horas: " + recargasRecientes.size());
        return recargasRecientes;
    }

    /*
    redistribuirDispositivos(){}
    Redistribuye dispositivos entre estaciones para balancear la carga
    Mueve dispositivos de estaciones muy ocupadas a estaciones con capacidad
    Retorna la cantidad de dispositivos redistribuidos
    */
    public int redistribuirDispositivos() {
        int redistribuidos = 0;
        List<Estacion> estacionesConCapacidad = obtenerEstacionesConCapacidad();
        List<Estacion> estacionesSobrecargadas = obtenerEstacionesConAltaOcupacion(80.0);

        for (Estacion sobrecargada : estacionesSobrecargadas) {
            for (Estacion conCapacidad : estacionesConCapacidad) {
                if (sobrecargada != conCapacidad) {
                    // Redistribuir robots
                    for (Robot robot : new ArrayList<>(sobrecargada.getRobotsCargando())) {
                        if (conCapacidad.tieneCapacidadDisponible()) {
                            sobrecargada.finalizarRecargaRobot(robot);
                            conCapacidad.atenderRobot(robot);
                            redistribuidos++;
                            System.out.println("Robot redistribuido de " + sobrecargada.getId() + " a " + conCapacidad.getId());
                        }
                    }

                    // Redistribuir drones
                    for (Dron dron : new ArrayList<>(sobrecargada.getDronesCargando())) {
                        if (conCapacidad.tieneCapacidadDisponible()) {
                            sobrecargada.finalizarRecargaDrone(dron);
                            conCapacidad.atenderDrone(dron);
                            redistribuidos++;
                            System.out.println("Dron redistribuido de " + sobrecargada.getId() + " a " + conCapacidad.getId());
                        }
                    }
                }
            }
        }

        System.out.println("Total dispositivos redistribuidos: " + redistribuidos);
        return redistribuidos;
    }

    /*
    obtenerEstacionOptimaParaRecarga(){}
    Encuentra la estación más adecuada para asignar un nuevo dispositivo
    Considera: capacidad disponible, ubicación, tiempo de espera estimado
    Retorna la Estacion óptima o null si no hay disponible
    */
    public Estacion obtenerEstacionOptimaParaRecarga() {
        Estacion estacionOptima = null;
        double mejorPuntuacion = -1.0;

        for (Estacion estacion : estaciones) {
            if (estacion.estaDisponible() && estacion.tieneCapacidadDisponible()) {
                // Puntuación basada en capacidad disponible y porcentaje de ocupación
                double capacidadDisponible = estacion.getCapacidadMaxima() - estacion.getCantidadDispositivosAtendiendo();
                double porcentajeOcupacion = estacion.getPorcentajeOcupacion();

                // Fórmula: priorizar alta capacidad disponible y baja ocupación
                double puntuacion = capacidadDisponible * (100 - porcentajeOcupacion) / 100;

                if (puntuacion > mejorPuntuacion) {
                    mejorPuntuacion = puntuacion;
                    estacionOptima = estacion;
                }
            }
        }

        if (estacionOptima != null) {
            System.out.println("Estación óptima para recarga: " + estacionOptima.getId());
        } else {
            System.out.println("No hay estaciones óptimas disponibles");
        }

        return estacionOptima;
    }

    /*
    getCantidadEstacionesDisponibles(){}
    Retorna la cantidad de estaciones en estado DISPONIBLE
    Para KPIs del dashboard
    */
    public int getCantidadEstacionesDisponibles() {
        int count = 0;
        for (Estacion estacion : estaciones) {
            if (estacion.getEstado() == EstadoEstacion.DISPONIBLE) {
                count++;
            }
        }
        return count;
    }

    /*
    getCantidadEstacionesEnMantenimiento(){}
    Retorna la cantidad de estaciones en estado EN_MANTENIMIENTO
    Para KPIs del dashboard
    */
    public int getCantidadEstacionesEnMantenimiento() {
        int count = 0;
        for (Estacion estacion : estaciones) {
            if (estacion.getEstado() == EstadoEstacion.EN_MANTENIMIENTO) {
                count++;
            }
        }
        return count;
    }

    /*
    getCantidadEstacionesEnMalEstado(){}
    Retorna la cantidad de estaciones en estado EN_MAL_ESTADO
    Para KPIs del dashboard
    */
    public int getCantidadEstacionesEnMalEstado() {
        int count = 0;
        for (Estacion estacion : estaciones) {
            if (estacion.getEstado() == EstadoEstacion.EN_MAL_ESTADO) {
                count++;
            }
        }
        return count;
    }

    /*
    getCapacidadTotalSistema(){}
    Calcula la capacidad total del sistema (suma de todas las capacidades máximas)
    Retorna int con la capacidad total
    */
    public int getCapacidadTotalSistema() {
        int capacidadTotal = 0;
        for (Estacion estacion : estaciones) {
            capacidadTotal += estacion.getCapacidadMaxima();
        }
        return capacidadTotal;
    }

    /*
    getOcupacionInstantaneaSistema(){}
    Calcula la ocupación instantánea del sistema (dispositivos atendidos / capacidad total)
    Retorna double con el porcentaje de ocupación del sistema
    */
    public double getOcupacionInstantaneaSistema() {
        int dispositivosAtendiendo = 0;
        int capacidadTotal = getCapacidadTotalSistema();

        if (capacidadTotal == 0) {
            return 0.0;
        }

        for (Estacion estacion : estaciones) {
            dispositivosAtendiendo += estacion.getCantidadDispositivosAtendiendo();
        }

        return (double) dispositivosAtendiendo / capacidadTotal * 100;
    }

    /*
    verificarEstacionDisponible(String idEstacion){}
    Verifica si una estación específica está disponible y tiene capacidad
    Retorna boolean
    */
    public boolean verificarEstacionDisponible(String idEstacion) {
        Estacion estacion = buscarEstacion(idEstacion);
        if (estacion == null) {
            return false;
        }
        return estacion.estaDisponible() && estacion.tieneCapacidadDisponible();
    }

    /*
    getTotalEstaciones(){}
    Retorna la cantidad total de estaciones gestionadas
    */
    public int getTotalEstaciones() {
        return estaciones.size();
    }

    /*
    getTodasEstaciones(){}
    Retorna una copia de la lista de todas las estaciones
    */
    public ArrayList<Estacion> getTodasEstaciones() {
        return new ArrayList<>(estaciones);
    }

    // Getters y Setters
    public ArrayList<Estacion> getEstaciones() {
        return new ArrayList<>(estaciones);
    }

    public void setEstaciones(ArrayList<Estacion> estaciones) {
        this.estaciones = estaciones;
    }
}