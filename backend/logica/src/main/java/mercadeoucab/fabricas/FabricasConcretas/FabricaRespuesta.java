package mercadeoucab.fabricas.FabricasConcretas;

import mercadeoucab.accesodatos.Dao;
import mercadeoucab.accesodatos.DaoRespuesta;
import mercadeoucab.dtos.DtoBase;
import mercadeoucab.dtos.DtoRespuesta;
import mercadeoucab.entidades.EntidadBase;
import mercadeoucab.entidades.Respuesta;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.responses.ResponseBase;
import mercadeoucab.responses.ResponseRespuesta;

public class FabricaRespuesta extends FabricaAbstracta {
    /**
     * Name: generarEntidad
     * Description: Crea una entidad
     *
     * @return EntidadBase
     */
    @Override
    public EntidadBase generarEntidad() {
        return new Respuesta();
    }

    /**
     * Name: generarDto
     * Description: Genera un Dto
     *
     * @return DtoBase
     */
    @Override
    public DtoBase generarDto() {
        return new DtoRespuesta();
    }

    /**
     * Name: generarDao
     * Description: retorna la clase Dao
     *
     * @return Dao
     */
    @Override
    public Dao generarDao() {
        return new DaoRespuesta();
    }

    /**
     * Name: generarResponse
     * Description: genera un Response base
     *
     * @return ResponseBase
     */
    @Override
    public ResponseBase generarResponse() {
        return new ResponseRespuesta();
    }
}
