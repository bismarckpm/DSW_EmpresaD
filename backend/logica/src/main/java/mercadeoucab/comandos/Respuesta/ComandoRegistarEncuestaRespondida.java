package mercadeoucab.comandos.Respuesta;

import mercadeoucab.accesodatos.DaoRespuesta;
import mercadeoucab.comandos.ComandoAbstracto;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoEncuestaEstudio;
import mercadeoucab.dtos.DtoRespuesta;
import mercadeoucab.entidades.EncuestaEstudio;
import mercadeoucab.entidades.Opcion;
import mercadeoucab.entidades.Respuesta;
import mercadeoucab.entidades.Usuario;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.responses.ResponseGeneral;

import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;

public class ComandoRegistarEncuestaRespondida extends ComandoAbstracto implements ComandoBase {

    private Response result;
    private DtoEncuestaEstudio dtoEncuestaEstudio;
    /**
     * Metodo para ejecutar los comandos
     */

    //Al terminar de registrar hay que verificar si se cierra la encuesta
    @Override
    public void execute() {
        try {
            FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.RESPUESTA);
            DaoRespuesta dao = (DaoRespuesta) fabrica.generarDao();
            for(DtoRespuesta dtorespuesta: dtoEncuestaEstudio.getRespuestas()){

                Respuesta respuesta = (Respuesta) fabrica.generarEntidad();
                respuesta.setRespuesta(dtorespuesta.getRespuesta());

                EncuestaEstudio encuestaEstudio = new EncuestaEstudio(dtorespuesta.getDtoEncuestaEstudio().get_id());
                respuesta.setEncuesta_estudio(encuestaEstudio);

                Usuario usuario = new Usuario(dtorespuesta.getDtousuario().get_id());
                respuesta.setFk_usuario(usuario);

                if(dtorespuesta.get_dtoopcion() != null){
                    Opcion opcion = new Opcion(dtorespuesta.get_dtoopcion().get_id());
                    respuesta.setFk_opcion(opcion);
                }
                respuesta.setActivo(1);
                respuesta.setCreado_el(new Date(Calendar
                        .getInstance()
                        .getTime()
                        .getTime()));
                dao.insert(respuesta);

            }
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

    public void setDtoEncuestaEstudio(DtoEncuestaEstudio dtoEncuestaEstudio) { this.dtoEncuestaEstudio = dtoEncuestaEstudio; }
}
