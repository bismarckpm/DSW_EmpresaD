package mercadeoucab.fabricas.FabricasConcretas;

import mercadeoucab.accesodatos.Dao;
import mercadeoucab.accesodatos.DaoTipo;
import mercadeoucab.dtos.DtoBase;
import mercadeoucab.dtos.DtoTipo;
import mercadeoucab.entidades.EntidadBase;
import mercadeoucab.entidades.Tipo;
import mercadeoucab.fabricas.FabricaAbstracta;

public class FabricaTipo extends FabricaAbstracta {
    /**
     * Name: generarEntidad
     * Description: Crea una entidad
     *
     * @return EntidadBase
     */
    @Override
    public EntidadBase generarEntidad() {
        return new Tipo();
    }

    /**
     * Name: generarDto
     * Description: Genera un Dto
     *
     * @return DtoBase
     */
    @Override
    public DtoBase generarDto() {
        return new DtoTipo();
    }

    /**
     * Name: generarDao
     * Description: retorna la clase Dao
     *
     * @return Dao
     */
    @Override
    public Dao generarDao() {
        return new DaoTipo();
    }
}
