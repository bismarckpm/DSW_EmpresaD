package mercadeoucab.comandos.presentacion;

import mercadeoucab.accesodatos.DaoPresentacion;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoPresentacion;
import mercadeoucab.entidades.Presentacion;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.mappers.PresentacionMapper;
import mercadeoucab.mappers.TipoMapper;
import mercadeoucab.responses.ResponseGeneral;

import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;

public class ComandoRegistrarPresentacion implements ComandoBase {

    private Response result;
    private DtoPresentacion dtoPresentacion;
    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try{
            FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.PRESENTACION);
            DaoPresentacion daoP = (DaoPresentacion) fabrica.generarDao();
            Presentacion presentacion = PresentacionMapper.mapDtoToEntity(dtoPresentacion);
            presentacion.setActivo(1);
            presentacion.setCreado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Presentacion resul = daoP.insert( presentacion);
            result = ResponseGeneral.SuccesCreate( resul.get_id() );
        }
        catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            this.result = ResponseGeneral.Failure("Ha ocurrido un error al registrar la presentacion");
        }
    }

    /**
     * Name: getResult
     *
     * @return Response
     */
    @Override
    public Response getResult() { return this.result; }

    public void setDtoPresentacion(DtoPresentacion dtoPresentacion) { this.dtoPresentacion = dtoPresentacion; }
}
