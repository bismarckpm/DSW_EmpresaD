package mercadeoucab.fabricas.FabricasConcretas;

import mercadeoucab.accesodatos.Dao;
import mercadeoucab.accesodatos.DaoEstado;
import mercadeoucab.dtos.DtoBase;
import mercadeoucab.dtos.DtoEstado;
import mercadeoucab.entidades.EntidadBase;
import mercadeoucab.entidades.Estado;
import mercadeoucab.fabricas.FabricaAbstracta;

public class FabricaEstado extends FabricaAbstracta {
    /**
     * Name: generarEntidad
     * Description: Crea una entidad
     *
     * @return EntidadBase
     */
    @Override
    public EntidadBase generarEntidad() {
        return new Estado();
    }

    /**
     * Name: generarDto
     * Description: Genera un Dto
     *
     * @return DtoBase
     */
    @Override
    public DtoBase generarDto() {
        return new DtoEstado();
    }

    /**
     * Name: generarDao
     * Description: retorna la clase Dao
     *
     * @return Dao
     */
    @Override
    public Dao generarDao() {
        return new DaoEstado();
    }
}
