package mercadeoucab.dtos;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

public class DtoPresentacion extends DtoBase{

    private String cantidad;
    private String tipo;

    public DtoPresentacion(long id)throws Exception { super(id); }
    public DtoPresentacion(){}

    public String getCantidad() { return cantidad; }
    public void setCantidad(String cantidad) { this.cantidad = cantidad; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }



}
