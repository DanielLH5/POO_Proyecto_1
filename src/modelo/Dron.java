package modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dron {
    // Atributos (según especificaciones página 5)
    private String idProcesador;
    private int horasVueloDisponible;
    private int nivelBateria;
    private boolean enAlerta;
    private List<ReporteAnomalia> historialReportes;
    private boolean enPatrullaje;

    // Constructor
    public Dron(String idProcesador) {
        this.idProcesador = idProcesador;
        Random random = new Random();
        this.horasVueloDisponible = 1 + random.nextInt(4); // 1-4 horas aleatorias
        this.nivelBateria = this.horasVueloDisponible * 25; // 25% por hora de vuelo
        this.enAlerta = false;
        this.historialReportes = new ArrayList<>();
        this.enPatrullaje = false;
    }

    // Getters y Setters
    public String getIdProcesador() {
        return idProcesador;
    }

    public void setIdProcesador(String idProcesador) {
        this.idProcesador = idProcesador;
    }

    public int getHorasVueloDisponible() {
        return horasVueloDisponible;
    }

    public void setHorasVueloDisponible(int horasVueloDisponible) {
        this.horasVueloDisponible = horasVueloDisponible;
    }

    public int getNivelBateria() {
        return nivelBateria;
    }

    public void setNivelBateria(int nivelBateria) {
        this.nivelBateria = nivelBateria;
    }

    public boolean isEnAlerta() {
        return enAlerta;
    }

    public void setEnAlerta(boolean enAlerta) {
        this.enAlerta = enAlerta;
    }

    public List<ReporteAnomalia> getHistorialReportes() {
        return new ArrayList<>(historialReportes);
    }

    public boolean isEnPatrullaje() {
        return enPatrullaje;
    }

    public void setEnPatrullaje(boolean enPatrullaje) {
        this.enPatrullaje = enPatrullaje;
    }

    //Métodos adicionales
    /*
    realizarPatrullaje(){}
    Realiza una hora de patrullaje (consume 25% de batería)
    Verifica que tenga batería suficiente y no esté en alerta
    Actualiza el estado de patrullaje y consume batería
    Retorna boolean indicando si pudo patrullar
    */

    /*
    finalizarPatrullaje(){}
    Finaliza el patrullaje actual
    Actualiza el estado y las horas de vuelo disponibles
    */

    /*
    detectarAnomalia(){}
    Simula la detección de una anomalía durante el patrullaje
    Genera un evento aleatorio y crea un reporte
    Retorna el ReporteAnomalia generado (o null si no detectó nada)
    */

    /*
    necesitaRecarga(int umbralMinimo){}
    Verifica si el drone necesita recarga urgente
    Retorna boolean (nivelBateria < umbralMinimo)
    */

    /*
    recargarBateria(int cantidad){}
    Aumenta el nivel de batería en la cantidad especificada
    No puede exceder el 100%
    Actualiza las horas de vuelo disponibles (batería / 25)
    */

    /*
    recargarCompleto(){}
    Recarga la batería al 100% y actualiza horas de vuelo a 4
    */

    /*
    estaOperativo(){}
    Verifica si el drone está operativo para patrullar
    Retorna boolean (batería > 0 y no en alerta crítica)
    */

    /*
    getHorasVueloDisponible(){}
    Calcula las horas de vuelo disponibles basado en la batería actual
    Retorna int (nivelBateria / 25)
    */

    /*
    getReportesRecientes(){}
    Retorna los reportes de las últimas 24 horas
    Para estadísticas del dashboard
    */

    /*
    getTotalReportes(){}
    Retorna el total de reportes generados por este drone
    */

    /*
    verificarEstadoAlerta(int umbralMinimo){}
    Verifica y actualiza el estado de alerta del drone
    Si batería < umbralMinimo, activa la alerta
    */

    // toString
    @Override
    public String toString() {
        return "Dron{" +
                "idProcesador='" + idProcesador + '\'' +
                ", horasVueloDisponible=" + horasVueloDisponible +
                ", nivelBateria=" + nivelBateria +
                ", enAlerta=" + enAlerta +
                ", historialReportes=" + historialReportes.size() +
                ", enPatrullaje=" + enPatrullaje +
                '}';
    }
}