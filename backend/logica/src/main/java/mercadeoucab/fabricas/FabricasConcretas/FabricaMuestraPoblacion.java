package mercadeoucab.fabricas.FabricasConcretas;

import mercadeoucab.accesodatos.Dao;
import mercadeoucab.accesodatos.DaoMuestraPoblacion;
import mercadeoucab.dtos.DtoBase;
import mercadeoucab.dtos.DtoMuestraPoblacion;
import mercadeoucab.entidades.EntidadBase;
import mercadeoucab.entidades.MuestraPoblacion;
import mercadeoucab.fabricas.FabricaAbstracta;

public class FabricaMuestraPoblacion extends FabricaAbstracta {
    /**
     * Name: generarEntidad
     * Description: Crea una entidad
     *
     * @return EntidadBase
     */
    @Override
    public EntidadBase generarEntidad() {
        return new MuestraPoblacion();
    }

    /**
     * Name: generarDto
     * Description: Genera un Dto
     *
     * @return DtoBase
     */
    @Override
    public DtoBase generarDto() {
        return new DtoMuestraPoblacion();
    }

    /**
     * Name: generarDao
     * Description: retorna la clase Dao
     *
     * @return Dao
     */
    @Override
    public Dao generarDao() {
        return new DaoMuestraPoblacion();
    }
}
