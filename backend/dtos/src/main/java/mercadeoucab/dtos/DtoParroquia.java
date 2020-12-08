package mercadeoucab.dtos;

import java.util.List;

public class DtoParroquia extends DtoBase{

    private String nombre;
    private int valor_socio_economico;
    private DtoMunicipio fk_municipio;

    public DtoParroquia(long id) throws Exception { super(id); }

    public DtoParroquia() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getValor_socio_economico() {
        return valor_socio_economico;
    }

    public void setValor_socio_economico(int valor_socio_economico) {
        this.valor_socio_economico = valor_socio_economico;
    }

    public DtoMunicipio getFk_municipio() {
        return fk_municipio;
    }

    public void setFk_municipio(DtoMunicipio fk_municipio) {
        this.fk_municipio = fk_municipio;
    }
}
