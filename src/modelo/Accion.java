package modelo;

public enum Accion {
    CONTACTAR_BOMBEROS("Contactar a los bomberos"),
    CONTACTAR_TRANSITO("Contactar a oficiales de tránsito a apersonarse al lugar"),
    LLAMAR_911("Llamar al 911"),
    CONVOCAR_AMBULANCIAS("Convocar ambulancias");

    private final String descripcion;

    Accion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    /*
    MÉTODOS ADICIONALES QUE PODRÍAS NECESITAR:

    getAccionesPorPrioridad(){}
    Retorna las acciones ordenadas por prioridad
    Para que el Consejo ejecute en orden

    getTipoEmergencia(){}
    Retorna el tipo de emergencia que atiende cada acción
    Para estadísticas del dashboard
    */
}