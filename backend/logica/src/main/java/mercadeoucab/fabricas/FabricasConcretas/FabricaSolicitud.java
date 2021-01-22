package mercadeoucab.fabricas.FabricasConcretas;

import mercadeoucab.accesodatos.Dao;
import mercadeoucab.accesodatos.DaoSolicitud;
import mercadeoucab.dtos.DtoBase;
import mercadeoucab.dtos.DtoSolicitud;
import mercadeoucab.entidades.EntidadBase;
import mercadeoucab.entidades.Solicitud;
import mercadeoucab.fabricas.FabricaAbstracta;

public class FabricaSolicitud extends FabricaAbstracta {
    /**
     * Name: generarEntidad
     * Description: Crea una entidad
     *
     * @return EntidadBase
     */
    @Override
    public EntidadBase generarEntidad() {
        return new Solicitud();
    }

    /**
     * Name: generarDto
     * Description: Genera un Dto
     *
     * @return DtoBase
     */
    @Override
    public DtoBase generarDto() {
        return new DtoSolicitud();
    }

    /**
     * Name: generarDao
     * Description: retorna la clase Dao
     *
     * @return Dao
     */
    @Override
    public Dao generarDao() {
        return new DaoSolicitud();
    }
}
