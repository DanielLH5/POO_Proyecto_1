package logica;

import modelo.Ciudadano;
import modelo.Edificio;
import modelo.Estacion;
import modelo.Robot;
import java.util.ArrayList;

public class GestorCiudadanos {
    private ArrayList<Ciudadano> ciudadanos;
    private GestorRobots gRobots;

    public GestorCiudadanos() {
        ciudadanos = new ArrayList<>();
        gRobots = new GestorRobots();
    }

    public boolean crearCiudadano(String id, String nombre, String edificioId) {
        for (Ciudadano ciudadano : ciudadanos) {
            if (ciudadano.getId().equals(id)) {
                System.out.println("Error: Ya existe un ciudadano con el mismo ID: " + id);
                return false;
            }
        }

        Ciudadano nuevoCiudadano = new Ciudadano(id, nombre, edificioId);
        ciudadanos.add(nuevoCiudadano);
        System.out.println("Ciudadano creado: " + nombre + " " + " en edificio " + edificioId);
        return true;
    }

    public boolean actualizarCiudadano(String id, String nuevoNombre, String nuevoEdificioId, String nuevoRobot) {
        for (Ciudadano ciudadano : ciudadanos) {
            if (ciudadano.getId().equals(id)) {
                if (!ciudadano.getNombre().equals(nuevoNombre)) {
                    ciudadano.setNombre(nuevoNombre);
                } else if (!ciudadano.getEdificio().equals(nuevoEdificioId)) {
                    ciudadano.setEdificio(nuevoEdificioId);
                }
                for (Robot robot : ciudadano.getRobotsAsistentes()) {
                    if (!robot.getIdProcesador().equals(nuevoRobot)) {
                        gRobots.crearRobotIndividual(nuevoRobot, 100);
                        Robot botNuevo = gRobots.buscarRobot(nuevoRobot);
                        ciudadano.agregarRobotAsistente(botNuevo);
                        System.out.println("Robot " + nuevoRobot + " agregado al ciudadano " + id);
                        break;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public boolean eliminarCiudadano(String id) {
        for (int i = 0; i < ciudadanos.size(); i++) {
            Ciudadano ciudadano = ciudadanos.get(i);
            if (ciudadano.getId().equals(id)) {
                ciudadanos.remove(i);
                return true;
            }
        }
        return false;
    }

    public Ciudadano obtenerCiudadano(String id) {
        // Buscar el ciudadano
        for (Ciudadano ciudadano : ciudadanos) {
            if (ciudadano.getId().equals(id)) {
                return ciudadano;
            }
        }

        System.out.println("Error: No se encontró un ciudadano con ID: " + id);
        return null;
    }
}
    /*
    registrarCiudadano(String id, String nombre, Edificio edificio){}
    Registra un nuevo ciudadano en el sistema
    Verifica que el edificio tenga capacidad disponible
    Retorna boolean indicando si fue exitoso el registro
    */

    /*
    registrarCiudadanos(int cantidad, ArrayList<Edificio> edificiosDisponibles){}
    Registra un grupo de N ciudadanos de forma masiva
    Asigna automáticamente edificios con capacidad disponible
    Retorna boolean indicando si todos fueron registrados exitosamente
    */

    /*
    buscarCiudadano(String id){}
    Busca un ciudadano por su ID único
    Retorna el Ciudadano encontrado o null si no existe
    */

    /*
    actualizarCiudadano(String id, String nuevoNombre, Edificio nuevoEdificio){}
    Actualiza los datos de un ciudadano existente
    Verifica que el nuevo edificio tenga capacidad
    Retorna boolean indicando si fue exitosa la actualización
    */

    /*
    eliminarCiudadano(String id){}
    Elimina un ciudadano del sistema
    Retorna boolean indicando si fue exitosa la eliminación
    */

    /*
    asignarRobotACiudadano(String idCiudadano, Robot robot){}
    Asigna un robot a un ciudadano específico
    Verifica que el ciudadano exista
    Retorna boolean indicando si la asignación fue exitosa
    */

    /*
    removerRobotDeCiudadano(String idCiudadano, String idRobot){}
    Remueve un robot de un ciudadano
    Retorna boolean indicando si fue exitosa la remoción
    */

    /*
    obtenerCiudadanosConRobot(){}
    Retorna lista de ciudadanos que tienen al menos un robot asignado
    Útil para KPIs de cobertura del dashboard
    */

    /*
    obtenerCiudadanosSinRobot(){}
    Retorna lista de ciudadanos que no tienen robots asignados
    Útil para KPIs de cobertura del dashboard
    */

    /*
    contarCiudadanosConRobot(){}
    Retorna la cantidad total de ciudadanos que tienen al menos un robot
    */

    /*
    contarCiudadanosSinRobot(){}
    Retorna la cantidad total de ciudadanos sin robots asignados
    */

    /*
    obtenerCiudadanosPorEdificio(String idEdificio){}
    Retorna lista de ciudadanos que viven en un edificio específico
    Útil para reportes por edificio en el dashboard
    */

    /*
    obtenerCiudadanosConRobotsEnAlerta(int umbralMinimo){}
    Retorna lista de ciudadanos que tienen al menos un robot en estado de alerta
    Útil para KPIs de energía del dashboard
    */

    /*
    contarCiudadanosConRobotsEnAlerta(int umbralMinimo){}
    Retorna la cantidad de ciudadanos afectados por robots en alerta
    */

    /*
    getTodosCiudadanos(){}
    Retorna la lista completa de ciudadanos (copia para evitar modificación externa)
    */

    /*
    existeCiudadano(String id){}
    Verifica si ya existe un ciudadano con el ID especificado
    Retorna boolean - útil para evitar duplicados
    */

    /*
    getTotalCiudadanos(){}
    Retorna la cantidad total de ciudadanos registrados
    */

    /*
    obtenerCiudadanosConMultiplesRobots(){}
    Retorna lista de ciudadanos que tienen más de un robot asignado
    Para análisis de distribución de recursos
    */

    /*
    calcularRelacionRobotsPorCiudadano(){}
    Calcula la relación promedio de robots por ciudadano en el sistema
    Retorna double (totalRobots / totalCiudadanos)
    */
