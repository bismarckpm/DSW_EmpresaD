package mercadeoucab.comandos.Usuario;

import mercadeoucab.accesodatos.DaoUsuario;
import mercadeoucab.comandos.ComandoAbstracto;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoUsuario;
import mercadeoucab.entidades.Usuario;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.mappers.UsuarioMapper;
import mercadeoucab.responses.ResponseGeneral;
import mercadeoucab.responses.ResponseUsuario;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 *
 * @author Oscar Marquez
 * @version 1.0
 * @since 2021-01-29
 */
public class ComandoListarAnalistas extends ComandoAbstracto implements ComandoBase {
    private Response result;

    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try {
            JsonArrayBuilder usuariosList = Json.createArrayBuilder();
            FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.USUARIO);
            DaoUsuario dao = (DaoUsuario) fabrica.generarDao();
            List<Usuario> usuarios = dao.listarAnalistas();
            ResponseUsuario responseUsuario = (ResponseUsuario) fabrica.generarResponse();
            if( usuarios.size() > 0){
                for(Usuario usuario: usuarios){
                    if(usuario.getActivo() == 1) {
                        DtoUsuario dtoUsuario = UsuarioMapper.mapEntityToDto( usuario);
                        JsonObject objeto = responseUsuario.generate( dtoUsuario);
                        usuariosList.add(objeto);
                    }
                }
                this.result = ResponseGeneral.Succes( usuariosList);
            }
            else{
                this.result = ResponseGeneral.NoData();
            }
        }catch (Exception e){
            this.result = ResponseGeneral.Failure("Ha ocurrido un error");
        }
    }

    @Override
    public Response getResult() {
        return this.result;
    }
}
