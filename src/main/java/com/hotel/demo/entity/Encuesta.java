package com.hotel.demo.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "encuestas")
public class Encuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nombre ;
    @NotBlank
    private String apellidos ;
    @Email
    private String email;
    @Min(value = 18,message = "Se deben tener al menos 18 años")
    private Integer edad;
    @NotBlank
    private String telefono;
    @PastOrPresent(message = "La fecha no puede ser mayor a la fecha de hoy")
    private LocalDate estancia;
    private Boolean piscina;
    private Boolean padel;
    private Boolean restaurante;
    @NotNull
    private String satisfaccion;
    private String comentario;

    public Encuesta() {
    }

    public Encuesta(Long id, String nombre, String apellidos, String email, Integer edad, String telefono, LocalDate estancia, String satisfaccion) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.edad = edad;
        this.telefono = telefono;
        this.estancia = estancia;
        this.satisfaccion = satisfaccion;
    }

    public Encuesta(Long id, String nombre, String apellidos, String email, Integer edad, String telefono, LocalDate estancia, Boolean piscina, Boolean padel, Boolean restaurante, String satisfaccion, String comentario) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.edad = edad;
        this.telefono = telefono;
        this.estancia = estancia;
        this.piscina = piscina;
        this.padel = padel;
        this.restaurante = restaurante;
        this.satisfaccion = satisfaccion;
        this.comentario = comentario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getEstancia() {
        return estancia;
    }

    public void setEstancia(LocalDate estancia) {
        this.estancia = estancia;
    }

    public Boolean getPiscina() {
        return piscina;
    }

    public void setPiscina(Boolean piscina) {
        this.piscina = piscina;
    }

    public Boolean getPadel() {
        return padel;
    }

    public void setPadel(Boolean padel) {
        this.padel = padel;
    }

    public Boolean getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Boolean restaurante) {
        this.restaurante = restaurante;
    }

    public String getSatisfaccion() {
        return satisfaccion;
    }

    public void setSatisfaccion(String satisfaccion) {
        this.satisfaccion = satisfaccion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public String toString() {
        return "Encuesta{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", email='" + email + '\'' +
                ", edad=" + edad +
                ", telefono='" + telefono + '\'' +
                ", estancia=" + estancia +
                ", piscina=" + piscina +
                ", padel=" + padel +
                ", restaurante=" + restaurante +
                ", satisfaccion='" + satisfaccion + '\'' +
                ", comentario='" + comentario + '\'' +
                '}';
    }
}
