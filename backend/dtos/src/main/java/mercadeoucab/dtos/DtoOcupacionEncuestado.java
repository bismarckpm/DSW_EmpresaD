package mercadeoucab.dtos;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class DtoOcupacionEncuestado extends DtoBase {

    private DtoOcupacion fk_ocupacion;

    private DtoDatoEncuestado fk_dato_encuestado;

    public DtoOcupacionEncuestado(){}
    public DtoOcupacionEncuestado(long id)throws Exception{super(id);}

    public DtoOcupacion getFk_ocupacion() { return fk_ocupacion; }
    public void setFk_ocupacion(DtoOcupacion fk_ocupacion) { this.fk_ocupacion = fk_ocupacion; }

    public DtoDatoEncuestado getFk_dato_encuestado() { return fk_dato_encuestado; }
    public void setFk_dato_encuestado(DtoDatoEncuestado fk_dato_encuestado) { this.fk_dato_encuestado = fk_dato_encuestado; }



}
