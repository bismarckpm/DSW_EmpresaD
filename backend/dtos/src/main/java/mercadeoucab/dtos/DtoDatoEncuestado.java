package mercadeoucab.dtos;


import java.sql.Date;
import java.util.List;

/**
 * Name: DtoDatoEncuestado
 */
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
    private DtoOcupacion ocupacion;
    private List<DtoHijo> hijos;
    private List<DtoTelefono> telefonos;

    /**
     * Name:DtoDatoEncuestado
     * Description: constructor vacio
     */
    public DtoDatoEncuestado(){}

    /**
     * Name: DtoDatoEncuestado
     * Description: Contructor con id
     * @param id
     * @throws Exception
     */
    public DtoDatoEncuestado(long id)throws Exception{super(id);}

    /**
     * Name: DtoDatoEncuestado
     * Description: constructor completo
     * @param segundoNombre
     * @param segundoapellido
     * @param cedula
     * @param medioConexion
     * @param edad
     * @param genero
     * @param nive_economico
     * @param nivelAcademico
     * @param personasHogar
     */
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

    /**
     * Name: getSegundoNombre
     * @return
     */
    public String getSegundoNombre() {
        return segundoNombre;
    }

    /**
     * Name: setSegundoNombre
     * @param segundoNombre
     */
    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    /**
     * Name: getSegundoapellido
     * @return segundoapellido
     */
    public String getSegundoapellido() {
        return segundoapellido;
    }

    /**
     * Name: setSegundoapellido
     * @param segundoapellido
     */
    public void setSegundoapellido(String segundoapellido) {
        this.segundoapellido = segundoapellido;
    }

    /**
     * Name: getCedula
     * @return cedula
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * Name: setCedula
     * @param cedula
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * Name: getMedioConexion
     * @return medioConexion
     */
    public String getMedioConexion() {
        return medioConexion;
    }

    /**
     * Name: setMedioConexion
     * @param medioConexion
     */
    public void setMedioConexion(String medioConexion) {
        this.medioConexion = medioConexion;
    }

    /**
     * Name: getEdad
     * @return edad
     */
    public Date getEdad() {
        return edad;
    }

    /**
     * Name: setEdad
     * @param edad
     */
    public void setEdad(Date edad) {
        this.edad = edad;
    }

    /**
     * Name: getGenero
     * @return genero
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Name: setGenero
     * @param genero
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * Name: getNive_economico
     * @return nive_economico
     */
    public int getNive_economico() {
        return nive_economico;
    }

    /**
     * Name: setNive_economico
     * @param nive_economico
     */
    public void setNive_economico(int nive_economico) {
        this.nive_economico = nive_economico;
    }

    /**
     * Name: getNivelAcademico
     * @return nivelAcademico
     */
    public String getNivelAcademico() {
        return nivelAcademico;
    }

    /**
     * Name: setNivelAcademico
     * @param nivelAcademico
     */
    public void setNivelAcademico(String nivelAcademico) {
        this.nivelAcademico = nivelAcademico;
    }

    /**
     * Name: getPersonasHogar
     * @return personasHogar
     */
    public int getPersonasHogar() {
        return personasHogar;
    }

    /**
     * Name: setPersonasHogar
     * @param personasHogar
     */
    public void setPersonasHogar(int personasHogar) {
        this.personasHogar = personasHogar;
    }

    /**
     * Name: getFk_lugar
     * @return fk_lugar
     */
    public DtoParroquia getFk_lugar() {
        return fk_lugar;
    }

    /**
     * Name:setFk_lugar
     * @param fk_lugar
     */
    public void setFk_lugar(DtoParroquia fk_lugar) {
        this.fk_lugar = fk_lugar;
    }

    /**
     * Name: getUsuario
     * @return usuario
     */
    public DtoUsuario getUsuario() {
        return usuario;
    }

    /**
     * Name: setUsuario
     * @param usuario
     */
    public void setUsuario(DtoUsuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Name: getOcupacion
     * @return ocupacion
     */
    public DtoOcupacion getOcupacion() {
        return ocupacion;
    }

    /**
     * Name: setOcupacion
     * @param ocupacion
     */
    public void setOcupacion(DtoOcupacion ocupacion) {
        this.ocupacion = ocupacion;
    }

    /**
     * Name: getHijos
     * @return hijos
     */
    public List<DtoHijo> getHijos() {
        return hijos;
    }

    /**
     * Name: setHijos
     * @param hijos
     */
    public void setHijos(List<DtoHijo> hijos) {
        this.hijos = hijos;
    }

    /**
     * Name: getTelefonos
     * @return telefonos
     */
    public List<DtoTelefono> getTelefonos() {
        return telefonos;
    }

    /**
     * Name: setTelefonos
     * @param telefonos
     */
    public void setTelefonos(List<DtoTelefono> telefonos) {
        this.telefonos = telefonos;
    }
}
