package mercadeoucab.fabricas.FabricasConcretas;

import mercadeoucab.accesodatos.Dao;
import mercadeoucab.dtos.DtoBase;
import mercadeoucab.dtos.DtoEncuestaEstudio;
import mercadeoucab.entidades.EncuestaEstudio;
import mercadeoucab.entidades.EntidadBase;
import mercadeoucab.fabricas.FabricaAbstracta;

public class FabricaEncuestaEstudio extends FabricaAbstracta {
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

    public EncuestaEstudio generarEntidad2(){
        return new EncuestaEstudio();
    }

    /**
     * Name: generarDto
     * Description: Genera un Dto
     *
     * @return DtoBase
     */
    @Override
    public DtoBase generarDto() {
        return new DtoEncuestaEstudio();
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
