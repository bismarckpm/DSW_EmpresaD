package mercadeoucab.comandos.Solicitud;

import mercadeoucab.accesodatos.DaoSolicitud;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoSolicitud;
import mercadeoucab.entidades.Solicitud;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.mappers.SolicitudMapper;
import mercadeoucab.responses.ResponseGeneral;
import mercadeoucab.responses.ResponseSolicitud;

import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import java.util.Objects;

public class ComandoObtenerSolicitud implements ComandoBase {

    private Response result;
    private long id;
    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try{
            FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.SOLICITUD);
            DaoSolicitud dao = (DaoSolicitud) fabrica.generarDao();
            Solicitud resul = dao.find( id, Solicitud.class);
            ResponseSolicitud responseSolicitud = (ResponseSolicitud) fabrica.generarResponse();
            DtoSolicitud dtoSolicitud = SolicitudMapper.mapEntityToDto( resul );
            System.out.println(dtoSolicitud.getPresentaciones().get(0).get_id());
            JsonObject solicitud = responseSolicitud.generate( dtoSolicitud);
            if (Objects.nonNull( dtoSolicitud)){
                result = ResponseGeneral.Succes( solicitud);
            }else{
                result = ResponseGeneral.NoData();
            }
        }catch (Exception e) {
            System.out.println(e);
            result = ResponseGeneral.Failure("Ha ocurrido un error al consultar la solicitud");
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
