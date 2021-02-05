package mercadeoucab.fabricas.FabricasConcretas;

import mercadeoucab.accesodatos.Dao;
import mercadeoucab.accesodatos.DaoPregunta;
import mercadeoucab.dtos.DtoBase;
import mercadeoucab.dtos.DtoPregunta;
import mercadeoucab.entidades.EntidadBase;
import mercadeoucab.entidades.Pregunta;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.responses.ResponseBase;
import mercadeoucab.responses.ResponsePregunta;

public class FabricaPreguntas extends FabricaAbstracta {
    /**
     * Name: generarEntidad
     * Description: Crea una entidad
     *
     * @return EntidadBase
     */
    @Override
    public EntidadBase generarEntidad() {
        return new Pregunta();
    }

    /**
     * Name: generarDto
     * Description: Genera un Dto
     *
     * @return DtoBase
     */
    @Override
    public DtoBase generarDto() {
        return new DtoPregunta();
    }

    /**
     * Name: generarDao
     * Description: retorna la clase Dao
     *
     * @return Dao
     */
    @Override
    public Dao generarDao() {
        return new DaoPregunta();
    }

    /**
     * Name: generarResponse
     * Description: genera un Response base
     *
     * @return ResponseBase
     */
    @Override
    public ResponseBase generarResponse() {
        return new ResponsePregunta();
    }
}
