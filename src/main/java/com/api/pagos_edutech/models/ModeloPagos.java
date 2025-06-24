package com.api.pagos_edutech.models;


import jakarta.persistence.*;

@Entity
@Table(name="Pagos")
public class ModeloPagos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Nombres;
    private String Apellidos;
    private String Correo;
    private String Celular;
    private Double Precio;
    private String MetodoPago;
    private String fechaPago;
    private String Curso;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String nombres) {
        Nombres = nombres;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getCelular() {
        return Celular;
    }

    public void setCelular(String celular) {
        Celular = celular;
    }

    public Double getPrecio() {
        return Precio;
    }

    public void setPrecio(Double precio) {
        Precio = precio;
    }

    public String getMetodoPago() {
        return MetodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        MetodoPago = metodoPago;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getCurso() {
        return Curso;
    }

    public void setCurso(String curso) {
        Curso = curso;
    }
}
