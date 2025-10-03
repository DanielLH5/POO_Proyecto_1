package modelo;

import java.time.LocalDateTime;
import java.util.List;

public class ReporteAnomalia {
    private Evento evento;
    private LocalDateTime fechaHora;
    private String idDron;
    private List<Accion> acciones;

    public ReporteAnomalia(Evento evento, LocalDateTime fechaHora, String idDron) {
        this.evento = evento;
        this.fechaHora = fechaHora;
        this.idDron = idDron;
    }

    // Getters y Setters
    public Evento getEvento() {
        return evento;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public String getIdDron() {
        return idDron;
    }

    public List<Accion> getAcciones() {
        return acciones;
    }

    public ReporteAnomalia setAcciones(List<Accion> acciones) {
        this.acciones = acciones;
        return this;
    }

    @Override
    public String toString() {
        return "ReporteAnomalia{" +
                "evento=" + evento +
                ", fechaHora=" + fechaHora +
                ", idDron='" + idDron + '\'' +
                ", acciones=" + acciones +
                '}';
    }
}