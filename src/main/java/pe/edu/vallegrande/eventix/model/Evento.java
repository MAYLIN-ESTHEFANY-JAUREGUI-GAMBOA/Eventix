package pe.edu.vallegrande.eventix.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Evento {
    private int id;
    private String nombreEvento;
    private String tipoEvento;
    private String modalidad;
    private String servicios;
    private LocalDate fechaEvento;
    private BigDecimal costo;
    private String estado;
    
    public Evento() {
    }
    
    public Evento(int id, String nombreEvento, String tipoEvento, String modalidad, 
                  String servicios, LocalDate fechaEvento, BigDecimal costo, String estado) {
        this.id = id;
        this.nombreEvento = nombreEvento;
        this.tipoEvento = tipoEvento;
        this.modalidad = modalidad;
        this.servicios = servicios;
        this.fechaEvento = fechaEvento;
        this.costo = costo;
        this.estado = estado;
    }
    
    public Evento(String nombreEvento, String tipoEvento, String modalidad, 
                  String servicios, LocalDate fechaEvento, BigDecimal costo, String estado) {
        this.nombreEvento = nombreEvento;
        this.tipoEvento = tipoEvento;
        this.modalidad = modalidad;
        this.servicios = servicios;
        this.fechaEvento = fechaEvento;
        this.costo = costo;
        this.estado = estado;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNombreEvento() {
        return nombreEvento;
    }
    
    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }
    
    public String getTipoEvento() {
        return tipoEvento;
    }
    
    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }
    
    public String getModalidad() {
        return modalidad;
    }
    
    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }
    
    public String getServicios() {
        return servicios;
    }
    
    public void setServicios(String servicios) {
        this.servicios = servicios;
    }
    
    public LocalDate getFechaEvento() {
        return fechaEvento;
    }
    
    public void setFechaEvento(LocalDate fechaEvento) {
        this.fechaEvento = fechaEvento;
    }
    
    public BigDecimal getCosto() {
        return costo;
    }
    
    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString() {
        return "Evento{" +
                "id=" + id +
                ", nombreEvento='" + nombreEvento + '\'' +
                ", tipoEvento='" + tipoEvento + '\'' +
                ", modalidad='" + modalidad + '\'' +
                ", servicios='" + servicios + '\'' +
                ", fechaEvento=" + fechaEvento +
                ", costo=" + costo +
                ", estado='" + estado + '\'' +
                '}';
    }
}
