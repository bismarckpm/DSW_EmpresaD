package mercadeoucab.fabricas.FabricasConcretas;

import mercadeoucab.accesodatos.Dao;
import mercadeoucab.accesodatos.DaoParroquia;
import mercadeoucab.dtos.DtoBase;
import mercadeoucab.dtos.DtoParroquia;
import mercadeoucab.entidades.EntidadBase;
import mercadeoucab.entidades.Parroquia;
import mercadeoucab.fabricas.FabricaAbstracta;

public class FabricaParroquia extends FabricaAbstracta {
    /**
     * Name: generarEntidad
     * Description: Crea una entidad
     *
     * @return EntidadBase
     */
    @Override
    public EntidadBase generarEntidad() {
        return new Parroquia();
    }

    /**
     * Name: generarDto
     * Description: Genera un Dto
     *
     * @return DtoBase
     */
    @Override
    public DtoBase generarDto() {
        return new DtoParroquia();
    }

    /**
     * Name: generarDao
     * Description: retorna la clase Dao
     *
     * @return Dao
     */
    @Override
    public Dao generarDao() {
        return new DaoParroquia();
    }
}
