package mercadeoucab.comandos.Estudio;

import mercadeoucab.accesodatos.DaoEstudio;
import mercadeoucab.accesodatos.DaoSolicitud;
import mercadeoucab.comandos.ComandoAbstracto;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoEstudio;
import mercadeoucab.entidades.Estudio;
import mercadeoucab.entidades.Solicitud;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.mappers.EstudioMapper;
import mercadeoucab.responses.ResponseGeneral;

import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;

public class ComandoAgregarEstudio extends ComandoAbstracto implements ComandoBase {

    private Response result;
    private DtoEstudio dtoEstudio;
    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try
        {
            FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.ESTUDIO);
            FabricaAbstracta fabricaSolicitud = FabricaAbstracta.getFactory(Fabricas.SOLICITUD);
            DaoEstudio dao = (DaoEstudio) fabrica.generarDao();
            Estudio estudio = EstudioMapper.mapDtotoEntity(dtoEstudio);
            estudio.setActivo(1);
            estudio.setCreado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Estudio resul = dao.insert(estudio);

            DaoSolicitud daoSolicitud = (DaoSolicitud) fabricaSolicitud.generarDao();
            Solicitud solicitud =  daoSolicitud.find(dtoEstudio.getSolicitud().get_id(), Solicitud.class);
            solicitud.setEstado("aceptada");
            solicitud.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            daoSolicitud.update(solicitud);

            result = ResponseGeneral.SuccesCreate( resul.get_id());
        }
        catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
            result = ResponseGeneral.Failure("Ha ocurrido un error al agregar el estudio");
        }
    }

    /**
     * Name: getResult
     *
     * @return Response
     */
    @Override
    public Response getResult() { return this.result; }

    public void setDtoEstudio(DtoEstudio dtoEstudio) { this.dtoEstudio = dtoEstudio; }
}
