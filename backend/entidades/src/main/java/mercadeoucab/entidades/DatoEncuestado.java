package mercadeoucab.entidades;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name = "dato_encuestado" )
public class DatoEncuestado extends EntidadBase{

    @Column(name = "segundo_nombre")
    private String segundoNombre;

    @Column(name = "segundo_apellido")
    private String segundoapellido;

    @Column(name = "cedula")
    private String cedula;

    @Column(name = "medio_conexion")
    private String medioConexion;

    @Column(name = "edad")
    private Date edad;

    @Column(name = "genero")
    private String genero;

    @Column(name = "nivel_economico")
    private  int nive_economico;

    @Column(name = "nivel_academico")
    private String nivelAcademico;

    @Column(name = "personas_hogar")
    private  int personasHogar;

    @ManyToOne
    @JoinColumn( name = "fk_lugar")
    private Parroquia fk_lugar;

    @ManyToOne
    @JoinColumn( name = "fk_usuario")
    private Usuario usuario;

    @JoinTable(
            name = "ocupacion_encuestado",
            joinColumns = @JoinColumn(name = "fk_dato_encuestado", nullable = false),
            inverseJoinColumns = @JoinColumn(name="fk_ocupacion", nullable = false))
    @ManyToMany()
    private List<Ocupacion> ocupaciones;

    @OneToMany( mappedBy = "datoEncuestado", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST )
    private List<Telefono> telefonos = new ArrayList<>();

    @OneToMany( mappedBy = "datoEncuestado", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST )
    private List<Hijo> hijos = new ArrayList<>();

    public DatoEncuestado(String segundoNombre, String segundoapellido, String cedula, String medioConexion, Date edad, String genero, int nive_economico, String nivelAcademico, int personasHogar) {
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

    public DatoEncuestado(long id) {
        super(id);
    }

    public DatoEncuestado() {
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

    public Parroquia getFk_lugar() {
        return fk_lugar;
    }

    public void setFk_lugar(Parroquia fk_lugar) {
        this.fk_lugar = fk_lugar;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Ocupacion> getOcupaciones() {
        return ocupaciones;
    }

    public void setOcupaciones(List<Ocupacion> ocupaciones) {
        this.ocupaciones = ocupaciones;
    }

    public List<Telefono> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<Telefono> telefonos) {
        this.telefonos = telefonos;
    }

    public List<Hijo> getHijos() {
        return hijos;
    }

    public void setHijos(List<Hijo> hijos) {
        this.hijos = hijos;
    }

    public void addOcupacion(Ocupacion ocupacion){
        if(this.ocupaciones == null)
            this.ocupaciones = new ArrayList<>();
        this.ocupaciones.add(ocupacion);
    }

    public void addTelefono(Telefono telefono){
        this.telefonos.add(telefono);
    }

    public void addHijo(Hijo hijo){
        this.hijos.add( hijo);
    }

}
