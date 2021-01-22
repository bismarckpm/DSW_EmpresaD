package mercadeoucab.fabricas.FabricasConcretas;

import mercadeoucab.accesodatos.Dao;
import mercadeoucab.accesodatos.DaoPais;
import mercadeoucab.dtos.DtoBase;
import mercadeoucab.dtos.DtoPais;
import mercadeoucab.entidades.EntidadBase;
import mercadeoucab.entidades.Pais;
import mercadeoucab.fabricas.FabricaAbstracta;

public class FabricaPais extends FabricaAbstracta {
    /**
     * Name: generarEntidad
     * Description: Crea una entidad
     *
     * @return EntidadBase
     */
    @Override
    public EntidadBase generarEntidad() {
        return new Pais();
    }

    /**
     * Name: generarDto
     * Description: Genera un Dto
     *
     * @return DtoBase
     */
    @Override
    public DtoBase generarDto() {
        return new DtoPais();
    }

    /**
     * Name: generarDao
     * Description: retorna la clase Dao
     *
     * @return Dao
     */
    @Override
    public Dao generarDao() {
        return new DaoPais();
    }
}
