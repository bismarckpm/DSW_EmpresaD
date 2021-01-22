package mercadeoucab.fabricas.FabricasConcretas;

import mercadeoucab.accesodatos.Dao;
import mercadeoucab.accesodatos.DaoMarca;
import mercadeoucab.dtos.DtoBase;
import mercadeoucab.dtos.DtoMarca;
import mercadeoucab.entidades.EntidadBase;
import mercadeoucab.entidades.Marca;
import mercadeoucab.fabricas.FabricaAbstracta;

public class FabricaMarca extends FabricaAbstracta {
    /**
     * Name: generarEntidad
     * Description: Crea una entidad
     *
     * @return EntidadBase
     */
    @Override
    public EntidadBase generarEntidad() {
        return new Marca();
    }

    /**
     * Name: generarDto
     * Description: Genera un Dto
     *
     * @return DtoBase
     */
    @Override
    public DtoBase generarDto() {
        return new DtoMarca();
    }

    /**
     * Name: generarDao
     * Description: retorna la clase Dao
     *
     * @return Dao
     */
    @Override
    public Dao generarDao() {
        return new DaoMarca();
    }
}
