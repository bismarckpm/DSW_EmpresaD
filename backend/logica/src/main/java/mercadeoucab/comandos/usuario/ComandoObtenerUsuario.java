package mercadeoucab.comandos.usuario;

import mercadeoucab.accesodatos.DaoUsuario;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoUsuario;
import mercadeoucab.entidades.Usuario;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.mappers.UsuarioMapper;
import mercadeoucab.responses.ResponseGeneral;
import mercadeoucab.responses.ResponseUsuario;

import javax.json.JsonObject;
import javax.ws.rs.core.Response;

public class ComandoObtenerUsuario implements ComandoBase {

    private Response result;
    private long id;
    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try{
            FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.USUARIO);
            DaoUsuario dao = (DaoUsuario) fabrica.generarDao();
            Usuario resul = dao.find( this.id, Usuario.class);
            ResponseUsuario responseUsuario = new ResponseUsuario();
            DtoUsuario dtoUsuario = UsuarioMapper.mapEntityToDto( resul);
            if (resul.getActivo()!= 0) {
                JsonObject usuario = responseUsuario.generate( dtoUsuario);
                this.result = ResponseGeneral.Succes( usuario);
            }else{
                this.result = ResponseGeneral.NoData();
            }
        }catch (Exception e){
            this.result = ResponseGeneral.Failure( "Ha ocurrido un error");
        }
    }

    @Override
    public Response getResult() {
        return this.result;
    }

    public void setId(long id) {
        this.id = id;
    }
}
