package mercadeoucab.dtos;

import javax.persistence.*;
import java.util.List;

public class DtoLugar  extends DtoBase{

    private String nombre;
    private String tipo;
    private long valor_socio_economico;
    private DtoLugar fk_lugar;
    private List<DtoLugar> fk_lugarList;
    private List<DtoDato_Encuestado> dato_encuestadoList;
    private List<DtoMuestra_Poblacion> muestra_poblacionList;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public long getValor_socio_economico() {
        return valor_socio_economico;
    }

    public void setValor_socio_economico(long valor_socio_economico) {
        this.valor_socio_economico = valor_socio_economico;
    }

    public DtoLugar getFk_lugar() {
        return fk_lugar;
    }

    public void setFk_lugar(DtoLugar fk_lugar) {
        this.fk_lugar = fk_lugar;
    }

    public List<DtoLugar> getFk_lugarList() {
        return fk_lugarList;
    }

    public void setFk_lugarList(List<DtoLugar> fk_lugarList) {
        this.fk_lugarList = fk_lugarList;
    }

    public List<DtoDato_Encuestado> getDato_encuestadoList() {
        return dato_encuestadoList;
    }

    public void setDato_encuestadoList(List<DtoDato_Encuestado> dato_encuestadoList) {
        this.dato_encuestadoList = dato_encuestadoList;
    }

    public List<DtoMuestra_Poblacion> getMuestra_poblacionList() {
        return muestra_poblacionList;
    }

    public void setMuestra_poblacionList(List<DtoMuestra_Poblacion> muestra_poblacionList) {
        this.muestra_poblacionList = muestra_poblacionList;
    }
}
