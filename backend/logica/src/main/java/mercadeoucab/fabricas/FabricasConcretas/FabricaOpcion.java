package mercadeoucab.fabricas.FabricasConcretas;

import mercadeoucab.accesodatos.Dao;
import mercadeoucab.accesodatos.DaoOpcion;
import mercadeoucab.dtos.DtoBase;
import mercadeoucab.dtos.DtoOpcion;
import mercadeoucab.entidades.EntidadBase;
import mercadeoucab.entidades.Opcion;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.responses.ResponseBase;
import mercadeoucab.responses.ResponseOpcion;

public class FabricaOpcion extends FabricaAbstracta {
    /**
     * Name: generarEntidad
     * Description: Crea una entidad
     *
     * @return EntidadBase
     */
    @Override
    public EntidadBase generarEntidad() {
        return new Opcion();
    }

    /**
     * Name: generarDto
     * Description: Genera un Dto
     *
     * @return DtoBase
     */
    @Override
    public DtoBase generarDto() {
        return new DtoOpcion();
    }

    /**
     * Name: generarDao
     * Description: retorna la clase Dao
     *
     * @return Dao
     */
    @Override
    public Dao generarDao() {
        return new DaoOpcion();
    }

    /**
     * Name: generarResponse
     * Description: genera un Response base
     *
     * @return ResponseBase
     */
    @Override
    public ResponseBase generarResponse() {
        return new ResponseOpcion();
    }
}
