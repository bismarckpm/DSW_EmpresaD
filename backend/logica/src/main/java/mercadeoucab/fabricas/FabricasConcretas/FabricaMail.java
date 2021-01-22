package mercadeoucab.fabricas.FabricasConcretas;

import mercadeoucab.accesodatos.Dao;
import mercadeoucab.dtos.DtoBase;
import mercadeoucab.dtos.DtoMail;
import mercadeoucab.entidades.EntidadBase;
import mercadeoucab.fabricas.FabricaAbstracta;

public class FabricaMail extends FabricaAbstracta {
    /**
     * Name: generarEntidad
     * Description: Crea una entidad
     *
     * @return EntidadBase
     */
    @Override
    public EntidadBase generarEntidad() {
        return null;
    }

    /**
     * Name: generarDto
     * Description: Genera un Dto
     * @return DtoBase
     */
    @Override
    public DtoBase generarDto() {
        return null;
    }

    public DtoMail generarDtoMail(){
        return new DtoMail();
    }

    /**
     * Name: generarDao
     * Description: retorna la clase Dao
     *
     * @return Dao
     */
    @Override
    public Dao generarDao() {
        return null;
    }
}
