package mercadeoucab.comandos.usuario;

import mercadeoucab.accesodatos.DaoUsuario;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoMail;
import mercadeoucab.dtos.DtoUsuario;
import mercadeoucab.entidades.Usuario;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.fabricas.FabricasConcretas.FabricaMail;
import mercadeoucab.mail.Mail;
import mercadeoucab.responses.ResponseGeneral;

import javax.ws.rs.core.Response;

/**
 *
 * @author Oscar Marquez
 * @version 1.0
 * @since 2021-01-29
 */
public class ComandoPeticionClaveOlvidada implements ComandoBase {

    private Response result;
    private DtoUsuario dtoUsuario;
    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try {
            FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.USUARIO);
            DaoUsuario dao = (DaoUsuario) fabrica.generarDao();
            Usuario usuario = dao.obtenerUsuarioPorCorreo(
                    dtoUsuario.getCorreo()
            );
            if ( usuario == null){
                this.result = ResponseGeneral.Failure("Usuario no se encuentra registrado");
            }else {
                FabricaMail fabricaMail = (FabricaMail) FabricaAbstracta.getFactory( Fabricas.MAIL);
                Mail enviarCorreo = fabricaMail.generarMail();
                DtoMail dtoMail = fabricaMail.generarDtoMail();
                dtoMail.emailResetearContrasena(
                        "http://localhost:4200/change-password?correo="+ usuario.getCorreo()
                );
                enviarCorreo.enviarCorreo(
                        usuario.getCorreo(),
                        dtoMail.getMensaje(),
                        dtoMail.getAsunto()
                );
                this.result = ResponseGeneral.SuccesMessage();
            }
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
}
