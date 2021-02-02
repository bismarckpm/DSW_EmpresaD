package mercadeoucab.comandos.Estudio;

import mercadeoucab.accesodatos.DaoEstudio;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoUsuario;
import mercadeoucab.entidades.Estudio;
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

public class ComandoUsuariosAplicanEncuesta implements ComandoBase {

    private Response result;
    private long id;
    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try {
            FabricaAbstracta fabricaEstudio = FabricaAbstracta.getFactory(Fabricas.ESTUDIO);
            FabricaAbstracta fabricaUsuario = FabricaAbstracta.getFactory(Fabricas.USUARIO);
            JsonArrayBuilder usuariosList = Json.createArrayBuilder();
            DaoEstudio daoEstudio = (DaoEstudio) fabricaEstudio.generarDao();
            ResponseUsuario responseUsuario = (ResponseUsuario) fabricaUsuario.generarResponse();
            List<Usuario> usuarios = daoEstudio.personasAplicanEstudio(daoEstudio.find(id, Estudio.class));
            if (usuarios.size()>0){
                for(Usuario usuario: usuarios){
                    if(usuario.getActivo() == 1) {
                        DtoUsuario dtoUsuario = UsuarioMapper.mapEntityToDto( usuario);
                        JsonObject agregar = responseUsuario.generate( dtoUsuario);
                        usuariosList.add(agregar);
                    }
                }
                result = ResponseGeneral.Succes( usuariosList);
            }
            else{
                result = ResponseGeneral.NoData();
            }
        }
        catch (Exception e){
            result = ResponseGeneral.Failure("Ha ocurrido un error al listar los usuario");
        }
    }

    /**
     * Name: getResult
     *
     * @return Response
     */
    @Override
    public Response getResult() { return this.result; }

    public void setId(long id) { this.id = id; }
}
