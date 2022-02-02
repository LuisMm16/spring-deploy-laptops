package com.example.obspringejerciciossesiones456.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String marca;
    private Double precio;
    private String procesador;
    private Byte ram;
    private Boolean videoDedicado;
    private Boolean touchScreen;


    //constructores
    public Laptop() {}

    public Laptop(Long id, String marca, Double precio, String procesador, Byte ram, Boolean videoDedicado, Boolean touchScreen) {
        this.id = id;
        this.marca = marca;
        this.precio = precio;
        this.procesador = procesador;
        this.ram = ram;
        this.videoDedicado = videoDedicado;
        this.touchScreen = touchScreen;
    }

    // getter y setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getProcesador() {
        return procesador;
    }

    public void setProcesador(String procesador) {
        this.procesador = procesador;
    }

    public Byte getRam() {
        return ram;
    }

    public void setRam(Byte ram) {
        this.ram = ram;
    }

    public Boolean getVideoDedicado() {
        return videoDedicado;
    }

    public void setVideoDedicado(Boolean videoDedicado) {
        this.videoDedicado = videoDedicado;
    }

    public Boolean getTouchScreen() {
        return touchScreen;
    }

    public void setTouchScreen(Boolean touchScreen) {
        this.touchScreen = touchScreen;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", precio=" + precio +
                ", procesador='" + procesador + '\'' +
                ", ram=" + ram +
                ", videoDedicado=" + videoDedicado +
                ", touchScreen=" + touchScreen +
                '}';
    }
}
