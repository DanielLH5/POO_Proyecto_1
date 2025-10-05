package logica;

import modelo.Robot;
import modelo.TareaRobot;
import modelo.Ciudadano;
import modelo.RegistroTarea;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GestorRobots {
    private ArrayList<Robot> robots;

    public GestorRobots(){
        robots = new ArrayList<>();
    }
    
    public boolean crearRobotIndividual(String idProcesador, int nivelBateria) {
        // Verificar si ya existe un robot con ese ID
        if (buscarRobot(idProcesador) != null) {
            System.out.println("Error: Ya existe un robot con el ID " + idProcesador);
            return false;
        }

        // Validar nivel de batería
        if (nivelBateria < 0 || nivelBateria > 100) {
            System.out.println("Error: El nivel de batería debe estar entre 0 y 100");
            return false;
        }

        // Crear y agregar el robot
        Robot nuevoRobot = new Robot(idProcesador, nivelBateria);
        robots.add(nuevoRobot);
        System.out.println("Robot creado exitosamente: " + nuevoRobot);
        return true;
    }

    /*
    crearRobotsBloque(int cantidad){}
    Crea un grupo de N robots de forma masiva
    Asigna IDs consecutivos automáticamente y batería entre 20-100%
    Asigna tareas aleatorias a cada robot
    Retorna boolean indicando si la creación fue exitosa
    */
    public boolean crearRobotsBloque(int cantidad) {
        if (cantidad <= 0) {
            System.out.println("Error: La cantidad debe ser mayor a 0");
            return false;
        }

        Random random = new Random();
        int creadosExitosamente = 0;

        for (int i = 0; i < cantidad; i++) {
            String idProcesador = "ROB" + String.format("%04d", robots.size() + 1);
            int nivelBateria = 20 + random.nextInt(81); // 20-100%

            Robot nuevoRobot = new Robot(idProcesador, nivelBateria);
            robots.add(nuevoRobot);
            creadosExitosamente++;
        }

        System.out.println("Se crearon " + creadosExitosamente + " robots.");
        return creadosExitosamente == cantidad;
    }

    /*
    buscarRobot(String idProcesador){}
    Busca un robot por su ID de procesador
    Retorna el Robot encontrado o null si no existe
    */
    public Robot buscarRobot(String idProcesador) {
        for (Robot robot : robots) {
            if (robot.getIdProcesador().equals(idProcesador)) {
                return robot;
            }
        }
        return null;
    }

    /*
    actualizarBateriaRobot(String idProcesador, int nuevaBateria){}
    Actualiza el nivel de batería de un robot específico
    No puede exceder 100% ni ser menor a 0%
    Retorna boolean indicando si fue exitosa la actualización
    */
    public boolean actualizarBateriaRobot(String idProcesador, int nuevaBateria) {
        Robot robot = buscarRobot(idProcesador);
        if (robot == null) {
            System.out.println("Error: No se encontró el robot con ID " + idProcesador);
            return false;
        }

        if (nuevaBateria < 0 || nuevaBateria > 100) {
            System.out.println("Error: El nivel de batería debe estar entre 0 y 100");
            return false;
        }

        robot.setNivelBateria(nuevaBateria);
        System.out.println("Batería del robot " + idProcesador + " actualizada a " + nuevaBateria + "%");
        return true;
    }

    /*
    eliminarRobot(String idProcesador){}
    Elimina un robot del sistema
    Verifica que el robot no esté asignado a ningún ciudadano
    Retorna boolean indicando si fue exitosa la eliminación
    */

    /*
    asignarTareaRobot(String idProcesador, TareaRobot tarea){}
    Asigna una tarea específica a un robot para que la realice
    Verifica que el robot tenga batería suficiente para la tarea
    Registra la tarea en el historial del robot
    Retorna boolean indicando si la tarea fue ejecutada
    */

    /*
    obtenerRobotsDisponiblesParaTarea(TareaRobot tarea){}
    Retorna lista de robots que pueden realizar una tarea específica
    (tienen la tarea asignada y batería suficiente)
    */

    /*
    obtenerRobotsEnAlerta(int umbralMinimo){}
    Retorna lista de robots que están en estado de alerta (batería < umbral)
    Útil para el dashboard de energía
    */

    /*
    contarRobotsEnAlerta(int umbralMinimo){}
    Retorna la cantidad de robots en estado de alerta
    Para KPIs del dashboard
    */

    /*
    obtenerRobotsPorCiudadano(Ciudadano ciudadano){}
    Retorna lista de robots asignados a un ciudadano específico
    */

    /*
    obtenerRobotsSinAsignar(){}
    Retorna lista de robots que no están asignados a ningún ciudadano
    Útil para asignaciones futuras
    */

    /*
    recargarRobot(String idProcesador, int cantidad){}
    Recarga la batería de un robot específico en la cantidad indicada
    No puede exceder el 100%
    Retorna boolean indicando si fue exitosa la recarga
    */

    /*
    recargarRobotsCompletos(List<String> idsProcesador){}
    Recarga al 100% un grupo de robots
    Útil cuando llegan a una estación de energía
    */

    /*
    obtenerEstadisticasRobots(){}
    Retorna estadísticas generales de todos los robots
    Incluye: total robots, robots en alerta, promedio de batería, etc.
    */

    /*
    obtenerRobotsConBateriaCritica(int umbralCritico){}
    Retorna lista de robots con batería por debajo de un umbral crítico
    Para priorizar recargas urgentes
    */

    /*
    simularConsumoDiario(){}
    Simula el consumo diario de batería de todos los robots
    Reduce la batería basado en tareas realizadas aleatoriamente
    Retorna el consumo total de energía del día
    */

    /*
    obtenerHistorialTareasRobot(String idProcesador){}
    Retorna el historial completo de tareas de un robot específico
    Incluye fecha, hora y tarea realizada
    */

    /*
    obtenerTareasMasRealizadas(){}
    Retorna un mapa con las tareas más realizadas por todos los robots
    Para análisis de patrones de uso
    */

    /*
    obtenerRobotsPorNivelBateria(int nivelMinimo, int nivelMaximo){}
    Retorna lista de robots con batería en el rango especificado
    Útil para filtros en el dashboard
    */

    /*
    verificarRobotPuedeRealizarTarea(String idProcesador, TareaRobot tarea){}
    Verifica si un robot específico puede realizar una tarea
    (tiene la tarea asignada y batería suficiente)
    Retorna boolean
    */

    /*
    getTotalRobots(){}
    Retorna la cantidad total de robots gestionados
    */

    /*
    getTodosRobots(){}
    Retorna una copia de la lista de todos los robots
    */

    /*
    obtenerRobotsAsignables(){}
    Retorna lista de robots que pueden ser asignados a ciudadanos
    (no asignados y en buen estado)
    */

    // Getters y Setters
    public ArrayList<Robot> getRobots() {
        return new ArrayList<>(robots); // Copia para proteger la lista original
    }

    public void setRobots(ArrayList<Robot> robots) {
        this.robots = robots;
    }
}