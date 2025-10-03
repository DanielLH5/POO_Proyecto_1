package modelo;

import java.time.LocalDateTime;

public class RegistroTarea {
    private TareaRobot tarea;
    private LocalDateTime fechaHora;

    public RegistroTarea(TareaRobot tarea, LocalDateTime fechaHora) {
        this.tarea = tarea;
        this.fechaHora = fechaHora;
    }

    // Getters
    public TareaRobot getTarea() {
        return tarea;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }
}