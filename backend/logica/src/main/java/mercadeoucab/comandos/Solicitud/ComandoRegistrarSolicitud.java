package mercadeoucab.comandos.Solicitud;

import mercadeoucab.accesodatos.DaoSolicitud;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoSolicitud;
import mercadeoucab.entidades.Solicitud;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.mappers.SolicitudMapper;
import mercadeoucab.responses.ResponseGeneral;

import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;

public class ComandoRegistrarSolicitud implements ComandoBase {

    private Response result;
    private DtoSolicitud dtoSolicitud;
    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try{
            FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.SOLICITUD);
            DaoSolicitud dao = (DaoSolicitud) fabrica.generarDao();
            Solicitud solicitud = SolicitudMapper.mapDtoToEntity(dtoSolicitud);
            solicitud.setActivo( 1 );
            solicitud.setCreado_el(
                    new Date(Calendar.getInstance().getTime().getTime())
            );
            Solicitud resul = dao.insert( solicitud );
            result = ResponseGeneral.SuccesCreate( resul.get_id());
        }catch (Exception e) {
            result = ResponseGeneral.Failure("Ha ocurrido un error al registrar la solicitud");
        }
    }

    /**
     * Name: getResult
     *
     * @return Response
     */
    @Override
    public Response getResult() { return this.result; }

    public void setDtoSolicitud(DtoSolicitud dtoSolicitud) { this.dtoSolicitud = dtoSolicitud; }
}
