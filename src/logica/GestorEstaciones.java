package logica;

import modelo.*;

import java.util.ArrayList;
import java.util.List;

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
    buscarEstacion(String id){}
    Busca una estación por su ID único
    Retorna la Estacion encontrada o null si no existe
    */

    /*
    actualizarEstadoEstacion(String idEstacion, EstadoEstacion nuevoEstado){}
    Actualiza el estado de una estación específica (disponible, en mal estado, en mantenimiento)
    Retorna boolean indicando si fue exitosa la actualización
    */

    /*
    obtenerEstacionesDisponibles(){}
    Retorna lista de estaciones que están disponibles para uso
    Útil para asignar dispositivos que necesitan recarga
    */

    /*
    obtenerEstacionesPorEstado(EstadoEstacion estado){}
    Retorna lista de estaciones filtradas por estado específico
    Útil para reportes del dashboard
    */

    /*
    atenderRobot(String idEstacion, Robot robot){}
    Asigna un robot a una estación para recarga
    Verifica que la estación esté disponible y tenga capacidad
    Retorna boolean indicando si fue exitosa la operación
    */

    /*
    atenderDron(String idEstacion, Dron dron){}
    Asigna un dron a una estación para recarga
    Verifica que la estación esté disponible y tenga capacidad
    Retorna boolean indicando si fue exitosa la operación
    */

    /*
    finalizarRecargaRobot(String idEstacion, String idRobot){}
    Finaliza la recarga de un robot en una estación específica
    Registra la recarga en el historial con fecha/hora y niveles de batería
    Retorna boolean indicando si fue exitosa la operación
    */

    /*
    finalizarRecargaDron(String idEstacion, String idDron){}
    Finaliza la recarga de un dron en una estación específica
    Registra la recarga en el historial con fecha/hora y niveles de batería
    Retorna boolean indicando si fue exitosa la operación
    */

    /*
    obtenerEstacionesConCapacidad(){}
    Retorna lista de estaciones que tienen capacidad disponible
    Útil para asignar nuevos dispositivos a recargar
    */

    /*
    calcularOcupacionPromedio(){}
    Calcula el porcentaje de ocupación promedio de todas las estaciones
    Retorna double con el promedio
    */

    /*
    obtenerEstadisticasEstaciones(){}
    Retorna estadísticas detalladas de todas las estaciones
    Incluye: total estaciones, disponibles, en mantenimiento, ocupación promedio, etc.
    */

    /*
    obtenerEstacionesConAltaOcupacion(double porcentajeMinimo){}
    Retorna lista de estaciones con ocupación mayor o igual al porcentaje especificado
    Útil para identificar estaciones sobresaturadas
    */

    /*
    obtenerTotalRecargasAtendidas(){}
    Calcula el total de recargas atendidas por todas las estaciones
    Retorna int con la suma de todos los registros de recarga
    */

    /*
    obtenerRecargasRecientes(int horas){}
    Retorna lista de recargas realizadas en las últimas N horas
    Para estadísticas de actividad reciente
    */

    /*
    redistribuirDispositivos(){}
    Redistribuye dispositivos entre estaciones para balancear la carga
    Mueve dispositivos de estaciones muy ocupadas a estaciones con capacidad
    Retorna la cantidad de dispositivos redistribuidos
    */

    /*
    obtenerEstacionOptimaParaRecarga(){}
    Encuentra la estación más adecuada para asignar un nuevo dispositivo
    Considera: capacidad disponible, ubicación, tiempo de espera estimado
    Retorna la Estacion óptima o null si no hay disponible
    */

    /*
    getCantidadEstacionesDisponibles(){}
    Retorna la cantidad de estaciones en estado DISPONIBLE
    Para KPIs del dashboard
    */

    /*
    getCantidadEstacionesEnMantenimiento(){}
    Retorna la cantidad de estaciones en estado EN_MANTENIMIENTO
    Para KPIs del dashboard
    */

    /*
    getCantidadEstacionesEnMalEstado(){}
    Retorna la cantidad de estaciones en estado EN_MAL_ESTADO
    Para KPIs del dashboard
    */

    /*
    getCapacidadTotalSistema(){}
    Calcula la capacidad total del sistema (suma de todas las capacidades máximas)
    Retorna int con la capacidad total
    */

    /*
    getOcupacionInstantaneaSistema(){}
    Calcula la ocupación instantánea del sistema (dispositivos atendidos / capacidad total)
    Retorna double con el porcentaje de ocupación del sistema
    */

    /*
    verificarEstacionDisponible(String idEstacion){}
    Verifica si una estación específica está disponible y tiene capacidad
    Retorna boolean
    */

    /*
    getTotalEstaciones(){}
    Retorna la cantidad total de estaciones gestionadas
    */

    /*
    getTodasEstaciones(){}
    Retorna una copia de la lista de todas las estaciones
    */

    // Getters y Setters
    public ArrayList<Estacion> getEstaciones() {
        return new ArrayList<>(estaciones); // Copia para proteger la lista original
    }

    public void setEstaciones(ArrayList<Estacion> estaciones) {
        this.estaciones = estaciones;
    }
}