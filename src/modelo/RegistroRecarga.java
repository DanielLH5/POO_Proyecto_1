package modelo;

import java.time.LocalDateTime;

public class RegistroRecarga {
    // Atributos
    private String idDispositivo;
    private String tipoDispositivo; // "ROBOT" o "DRONE"
    private LocalDateTime fechaHora;
    private int nivelBateriaInicial;
    private int nivelBateriaFinal;
    private int energiaSuministrada;

    // Constructor
    public RegistroRecarga(String idDispositivo, String tipoDispositivo,
                           LocalDateTime fechaHora, int nivelBateriaInicial,
                           int nivelBateriaFinal) {
        this.idDispositivo = idDispositivo;
        this.tipoDispositivo = tipoDispositivo;
        this.fechaHora = fechaHora;
        this.nivelBateriaInicial = nivelBateriaInicial;
        this.nivelBateriaFinal = nivelBateriaFinal;
        this.energiaSuministrada = nivelBateriaFinal - nivelBateriaInicial;
    }

    // Getters
    public String getIdDispositivo() {
        return idDispositivo;
    }

    public String getTipoDispositivo() {
        return tipoDispositivo;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public int getNivelBateriaInicial() {
        return nivelBateriaInicial;
    }

    public int getNivelBateriaFinal() {
        return nivelBateriaFinal;
    }

    public int getEnergiaSuministrada() {
        return energiaSuministrada;
    }

    /*
    getDuracionRecarga(){}
    Si tuvieras fechaHoraInicio y fechaHoraFin
    Calcula la duración de la recarga en minutos
    Retorna long con los minutos de diferencia
    */

    /*
    fueRecargaCompleta(){}
    Verifica si la recarga fue al 100%
    Retorna boolean (nivelBateriaFinal == 100)
    */

    /*
    getEficienciaRecarga(){}
    Calcula la eficiencia de la recarga
    Retorna double (energiaSuministrada / duracion) si tuvieras duración
    */

    // toString
    @Override
    public String toString() {
        return "RegistroRecarga{" +
                "idDispositivo='" + idDispositivo + '\'' +
                ", tipoDispositivo='" + tipoDispositivo + '\'' +
                ", fechaHora=" + fechaHora +
                ", nivelBateriaInicial=" + nivelBateriaInicial +
                ", nivelBateriaFinal=" + nivelBateriaFinal +
                ", energiaSuministrada=" + energiaSuministrada +
                '}';
    }
}