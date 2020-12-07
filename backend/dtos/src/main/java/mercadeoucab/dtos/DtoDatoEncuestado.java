package mercadeoucab.dtos;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Date;

public class DtoDatoEncuestado extends DtoBase {


    private String segundoNombre;
    private String segundoapellido;
    private String cedula;
    private String medioConexion;
    private Date edad;
    private String genero;
    private int nive_economico;
    private String nivelAcademico;
    private int personasHogar;
    private DtoParroquia fk_lugar;
    private DtoUsuario usuario;

    public DtoDatoEncuestado(){}
    public DtoDatoEncuestado(long id)throws Exception{super(id);}

    public DtoDatoEncuestado(String segundoNombre, String segundoapellido, String cedula, String medioConexion, Date edad, String genero, int nive_economico, String nivelAcademico, int personasHogar) {
        this.segundoNombre = segundoNombre;
        this.segundoapellido = segundoapellido;
        this.cedula = cedula;
        this.medioConexion = medioConexion;
        this.edad = edad;
        this.genero = genero;
        this.nive_economico = nive_economico;
        this.nivelAcademico = nivelAcademico;
        this.personasHogar = personasHogar;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getSegundoapellido() {
        return segundoapellido;
    }

    public void setSegundoapellido(String segundoapellido) {
        this.segundoapellido = segundoapellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getMedioConexion() {
        return medioConexion;
    }

    public void setMedioConexion(String medioConexion) {
        this.medioConexion = medioConexion;
    }

    public Date getEdad() {
        return edad;
    }

    public void setEdad(Date edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getNive_economico() {
        return nive_economico;
    }

    public void setNive_economico(int nive_economico) {
        this.nive_economico = nive_economico;
    }

    public String getNivelAcademico() {
        return nivelAcademico;
    }

    public void setNivelAcademico(String nivelAcademico) {
        this.nivelAcademico = nivelAcademico;
    }

    public int getPersonasHogar() {
        return personasHogar;
    }

    public void setPersonasHogar(int personasHogar) {
        this.personasHogar = personasHogar;
    }

    public DtoParroquia getFk_lugar() {
        return fk_lugar;
    }

    public void setFk_lugar(DtoParroquia fk_lugar) {
        this.fk_lugar = fk_lugar;
    }

    public DtoUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(DtoUsuario usuario) {
        this.usuario = usuario;
    }
}
