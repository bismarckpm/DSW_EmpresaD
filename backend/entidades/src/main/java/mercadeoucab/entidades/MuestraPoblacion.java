package mercadeoucab.entidades;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "muestra_poblacion")
public class MuestraPoblacion extends EntidadBase{

    @Column(name = "genero")
    private String genero;

    @Column(name = "nivel_economico")
    private  int nivelEconomico;

    @Column(name = "nivel_academico")
    private String nivelAcademico;

    @Column(name = "rango_edad_inicio")
    private int rangoEdadInicio;

    @Column(name = "rango_edad_fin")
    private int rangoEdadFin;

    @Column(name = "cantidad_hijos")
    private int cantidadHijos;

    @ManyToOne
    @JoinColumn( name = "fk_lugar")
    private Parroquia fk_lugar;

    public MuestraPoblacion(){}
    public MuestraPoblacion(long id){super(id);}

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getNivelEconomico() {
        return nivelEconomico;
    }

    public void setNivelEconomico(int nivelEconomico) {
        this.nivelEconomico = nivelEconomico;
    }

    public String getNivelAcademico() {
        return nivelAcademico;
    }

    public void setNivelAcademico(String nivelAcademico) {
        this.nivelAcademico = nivelAcademico;
    }

    public int getRangoEdadInicio() {
        return rangoEdadInicio;
    }

    public void setRangoEdadInicio(int rangoEdadInicio) {
        this.rangoEdadInicio = rangoEdadInicio;
    }

    public int getRangoEdadFin() {
        return rangoEdadFin;
    }

    public void setRangoEdadFin(int rangoEdadFin) {
        this.rangoEdadFin = rangoEdadFin;
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
}
