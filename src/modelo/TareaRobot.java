package modelo;

public enum TareaRobot {
    AGENDAR_CITA_MEDICA("Agendar cita médica", 5),
    ASEAR_DORMITORIO("Asear el dormitorio", 15),
    ELABORAR_LISTA_ALIMENTOS("Elaborar lista de alimentos", 10),
    REGAR_PLANTAS("Regar las plantas", 5),
    PASEO_EXTERIOR("Dar un paseo exterior", 20),
    ASISTIR_REUNION("Asistir a reunión", 25);

    private final String descripcion;
    private final int consumoEnergetico;

    TareaRobot(String descripcion, int consumoEnergetico) {
        this.descripcion = descripcion;
        this.consumoEnergetico = consumoEnergetico;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getConsumoEnergetico() {
        return consumoEnergetico;
    }
}