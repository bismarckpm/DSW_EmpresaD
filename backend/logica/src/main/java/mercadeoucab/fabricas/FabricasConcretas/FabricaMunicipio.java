package mercadeoucab.fabricas.FabricasConcretas;

import mercadeoucab.accesodatos.Dao;
import mercadeoucab.accesodatos.DaoMunicipio;
import mercadeoucab.dtos.DtoBase;
import mercadeoucab.dtos.DtoMunicipio;
import mercadeoucab.entidades.EntidadBase;
import mercadeoucab.entidades.Municipio;
import mercadeoucab.fabricas.FabricaAbstracta;

public class FabricaMunicipio extends FabricaAbstracta {
    /**
     * Name: generarEntidad
     * Description: Crea una entidad
     *
     * @return EntidadBase
     */
    @Override
    public EntidadBase generarEntidad() {
        return new Municipio();
    }

    /**
     * Name: generarDto
     * Description: Genera un Dto
     *
     * @return DtoBase
     */
    @Override
    public DtoBase generarDto() {
        return new DtoMunicipio();
    }

    /**
     * Name: generarDao
     * Description: retorna la clase Dao
     *
     * @return Dao
     */
    @Override
    public Dao generarDao() {
        return new DaoMunicipio();
    }
}
