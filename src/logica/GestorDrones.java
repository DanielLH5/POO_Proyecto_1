package logica;

import modelo.Dron;
import modelo.Evento;
import modelo.ReporteAnomalia;
import modelo.Edificio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GestorDrones {
    private ArrayList<Dron> drones;

    public GestorDrones(){
        drones = new ArrayList<>();
    }

    // Getters y Setters
    public ArrayList<Dron> getDrones() {
        return new ArrayList<>(drones); // Copia para proteger la lista original
    }

    public void setDrones(ArrayList<Dron> drones) {
        this.drones = drones;
    }

    /*
    generarDrones(int cantidadEdificios){}
    Genera drones automáticamente en cantidad no mayor al doble de edificios
    Asigna ID único consecutivo, horas de vuelo aleatorias (1-4) y batería correspondiente
    No se pueden modificar ni eliminar después de creados
    Retorna boolean indicando si la generación fue exitosa
    */
    public boolean generarDrones(int cantidadEdificios) {
        if (cantidadEdificios <= 0) {
            System.out.println("Error: La cantidad de edificios debe ser mayor a 0");
            return false;
        }

        Random random = new Random();
        // Cantidad de drones: entre 1 y el doble de edificios (mínimo 1, máximo 2*cantidadEdificios)
        int cantidadDrones = 1 + random.nextInt(cantidadEdificios * 2);

        System.out.println("Generando " + cantidadDrones + " drones para " + cantidadEdificios + " edificios");

        for (int i = 0; i < cantidadDrones; i++) {
            String idProcesador = "DRON" + String.format("%03d", drones.size() + 1);
            Dron nuevoDron = new Dron(idProcesador);
            drones.add(nuevoDron);
            System.out.println("Dron creado: " + nuevoDron);
        }

        System.out.println("Generación completada: " + cantidadDrones + " drones creados");
        return true;
    }

    /*
    buscarDron(String idProcesador){}
    Busca un dron por su ID de procesador
    Retorna el Dron encontrado o null si no existe
    */
    public Dron buscarDron(String idProcesador) {
        for (Dron dron : drones) {
            if (dron.getIdProcesador().equals(idProcesador)) {
                return dron;
            }
        }
        System.out.println("No se encontró dron con ID: " + idProcesador);
        return null;
    }

    public boolean eliminarDron(String idProcesador) {
        return true;
    }

    /*
    obtenerDronesActivos(){}
    Retorna lista de drones que están operativos (batería > 0 y no en alerta crítica)
    Útil para la simulación de patrullaje
    */
    public List<Dron> obtenerDronesActivos() {
        List<Dron> dronesActivos = new ArrayList<>();
        for (Dron dron : drones) {
            if (dron.getNivelBateria() > 0 && !dron.isEnAlerta()) {
                dronesActivos.add(dron);
            }
        }
        System.out.println("Drones activos: " + dronesActivos.size());
        return dronesActivos;
    }

    /*
    obtenerDronesEnAlerta(int umbralMinimo){}
    Retorna lista de drones que están en estado de alerta (batería < umbralMinimo)
    Útil para el dashboard de energía
    */
    public List<Dron> obtenerDronesEnAlerta(int umbralMinimo) {
        List<Dron> dronesEnAlerta = new ArrayList<>();
        for (Dron dron : drones) {
            if (dron.getNivelBateria() < umbralMinimo) {
                dronesEnAlerta.add(dron);
            }
        }
        System.out.println("Drones en alerta: " + dronesEnAlerta.size() + " (umbral: " + umbralMinimo + "%)");
        return dronesEnAlerta;
    }

    /*
    contarDronesEnAlerta(int umbralMinimo){}
    Retorna la cantidad de drones en estado de alerta
    Para KPIs del dashboard
    */
    public int contarDronesEnAlerta(int umbralMinimo) {
        int count = 0;
        for (Dron dron : drones) {
            if (dron.getNivelBateria() < umbralMinimo) {
                count++;
            }
        }
        return count;
    }

    /*
    obtenerDronesDisponiblesParaPatrullaje(){}
    Retorna lista de drones que pueden patrullar (batería >= 25 y no en alerta)
    */
    public List<Dron> obtenerDronesDisponiblesParaPatrullaje() {
        List<Dron> dronesDisponibles = new ArrayList<>();
        for (Dron dron : drones) {
            if (dron.getNivelBateria() >= 25 && !dron.isEnAlerta() && !dron.isEnPatrullaje()) {
                dronesDisponibles.add(dron);
            }
        }
        System.out.println("Drones disponibles para patrullaje: " + dronesDisponibles.size());
        return dronesDisponibles;
    }

    /*
    realizarPatrullaje(){}
    Simula el patrullaje de todos los drones disponibles por una hora
    Consume 25% de batería a cada dron que patrulla
    Actualiza el estado de alerta de los drones si es necesario
    Retorna la cantidad de drones que patrullaron exitosamente
    */
    public int realizarPatrullaje() {
        List<Dron> dronesDisponibles = obtenerDronesDisponiblesParaPatrullaje();
        int dronesQuePatrullaron = 0;

        for (Dron dron : dronesDisponibles) {
            // Verificar nuevamente que tenga batería suficiente (por si cambió el estado)
            if (dron.getNivelBateria() >= 25) {
                dron.setEnPatrullaje(true);
                // Consumir 25% de batería por hora de patrullaje
                int bateriaActual = dron.getNivelBateria();
                dron.setNivelBateria(bateriaActual - 25);
                // Actualizar horas de vuelo disponibles
                dron.setHorasVueloDisponible(dron.getNivelBateria() / 25);
                // Verificar estado de alerta después del consumo
                dron.setEnAlerta(dron.getNivelBateria() < 20); // Umbral por defecto

                dron.setEnPatrullaje(false);
                dronesQuePatrullaron++;

                System.out.println("Dron " + dron.getIdProcesador() + " completó patrullaje. Batería restante: " + dron.getNivelBateria() + "%");
            }
        }

        System.out.println("Patrullaje completado: " + dronesQuePatrullaron + " drones patrullaron exitosamente");
        return dronesQuePatrullaron;
    }

    /*
    detectarAnomalias(){}
    Simula la detección de anomalías en los drones que están patrullando
    Cada dron en patrullaje tiene una probabilidad de detectar una anomalía
    Retorna lista de reportes de anomalías generados
    */
    public List<ReporteAnomalia> detectarAnomalias() {
        List<ReporteAnomalia> reportes = new ArrayList<>();
        Random random = new Random();

        for (Dron dron : drones) {
            if (dron.isEnPatrullaje()) {
                // 30% de probabilidad de detectar una anomalía durante el patrullaje
                if (random.nextDouble() < 0.3) {
                    ReporteAnomalia reporte = simularDeteccionAnomalia(dron);
                    if (reporte != null) {
                        reportes.add(reporte);
                        // Aquí deberías agregar el reporte al historial del dron
                        System.out.println("Dron " + dron.getIdProcesador() + " detectó anomalía: " + reporte.getEvento());
                    }
                }
            }
        }

        System.out.println("Detección de anomalías: " + reportes.size() + " anomalías detectadas");
        return reportes;
    }


    /*
    recargarDrones(int cantidad){}
    Recarga la batería de todos los drones en una cantidad específica
    No puede exceder el 100%
    Actualiza las horas de vuelo disponibles de cada dron
    */
    public void recargarDrones(int cantidad) {
        int dronesRecargados = 0;

        for (Dron dron : drones) {
            int nuevaBateria = dron.getNivelBateria() + cantidad;
            if (nuevaBateria > 100) {
                dron.setNivelBateria(100);
            } else {
                dron.setNivelBateria(nuevaBateria);
            }

            // Actualizar horas de vuelo disponibles
            dron.setHorasVueloDisponible(dron.getNivelBateria() / 25);

            // Si se recargó por encima del umbral, quitar alerta
            if (dron.getNivelBateria() >= 20) {
                dron.setEnAlerta(false);
            }

            dronesRecargados++;
        }

        System.out.println("Recarga masiva: " + dronesRecargados + " drones recargados con " + cantidad + "% de batería");
    }

    /*
    recargarDronCompleto(String idProcesador){}
    Recarga un dron específico al 100%
    Actualiza sus horas de vuelo a 4
    */
    public boolean recargarDronCompleto(String idProcesador) {
        Dron dron = buscarDron(idProcesador);
        if (dron == null) {
            System.out.println("Error: No se encontró dron con ID " + idProcesador);
            return false;
        }

        dron.setNivelBateria(100);
        dron.setHorasVueloDisponible(4); // 100% / 25% por hora = 4 horas
        dron.setEnAlerta(false);

        System.out.println("Dron " + idProcesador + " recargado al 100%. Horas de vuelo: 4");
        return true;
    }

    /*
    obtenerTotalDrones(){}
    Retorna la cantidad total de drones gestionados
    */
    public int obtenerTotalDrones() {
        return drones.size();
    }

    /*
    getTodosDrones(){}
    Retorna una copia de la lista de todos los drones
    */
    public List<Dron> getTodosDrones() {
        return new ArrayList<>(drones);
    }

    /*
    obtenerDronesPorEdificio(Edificio edificio){}
    Retorna lista de drones que están asignados a patrullar cerca de un edificio específico
    (Asumiendo que cada dron está asignado a un edificio o sector)
    */
    public List<Dron> obtenerDronesPorEdificio(Edificio edificio) {
        List<Dron> dronesDelEdificio = new ArrayList<>();
        // Esta implementación asigna drones a edificios de forma round-robin
        // En una implementación real, tendrías un mapeo específico

        if (edificio == null) {
            return dronesDelEdificio;
        }

        // Simulación: asignar algunos drones al edificio basado en su ID
        String idEdificio = edificio.getId();
        int hash = Math.abs(idEdificio.hashCode());

        for (int i = 0; i < drones.size(); i++) {
            if (i % 3 == hash % 3) { // Asignación simple basada en módulo
                dronesDelEdificio.add(drones.get(i));
            }
        }

        System.out.println("Drones asignados al edificio " + edificio.getId() + ": " + dronesDelEdificio.size());
        return dronesDelEdificio;
    }

    /*
    obtenerDronesConBateriaBaja(int umbral){}
    Retorna lista de drones con batería por debajo del umbral
    */
    public List<Dron> obtenerDronesConBateriaBaja(int umbral) {
        List<Dron> dronesBateriaBaja = new ArrayList<>();
        for (Dron dron : drones) {
            if (dron.getNivelBateria() < umbral) {
                dronesBateriaBaja.add(dron);
            }
        }
        System.out.println("Drones con batería baja (< " + umbral + "%): " + dronesBateriaBaja.size());
        return dronesBateriaBaja;
    }

    /*
    generarReporteEstadisticas(){}
    Genera un reporte estadístico de la flota de drones
    Incluye: total drones, drones activos, drones en alerta, promedio de batería, etc.
    */
    public String generarReporteEstadisticas() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("=== REPORTE ESTADÍSTICO DE DRONES ===\n");

        int totalDrones = drones.size();
        int dronesActivos = obtenerDronesActivos().size();
        int dronesEnAlerta = contarDronesEnAlerta(20);
        int dronesPatrullando = 0;
        int bateriaTotal = 0;

        for (Dron dron : drones) {
            bateriaTotal += dron.getNivelBateria();
            if (dron.isEnPatrullaje()) {
                dronesPatrullando++;
            }
        }

        double promedioBateria = totalDrones > 0 ? (double) bateriaTotal / totalDrones : 0;

        reporte.append("Total de drones: ").append(totalDrones).append("\n");
        reporte.append("Drones activos: ").append(dronesActivos).append("\n");
        reporte.append("Drones en alerta: ").append(dronesEnAlerta).append("\n");
        reporte.append("Drones patrullando: ").append(dronesPatrullando).append("\n");
        reporte.append("Promedio de batería: ").append(String.format("%.2f", promedioBateria)).append("%\n");
        reporte.append("Horas de vuelo totales: ").append(calcularHorasVueloTotales()).append(" horas\n");

        return reporte.toString();
    }

    /*
    simularDeteccionAnomalia(Dron dron){}
    Simula que un dron específico detecta una anomalía durante el patrullaje
    Genera un evento aleatorio y crea un reporte
    Retorna el ReporteAnomalia generado
    */
    public ReporteAnomalia simularDeteccionAnomalia(Dron dron) {
        if (dron == null) {
            return null;
        }

        Random random = new Random();
        Evento[] eventos = Evento.values();
        Evento eventoAleatorio = eventos[random.nextInt(eventos.length)];

        ReporteAnomalia reporte = new ReporteAnomalia(eventoAleatorio, LocalDateTime.now(), dron.getIdProcesador());

        System.out.println("Dron " + dron.getIdProcesador() + " generó reporte: " + eventoAleatorio);
        return reporte;
    }

    private int calcularHorasVueloTotales() {
        int totalHoras = 0;
        for (Dron dron : drones) {
            totalHoras += dron.getHorasVueloDisponible();
        }
        return totalHoras;
    }
}