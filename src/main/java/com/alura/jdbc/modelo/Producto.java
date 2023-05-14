package com.alura.jdbc.modelo;

public class Producto {

    // Attributes
    public Integer categoriaId;
    private Integer id;
    private String nombre;
    private String descripcion;
    private Integer cantidad;

    // Constructor
    public Producto(String nombre, String descripcion, Integer cantidad) {
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.nombre = nombre;
    }

    public Producto(Integer id, String nombre, String descripcion, Integer cantidad) {
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.nombre = nombre;
        this.id = id;
    }

    public Producto(Integer id) {
        this.id = id;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public int getCategoriaId() { return this.categoriaId; }

    public Integer getId() {
        return id;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }


    //toString
    @Override
    public String toString() {
        return String.format(
             "{id: %s, nombre: %s, descripcion: %s, cantidad: %s}",
                this.id,
                this.nombre,
                this.descripcion,
                this.cantidad
        );
    }

}
