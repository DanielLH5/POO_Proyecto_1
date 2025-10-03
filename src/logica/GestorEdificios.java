package logica;

import modelo.Edificio;
import modelo.Ciudadano;
import modelo.Robot;

import java.util.ArrayList;
import java.util.List;

public class GestorEdificios {
    private ArrayList<Edificio> edificios;

    public GestorEdificios(){
        edificios = new ArrayList<>();
    }

    /*
    crearEdificios(int cantidad){}
    Crea entre 3 y 10 edificios inteligentes con datos aleatorios o definidos
    Asigna ID único, nombre, ubicación (calle-avenida) y capacidad máxima diferente para cada uno
    Retorna boolean indicando si la creación fue exitosa
    */

    /*
    buscarEdificio(String id){}
    Busca un edificio por su ID único
    Retorna el Edificio encontrado o null si no existe
    */

    /*
    obtenerEdificiosConCapacidad(){}
    Retorna lista de edificios que tienen capacidad disponible
    Útil para asignar nuevos ciudadanos
    */

    /*
    agregarResidente(String idEdificio, Ciudadano ciudadano){}
    Agrega un residente a un edificio específico
    Verifica que el edificio exista y tenga capacidad disponible
    Retorna boolean indicando si fue exitosa la operación
    */

    /*
    removerResidente(String idEdificio, Ciudadano ciudadano){}
    Remueve un residente de un edificio (para mudanzas)
    Retorna boolean indicando si fue exitosa la operación
    */

    /*
    obtenerEdificiosPorOcupacion(double porcentajeMinimo){}
    Retorna lista de edificios con ocupación mayor o igual al porcentaje especificado
    Útil para identificar edificios al 90%+ (KPI del dashboard)
    */

    /*
    obtenerEdificiosConAltaOcupacion(){}
    Retorna lista de edificios con ocupación mayor al 90%
    Para priorizar traslados/expansión (KPI del dashboard)
    */

    /*
    calcularOcupacionPromedio(){}
    Calcula el porcentaje de ocupación promedio de todos los edificios
    Retorna double con el promedio
    */

    /*
    obtenerEdificiosConRobotsEnAlerta(int umbralMinimo){}
    Retorna lista de edificios que tienen al menos un robot en estado de alerta
    Útil para KPIs de energía del dashboard
    */

    /*
    obtenerEdificiosSinCoberturaRobots(double porcentajeMinimo){}
    Retorna lista de edificios donde el % de ciudadanos con robot es menor al especificado
    Útil para alertas de cobertura mínima (KPI del dashboard)
    */

    /*
    obtenerEstadisticasPorEdificio(){}
    Retorna un mapa o lista con estadísticas detalladas por edificio
    Incluye: capacidad, residentes, ocupación%, ciudadanos con/sin robot, robots asignados, etc.
    */

    /*
    obtenerTopEdificiosPorOcupacion(int cantidad){}
    Retorna los N edificios con mayor porcentaje de ocupación
    Para el Top-N del dashboard
    */

    /*
    obtenerTopEdificiosSinCoberturaRobots(int cantidad){}
    Retorna los N edificios con mayor porcentaje de ciudadanos sin robot
    Para el Top-N del dashboard
    */

    /*
    obtenerTopEdificiosConRobotsEnAlerta(int cantidad, int umbralMinimo){}
    Retorna los N edificios con más robots en estado de alerta
    Para el Top-N del dashboard
    */

    /*
    getTotalCapacidad(){}
    Calcula la capacidad total de todos los edificios combinados
    Retorna int con la suma de todas las capacidades máximas
    */

    /*
    getTotalResidentes(){}
    Calcula el total de residentes en todos los edificios
    Retorna int con la suma de todos los residentes
    */

    /*
    obtenerEdificioConMayorOcupacion(){}
    Retorna el edificio con el mayor porcentaje de ocupación
    */

    /*
    obtenerEdificioConMenorOcupacion(){}
    Retorna el edificio con el menor porcentaje de ocupación
    */

    /*
    verificarCapacidadEdificio(String idEdificio){}
    Verifica si un edificio específico tiene capacidad disponible
    Retorna boolean
    */

    /*
    getCantidadEdificios(){}
    Retorna la cantidad total de edificios gestionados
    */

    /*
    getTodosEdificios(){}
    Retorna una copia de la lista de todos los edificios
    */

    // Getters y Setters
    public ArrayList<Edificio> getEdificios() {
        return new ArrayList<>(edificios); // Copia para proteger la lista original
    }

    public void setEdificios(ArrayList<Edificio> edificios) {
        this.edificios = edificios;
    }
}