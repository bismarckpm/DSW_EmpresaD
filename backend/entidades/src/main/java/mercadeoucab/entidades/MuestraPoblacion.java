package mercadeoucab.entidades;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "muestra_poblacion")
public class MuestraPoblacion extends EntidadBase{

    @Column(name = "genero")
    private String genero;

    @Column(name = "nivel_economico")
    private  String nivelEconomico;

    @Column(name = "nivel_academico")
    private String nivelAcademico;

    @Column(name = "rango_edad_inicio")
    private Date rangoEdadInicio;

    @Column(name = "rango_edad_fin")
    private Date rangoEdadFin;

    @Column(name = "cantidad_hijos")
    private int cantidadHijos;

    @ManyToOne
    @JoinColumn( name = "fk_lugar")
    private Parroquia fk_lugar;

    @ManyToOne
    @JoinColumn( name = "fk_ocupacion")
    private Ocupacion fk_ocupacion;


    public MuestraPoblacion(){}
    public MuestraPoblacion(long id){super(id);}

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNivelAcademico() {
        return nivelAcademico;
    }

    public void setNivelAcademico(String nivelAcademico) {
        this.nivelAcademico = nivelAcademico;
    }

    public int getCantidadHijos() {
        return cantidadHijos;
    }

    public void setCantidadHijos(int cantidadHijos) {
        this.cantidadHijos = cantidadHijos;
    }

    public Parroquia getFk_lugar() {
        return fk_lugar;
    }

    public void setFk_lugar(Parroquia fk_lugar) {
        this.fk_lugar = fk_lugar;
    }

    public Ocupacion getFk_ocupacion() {
        return fk_ocupacion;
    }

    public void setFk_ocupacion(Ocupacion fk_ocupacion) {
        this.fk_ocupacion = fk_ocupacion;
    }

    public String getNivelEconomico() {
        return nivelEconomico;
    }

    public void setNivelEconomico(String nivelEconomico) {
        this.nivelEconomico = nivelEconomico;
    }

    public Date getRangoEdadInicio() {
        return rangoEdadInicio;
    }

    public void setRangoEdadInicio(Date rangoEdadInicio) {
        this.rangoEdadInicio = rangoEdadInicio;
    }

    public Date getRangoEdadFin() {
        return rangoEdadFin;
    }

    public void setRangoEdadFin(Date rangoEdadFin) {
        this.rangoEdadFin = rangoEdadFin;
    }
}
