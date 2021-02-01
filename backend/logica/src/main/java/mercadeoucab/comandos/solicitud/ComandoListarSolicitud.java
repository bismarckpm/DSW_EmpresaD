package mercadeoucab.comandos.solicitud;

import mercadeoucab.accesodatos.DaoSolicitud;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoSolicitud;
import mercadeoucab.entidades.Solicitud;
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

public class ComandoListarSolicitud implements ComandoBase {

    private Response result;
    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try {
            JsonArrayBuilder solicitudesList = Json.createArrayBuilder();
            FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.SOLICITUD);
            DaoSolicitud dao = (DaoSolicitud) fabrica.generarDao();
            List<Solicitud> solicitudes = dao.findAll(Solicitud.class);
            if ( solicitudes.size() > 0) {
                for (Solicitud resul : solicitudes) {
                    if (resul.getActivo() == 1) {
                        ResponseSolicitud responseSolicitud = (ResponseSolicitud) fabrica.generarResponse();
                        DtoSolicitud dtoSolicitud = SolicitudMapper.mapEntityToDto(resul);
                        JsonObject object = responseSolicitud.generate(dtoSolicitud);
                        solicitudesList.add(object);
                    }
                } //final for
                result = ResponseGeneral.Succes( solicitudesList );
            } else{
                result = ResponseGeneral.NoData();
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            this.result = ResponseGeneral.Failure("Ha ocurrido un error al listar las solicitudes");
        }
    }

    /**
     * Name: getResult
     *
     * @return Response
     */
    @Override
    public Response getResult() { return this.result; }
}
