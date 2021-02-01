package mercadeoucab.comandos.Usuario;

import mercadeoucab.accesodatos.DaoUsuario;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoUsuario;
import mercadeoucab.entidades.Usuario;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.responses.ResponseGeneral;

import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;

/**
 *
 * @author Oscar Marquez
 * @version 1.0
 * @since 2021-01-29
 */
public class ComandoActualizarUsuario implements ComandoBase {

    private Response result;
    private DtoUsuario dtoUsuario;
    private long id;
    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try{
            FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.USUARIO);
            DaoUsuario dao = (DaoUsuario) fabrica.generarDao();
            Usuario usuario = dao.find( id, Usuario.class);
            usuario.setNombre( dtoUsuario.getNombre());
            usuario.setApellido( dtoUsuario.getApellido());
            usuario.setEstado( dtoUsuario.getEstado());
            usuario.setModificado_el(
                    new Date(Calendar
                            .getInstance()
                            .getTime()
                            .getTime()));
            Usuario resul = dao.update( usuario);
            this.result = ResponseGeneral.SuccesMessage();
        }catch (Exception e){
            this.result = ResponseGeneral.Failure("Ha ocurrido un error");
        }
    }

    @Override
    public Response getResult() {
        return this.result;
    }

    public void setDtoUsuario(DtoUsuario dtoUsuario) {
        this.dtoUsuario = dtoUsuario;
    }

    public void setId(long id) {
        this.id = id;
    }
}
