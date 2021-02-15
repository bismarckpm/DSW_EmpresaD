package mercadeoucab.comandos.MuestraPoblacion;

import mercadeoucab.accesodatos.DaoMuestraPoblacion;
import mercadeoucab.comandos.ComandoAbstracto;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoMuestraPoblacion;
import mercadeoucab.entidades.MuestraPoblacion;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.responses.ResponseGeneral;

import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;

public class ComandoActualizarMuestraPoblacion extends ComandoAbstracto implements ComandoBase {

    private Response result;
    private long id;
    private DtoMuestraPoblacion dtoMuestraPoblacion;
    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try
        {
            FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.MUESTRAPOBLACION);
            DaoMuestraPoblacion dao = (DaoMuestraPoblacion) fabrica.generarDao();
            MuestraPoblacion muestraPoblacion = dao.find(id, MuestraPoblacion.class);
            muestraPoblacion.setCantidadHijos(dtoMuestraPoblacion.getCantidadHijos());
            muestraPoblacion.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            muestraPoblacion.setGenero(dtoMuestraPoblacion.getGenero());
            muestraPoblacion.setNivelAcademico(dtoMuestraPoblacion.getNivelAcademico());
            muestraPoblacion.setNivelEconomico(dtoMuestraPoblacion.getNivelEconomico());
            muestraPoblacion.setRangoEdadInicio(dtoMuestraPoblacion.getRangoEdadInicio());
            muestraPoblacion.setRangoEdadFin(dtoMuestraPoblacion.getRangoEdadFin());
            dao.update(muestraPoblacion);
            result = ResponseGeneral.SuccesMessage();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            result = ResponseGeneral.Failure("Ha ocurrido un error");
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

    public void setDtoMuestraPoblacion(DtoMuestraPoblacion dtoMuestraPoblacion) { this.dtoMuestraPoblacion = dtoMuestraPoblacion; }
}
