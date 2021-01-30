package mercadeoucab.comandos.usuario;

import mercadeoucab.accesodatos.DaoSolicitud;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoSolicitud;
import mercadeoucab.entidades.Solicitud;
import mercadeoucab.entidades.Usuario;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.mappers.SolicitudMapper;
import mercadeoucab.responses.ResponseGeneral;
import mercadeoucab.responses.ResponseSolicitud;

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
public class ComandoSolicitudesCliente implements ComandoBase {
    private Response result;
    private long id;

    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try {
            JsonArrayBuilder solicitudesList = Json.createArrayBuilder();
            FabricaAbstracta fabricaSolicitud = FabricaAbstracta.getFactory( Fabricas.SOLICITUD);
            FabricaAbstracta fabricaUsuario = FabricaAbstracta.getFactory( Fabricas.USUARIO);
            DaoSolicitud dao = (DaoSolicitud) fabricaSolicitud.generarDao();
            Usuario usuario = (Usuario) fabricaUsuario.generarEntidad();
            usuario.set_id( id);
            List<Solicitud> solicitudes = dao.solicitudesCliente(
                    usuario
            );
            ResponseSolicitud responseSolicitud =  (ResponseSolicitud) fabricaSolicitud.generarResponse();
            if(!(solicitudes.isEmpty())){
                for (Solicitud resul: solicitudes){
                    if(resul.getActivo() == 1){
                        DtoSolicitud dtoSolicitud = SolicitudMapper.mapEntityToDto( resul);
                        JsonObject object = responseSolicitud.generate( dtoSolicitud);
                        solicitudesList.add(object);
                    }
                } //final for
                this.result = ResponseGeneral.Succes( solicitudesList);
            }
            else{
                this.result = ResponseGeneral.NoData();
            }
        }catch (Exception e){
            this.result = ResponseGeneral.Failure("ERROR");
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
