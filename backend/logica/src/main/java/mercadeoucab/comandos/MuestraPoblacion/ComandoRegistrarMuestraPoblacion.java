package mercadeoucab.comandos.MuestraPoblacion;

import mercadeoucab.accesodatos.DaoMuestraPoblacion;
import mercadeoucab.comandos.ComandoAbstracto;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoMuestraPoblacion;
import mercadeoucab.entidades.MuestraPoblacion;
import mercadeoucab.entidades.Ocupacion;
import mercadeoucab.entidades.Parroquia;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.responses.ResponseGeneral;

import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;

public class ComandoRegistrarMuestraPoblacion extends ComandoAbstracto implements ComandoBase {

    private Response result;
    private DtoMuestraPoblacion dtoMuestraPoblacion;
    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try {
            FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.MUESTRAPOBLACION);
            DaoMuestraPoblacion dao = (DaoMuestraPoblacion) fabrica.generarDao();
            MuestraPoblacion muestraPoblacion = new MuestraPoblacion();
            muestraPoblacion.setCantidadHijos(dtoMuestraPoblacion.getCantidadHijos());
            muestraPoblacion.setGenero(dtoMuestraPoblacion.getGenero());
            muestraPoblacion.setNivelAcademico(dtoMuestraPoblacion.getNivelAcademico());
            muestraPoblacion.setNivelEconomico(dtoMuestraPoblacion.getNivelEconomico());
            muestraPoblacion.setRangoEdadInicio(dtoMuestraPoblacion.getRangoEdadInicio());
            muestraPoblacion.setRangoEdadFin(dtoMuestraPoblacion.getRangoEdadFin());
            Parroquia parroquia = new Parroquia(dtoMuestraPoblacion.getFk_lugar().get_id());
            muestraPoblacion.setFk_lugar(parroquia);
            Ocupacion ocupacion = new Ocupacion(dtoMuestraPoblacion.getDtoOcupacion().get_id());
            muestraPoblacion.setFk_ocupacion(ocupacion);
            muestraPoblacion.setCreado_el(new Date(Calendar.getInstance().getTime().getTime()));
            muestraPoblacion.setActivo(1);
            MuestraPoblacion resul = dao.insert(muestraPoblacion);
            result = ResponseGeneral.SuccesCreate( resul.get_id());
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

    public void setDtoMuestraPoblacion(DtoMuestraPoblacion dtoMuestraPoblacion) { this.dtoMuestraPoblacion = dtoMuestraPoblacion; }
}
