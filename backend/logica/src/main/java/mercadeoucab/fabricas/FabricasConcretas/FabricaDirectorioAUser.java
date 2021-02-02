package mercadeoucab.fabricas.FabricasConcretas;

import mercadeoucab.accesodatos.Dao;
import mercadeoucab.directorioactivo.DirectorioActivo;
import mercadeoucab.dtos.DtoBase;
import mercadeoucab.dtos.DtoDirectorioAUser;
import mercadeoucab.entidades.EntidadBase;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.responses.ResponseBase;

public class FabricaDirectorioAUser extends FabricaAbstracta {
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
     *
     * @return DtoBase
     */
    @Override
    public DtoBase generarDto() {
        return null;
    }

    /**
     * Name: generarDtoDAU
     * Description: retorna la clase DtoDirectorioAUser
     *
     * @return DtoDirectorioAUser
     */
    public DtoDirectorioAUser generarDtoDAU(){
        return new DtoDirectorioAUser();
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

    /**
     * Name: generarDirectorioActivo
     * Description: retorna la clase DirectorioActivo
     * @param rol Rol perteneciente a la unidad organizacional del ldap
     * @return DirectorioActivo
     */
    public DirectorioActivo generarDirectorioActivo(String rol){
        return new DirectorioActivo( rol);
    }

    /**
     * Name: generarResponse
     * Description: genera un Response base
     *
     * @return ResponseBase
     */
    @Override
    public ResponseBase generarResponse() {
        return null;
    }
}
