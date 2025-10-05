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

}