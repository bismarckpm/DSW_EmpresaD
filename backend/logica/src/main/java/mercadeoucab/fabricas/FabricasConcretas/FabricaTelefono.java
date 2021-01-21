package mercadeoucab.fabricas.FabricasConcretas;

import mercadeoucab.accesodatos.Dao;
import mercadeoucab.accesodatos.DaoTelefono;
import mercadeoucab.dtos.DtoBase;
import mercadeoucab.dtos.DtoTelefono;
import mercadeoucab.entidades.EntidadBase;
import mercadeoucab.entidades.Telefono;
import mercadeoucab.fabricas.FabricaAbstracta;

public class FabricaTelefono extends FabricaAbstracta {
    /**
     * Name: generarEntidad
     * Description: Crea una entidad
     *
     * @return EntidadBase
     */
    @Override
    public EntidadBase generarEntidad() {
        return new Telefono();
    }

    /**
     * Name: generarDto
     * Description: Genera un Dto
     *
     * @return DtoBase
     */
    @Override
    public DtoBase generarDto() {
        return new DtoTelefono();
    }

    /**
     * Name: generarDao
     * Description: retorna la clase Dao
     *
     * @return Dao
     */
    @Override
    public Dao generarDao() {
        return new DaoTelefono();
    }
}
