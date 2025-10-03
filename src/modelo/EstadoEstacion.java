package modelo;

public enum EstadoEstacion {
    DISPONIBLE("Disponible"),
    EN_MAL_ESTADO("En mal estado"),
    EN_MANTENIMIENTO("En mantenimiento");

    private final String descripcion;

    EstadoEstacion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }

    /*
    MÉTODOS ADICIONALES QUE PODRÍAS NECESITAR:

    isOperativa(){}
    Verifica si la estación puede atender dispositivos
    Retorna boolean (this == DISPONIBLE)

    fromString(String texto){}
    Convierte un texto en el enum correspondiente
    Útil para configuraciones del administrador

    getEstadosCriticos(){}
    Retorna lista de estados que impiden el funcionamiento
    Para alertas del dashboard
    */
}