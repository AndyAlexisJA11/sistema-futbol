package com.andyjimenez.sistemafutbol.season;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "temporada")
public class Temporada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTemporada;

    @NotBlank
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @NotNull
    @Min(2000)
    @Column(name = "anio", nullable = false)
    private Integer anio;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 20)
    private EstadoTemporada estado;

    public Temporada(){}

    public Long getIdTemporada(){
        return idTemporada;
    }

    public void setIdTemporada(Long idTemporada){
        this.idTemporada = idTemporada;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public Integer getAnio(){
        return anio;
    }

    public void  setAnio(Integer anio){
        this.anio = anio;
    }

    public EstadoTemporada getEstado(){
        return estado;
    }

    public void setEstado (EstadoTemporada estado){
        this.estado = estado;
    }
}
