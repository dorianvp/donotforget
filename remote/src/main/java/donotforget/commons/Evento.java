package donotforget.commons;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Evento implements Serializable {
    private static final long serialVersionUID = 2L;
    private int id_evento;
    private int id_categoria;
    private String titulo;
    private String descripcion;
    private LocalDateTime fecha_inicio;
    private LocalDateTime fecha_finalizacion;
    private LocalTime tiempo_aviso_previo;

    public Evento(int c, String t, String d, LocalDateTime i, LocalDateTime f) {
        this.id_categoria = c;
        this.titulo = t;
        this.descripcion = d;
        this.fecha_inicio = i;
        this.fecha_finalizacion = f;
    }

    public int getId_evento() {
        return id_evento;
    }

    public void setId_evento(int id_evento) {
        this.id_evento = id_evento;
    }

    public int getCategoria() {
        return id_categoria;
    }

    public void setCategoria(int id) {
        this.id_categoria = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaInicio() {
        return fecha_inicio;
    }

    public void setFechaInicio(LocalDateTime fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public LocalDateTime getFechaFinalizacion() {
        return fecha_finalizacion;
    }

    public void setFechaFinalizacion(LocalDateTime fecha_finalizacion) {
        this.fecha_finalizacion = fecha_finalizacion;
    }

    public LocalTime getTiempoAvisoPrevio() {
        return tiempo_aviso_previo;
    }

    public void setTiempoAvisoPrevio(LocalTime tiempo_aviso_previo) {
        this.tiempo_aviso_previo = tiempo_aviso_previo;
    }
}
