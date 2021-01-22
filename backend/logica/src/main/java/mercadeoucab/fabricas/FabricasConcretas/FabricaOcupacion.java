package mercadeoucab.fabricas.FabricasConcretas;

import mercadeoucab.accesodatos.Dao;
import mercadeoucab.accesodatos.DaoOcupacion;
import mercadeoucab.dtos.DtoBase;
import mercadeoucab.dtos.DtoOcupacion;
import mercadeoucab.entidades.EntidadBase;
import mercadeoucab.entidades.Ocupacion;
import mercadeoucab.fabricas.FabricaAbstracta;

public class FabricaOcupacion extends FabricaAbstracta {
    /**
     * Name: generarEntidad
     * Description: Crea una entidad
     *
     * @return EntidadBase
     */
    @Override
    public EntidadBase generarEntidad() {
        return new Ocupacion();
    }

    /**
     * Name: generarDto
     * Description: Genera un Dto
     *
     * @return DtoBase
     */
    @Override
    public DtoBase generarDto() {
        return new DtoOcupacion();
    }

    /**
     * Name: generarDao
     * Description: retorna la clase Dao
     *
     * @return Dao
     */
    @Override
    public Dao generarDao() {
        return new DaoOcupacion();
    }
}
