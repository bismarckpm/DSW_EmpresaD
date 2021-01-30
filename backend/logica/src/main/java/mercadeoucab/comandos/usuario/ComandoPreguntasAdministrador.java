package mercadeoucab.comandos.usuario;

import mercadeoucab.accesodatos.DaoPregunta;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoPregunta;
import mercadeoucab.entidades.Pregunta;
import mercadeoucab.entidades.Usuario;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.mappers.PreguntaMapper;
import mercadeoucab.responses.ResponseGeneral;
import mercadeoucab.responses.ResponsePregunta;

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
public class ComandoPreguntasAdministrador implements ComandoBase {

    private Response result;
    private long id;

    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try{
            FabricaAbstracta fabricaPregunta = FabricaAbstracta.getFactory(Fabricas.PREGUNTA);
            FabricaAbstracta fabricaUsuario = FabricaAbstracta.getFactory(Fabricas.USUARIO);
            DaoPregunta dao = (DaoPregunta) fabricaPregunta.generarDao();
            Usuario usuario = (Usuario) fabricaUsuario.generarEntidad();
            usuario.set_id( id);
            List<Pregunta> preguntas = dao.obtenerPreguntasAdministrador(
                    usuario
            );
            JsonArrayBuilder preguntaslist = Json.createArrayBuilder();
            if(!(preguntas.isEmpty())) {
                for (Pregunta pregunta : preguntas) {

                    if(pregunta.getActivo() == 1) {
                        String tipo = pregunta.getTipo();
                        JsonObject objeto = null;
                        ResponsePregunta responsePregunta = (ResponsePregunta) fabricaPregunta.generarResponse();
                        DtoPregunta dtoPregunta = PreguntaMapper.mapEntityToDto( pregunta);
                        switch (tipo) {
                            case "abierta":
                            case "boolean":
                                objeto = responsePregunta.generate( dtoPregunta);
                                preguntaslist.add(objeto);
                                break;

                            case "multiple":
                            case "simple":
                                objeto = responsePregunta.generateWithOptions( dtoPregunta);
                                preguntaslist.add(objeto);
                                break;
                            case "rango":
                                objeto = responsePregunta.generateWithRango( dtoPregunta);
                                preguntaslist.add(objeto);
                                break;
                        }//final switch
                    }
                }//Final for
                this.result = ResponseGeneral.Succes( preguntaslist);
            }//final if
            else{
                this.result = ResponseGeneral.NoData();
            }
        }catch (Exception e){
            this.result = ResponseGeneral.Failure("Ha ocurrido un error");
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
