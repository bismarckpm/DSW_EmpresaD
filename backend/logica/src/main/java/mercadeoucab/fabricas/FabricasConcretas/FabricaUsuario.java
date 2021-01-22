package mercadeoucab.fabricas.FabricasConcretas;

import mercadeoucab.accesodatos.Dao;
import mercadeoucab.accesodatos.DaoUsuario;
import mercadeoucab.dtos.DtoBase;
import mercadeoucab.dtos.DtoUsuario;
import mercadeoucab.entidades.EntidadBase;
import mercadeoucab.entidades.Usuario;
import mercadeoucab.fabricas.FabricaAbstracta;

public class FabricaUsuario extends FabricaAbstracta {
    /**
     * Name: generarEntidad
     * Description: Crea una entidad
     *
     * @return EntidadBase
     */
    @Override
    public EntidadBase generarEntidad() {
        return new Usuario();
    }

    /**
     * Name: generarDto
     * Description: Genera un Dto
     *
     * @return DtoBase
     */
    @Override
    public DtoBase generarDto() {
        return new DtoUsuario();
    }

    /**
     * Name: generarDao
     * Description: retorna la clase Dao
     *
     * @return Dao
     */
    @Override
    public Dao generarDao() {
        return new DaoUsuario();
    }
}
