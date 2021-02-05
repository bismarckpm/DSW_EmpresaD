package mercadeoucab.comandos.DatoEncuestado;

import mercadeoucab.accesodatos.DaoDatoEncuestado;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoDatoEncuestado;
import mercadeoucab.entidades.DatoEncuestado;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.responses.ResponseGeneral;

import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;

/**
 *
 * @author Oscar Marquez
 * @version 1.0
 * @since 2021-01-29
 */
public class ComandoActualizarDatoEncuestado implements ComandoBase {
    private Response result;
    private DtoDatoEncuestado dtoDatoEncuestado;
    private long id;

    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try {
            FabricaAbstracta fabricaDatoEncuestado = FabricaAbstracta.getFactory( Fabricas.DATOENCUESTADO);
            DaoDatoEncuestado dao =  (DaoDatoEncuestado) fabricaDatoEncuestado.generarDao();
            DatoEncuestado datoEncuestado = dao.find(id, DatoEncuestado.class);
            datoEncuestado.setModificado_el(
                    new Date(Calendar.getInstance().getTime().getTime())
            );
            datoEncuestado.setSegundoNombre(dtoDatoEncuestado.getSegundoNombre());
            datoEncuestado.setSegundoapellido(dtoDatoEncuestado.getSegundoapellido());
            datoEncuestado.setEdad(dtoDatoEncuestado.getEdad());
            datoEncuestado.setGenero(dtoDatoEncuestado.getGenero());
            datoEncuestado.setMedioConexion(dtoDatoEncuestado.getMedioConexion());
            datoEncuestado.setNive_economico(dtoDatoEncuestado.getNive_economico());
            datoEncuestado.setNivelAcademico(dtoDatoEncuestado.getNivelAcademico());
            datoEncuestado.setPersonasHogar(dtoDatoEncuestado.getPersonasHogar());
            dao.update(datoEncuestado);
            this.result = ResponseGeneral.SuccesMessage();
        }catch (Exception e) {
            this.result = ResponseGeneral.Failure("Error al actualizar el dato de encuestado");
        }
    }

    @Override
    public Response getResult() {
        return this.result;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDtoDatoEncuestado(DtoDatoEncuestado dtoDatoEncuestado) {
        this.dtoDatoEncuestado = dtoDatoEncuestado;
    }
}
