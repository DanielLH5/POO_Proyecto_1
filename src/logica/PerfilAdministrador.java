package logica;

import java.util.ArrayList;
import java.util.List;
import modelo.Evento;
import modelo.Accion;
import modelo.EstadoEstacion;

public class PerfilAdministrador {
    private List<GestorDrones> gDrones;
    private List<GestorCiudadanos> gCiudadanos;
    private List<GestorEdificios> gEdificios;
    private List<GestorRobots> gRobots;
    private List<GestorEstaciones> gEstaciones;

    // Reglas del sistema (según especificaciones página 4)
    private int umbralBateriaMinimaDrones;
    private int umbralBateriaMinimaRobots;
    private boolean reglaDronesActiva;
    private boolean reglaRobotsActiva;

    // Asociaciones evento-acción para el Consejo de Inteligencia
    private java.util.Map<Evento, List<Accion>> asociacionesEventoAccion;

    public PerfilAdministrador(){
        gDrones = new ArrayList<GestorDrones>();
        gCiudadanos = new ArrayList<GestorCiudadanos>();
        gEdificios = new ArrayList<GestorEdificios>();
        gRobots = new ArrayList<GestorRobots>();
        gEstaciones = new ArrayList<GestorEstaciones>();

        // Valores por defecto para las reglas
        this.umbralBateriaMinimaDrones = 20;
        this.umbralBateriaMinimaRobots = 15;
        this.reglaDronesActiva = true;
        this.reglaRobotsActiva = true;

        this.asociacionesEventoAccion = new java.util.HashMap<>();
        inicializarAsociacionesPredefinidas();
    }

    // ===== MÉTODOS DE INICIALIZACIÓN PRIVADOS =====
    private void inicializarAsociacionesPredefinidas() {
        // Inicializar con asociaciones lógicas por defecto
        // Ejemplo: Incendio -> Contactar bomberos y llamar al 911
        // Esto puede expandirse según las especificaciones
    }

    // ===== GETTERS Y SETTERS PARA LOS GESTORES =====
    public List<GestorDrones> getGestorDrones() {
        return gDrones;
    }

    public List<GestorCiudadanos> getGestorCiudadanos() {
        return gCiudadanos;
    }

    public List<GestorEdificios> getGestorEdificios() {
        return gEdificios;
    }

    public List<GestorRobots> getGestorRobots() {
        return gRobots;
    }

    public List<GestorEstaciones> getGestorEstaciones() {
        return gEstaciones;
    }

    // ===== GETTERS Y SETTERS PARA LAS REGLAS =====
    public int getUmbralBateriaMinimaDrones() {
        return umbralBateriaMinimaDrones;
    }

    public int getUmbralBateriaMinimaRobots() {
        return umbralBateriaMinimaRobots;
    }

    public boolean isReglaDronesActiva() {
        return reglaDronesActiva;
    }

    public boolean isReglaRobotsActiva() {
        return reglaRobotsActiva;
    }

    public java.util.Map<Evento, List<Accion>> getAsociacionesEventoAccion() {
        return new java.util.HashMap<>(asociacionesEventoAccion);
    }

    /*
    // ===== CONFIGURACIÓN INICIAL DEL SISTEMA =====
    configurarEdificiosInteligentes(int cantidad, boolean aleatorio){}
    Crea entre 3-10 edificios inteligentes (Requerimiento 1)
    Puede ser cantidad definida por admin o aleatoria
    Retorna boolean indicando éxito

    configurarEstacionesEnergia(int cantidad, boolean aleatorio){}
    Crea entre 5-8 estaciones de energía (Requerimiento 2)
    Puede ser cantidad definida por admin o aleatoria
    Retorna boolean indicando éxito

    inicializarEventosPredefinidos(){}
    Inicializa los eventos pre-registrados (Requerimiento 3)
    Se ejecuta automáticamente en el constructor

    inicializarAccionesPredefinidas(){}
    Inicializa las acciones pre-registradas (Requerimiento 4)
    Se ejecuta automáticamente en el constructor
    */

    /*
    // ===== GESTIÓN DE ESTACIONES DE ENERGÍA =====
    actualizarEstadoEstacion(String idEstacion, EstadoEstacion nuevoEstado){}
    Actualiza el estado de una estación específica (Requerimiento 2)
    Estados: disponible, en mal estado, en mantenimiento
    Retorna boolean indicando éxito
    */

    /*
    // ===== GESTIÓN DE ASOCIACIONES EVENTO-ACCIÓN =====
    gestionarAsociacionEventoAccion(Evento evento, List<Accion> acciones){}
    Gestiona la asociación de acciones a realizar para cada evento (Requerimiento 5)
    Cada evento debe tener al menos 1 acción asociada
    Retorna boolean indicando éxito

    agregarAsociacionEventoAccion(Evento evento, Accion accion){}
    Agrega una acción específica a un evento
    Retorna boolean indicando éxito

    eliminarAsociacionEventoAccion(Evento evento, Accion accion){}
    Elimina una acción específica de un evento
    Verifica que el evento quede con al menos 1 acción
    Retorna boolean indicando éxito

    obtenerAccionesParaEvento(Evento evento){}
    Retorna la lista de acciones asociadas a un evento específico
    Para que el Consejo de Inteligencia las utilice
    */

    /*
    // ===== GESTIÓN DE REGLAS DEL SISTEMA (Requerimiento 6) =====
    modificarUmbralBateriaDrones(int nuevoUmbral){}
    Modifica el umbral mínimo de batería para drones
    Solo si la regla está activa - afecta a todos los drones
    Retorna boolean indicando éxito

    modificarUmbralBateriaRobots(int nuevoUmbral){}
    Modifica el umbral mínimo de batería para robots
    Solo si la regla está activa - afecta a todos los robots
    Retorna boolean indicando éxito

    activarReglaDrones(boolean activa){}
    Activa o desactiva la regla de batería para drones
    Retorna el estado anterior de la regla

    activarReglaRobots(boolean activa){}
    Activa o desactiva la regla de batería para robots
    Retorna el estado anterior de la regla
    */

    /*
    // ===== CONSULTAS Y REPORTES =====
    consultarParametrizacionActual(){}
    Consulta y retorna la parametrización actual del sistema
    Incluye: cantidad edificios, estaciones, reglas activas, umbrales, etc.

    generarReporteConfiguracion(){}
    Genera un reporte detallado de toda la configuración del sistema
    Para que el administrador pueda revisar el estado completo

    obtenerEstadisticasSistema(){}
    Retorna estadísticas generales del sistema configurado
    Incluye: totales por entidad, estados, capacidades, etc.
    */

    /*
    // ===== VALIDACIONES DEL SISTEMA =====
    validarConfiguracionMinima(){}
    Valida que el sistema tenga la configuración mínima requerida
    Verifica: al menos 3 edificios, 5 estaciones, asociaciones evento-acción, etc.
    Retorna boolean indicando si el sistema está listo para operar

    verificarAsociacionesCompletas(){}
    Verifica que todos los eventos tengan al menos una acción asociada
    Retorna lista de eventos sin acciones asociadas (debe estar vacía)
    */
}