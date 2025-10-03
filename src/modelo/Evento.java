package modelo;

public enum Evento {
    COLISION_VEHICULAR("Colisión vehicular"),
    CONGESTION_VEHICULAR("Congestión vehicular"),
    OBRA_PUBLICA("Desarrollo de obra pública"),
    DERRAME_SUSTANCIAS("Derrames de sustancias peligrosas en carretera"),
    INCENDIO("Incendio"),
    PRESENCIA_HUMO("Presencia de humo"),
    PRESENCIA_GASES("Presencia de gases"),
    ACCIDENTE_GRAVE("Accidente grave"),
    AMBULANCIAS_EMERGENCIA("Presencia de ambulancias en estado de emergencia");

    private final String descripcion;

    Evento(String descripcion) {
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