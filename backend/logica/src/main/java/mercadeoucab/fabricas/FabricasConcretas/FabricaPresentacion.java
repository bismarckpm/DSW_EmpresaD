package mercadeoucab.fabricas.FabricasConcretas;

import mercadeoucab.accesodatos.Dao;
import mercadeoucab.accesodatos.DaoPresentacion;
import mercadeoucab.dtos.DtoBase;
import mercadeoucab.dtos.DtoPresentacion;
import mercadeoucab.entidades.EntidadBase;
import mercadeoucab.entidades.Presentacion;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.responses.ResponseBase;
import mercadeoucab.responses.ResponsePresentacion;

public class FabricaPresentacion extends FabricaAbstracta {
    /**
     * Name: generarEntidad
     * Description: Crea una entidad
     *
     * @return EntidadBase
     */
    @Override
    public EntidadBase generarEntidad() {
        return new Presentacion();
    }

    /**
     * Name: generarDto
     * Description: Genera un Dto
     *
     * @return DtoBase
     */
    @Override
    public DtoBase generarDto() {
        return new DtoPresentacion();
    }

    /**
     * Name: generarDao
     * Description: retorna la clase Dao
     *
     * @return Dao
     */
    @Override
    public Dao generarDao() {
        return new DaoPresentacion();
    }

    /**
     * Name: generarResponse
     * Description: genera un Response base
     *
     * @return ResponseBase
     */
    @Override
    public ResponseBase generarResponse() {
        return new ResponsePresentacion();
    }
}
