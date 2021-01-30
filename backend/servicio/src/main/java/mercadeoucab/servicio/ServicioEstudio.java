package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoEstudio;
import mercadeoucab.accesodatos.DaoRespuesta;
import mercadeoucab.accesodatos.DaoUsuario;
import mercadeoucab.dtos.DtoEstudio;
import mercadeoucab.dtos.DtoPregunta;
import mercadeoucab.dtos.DtoUsuario;
import mercadeoucab.entidades.Estudio;
import mercadeoucab.entidades.Pregunta;
import mercadeoucab.entidades.Solicitud;
import mercadeoucab.entidades.Usuario;
import mercadeoucab.mappers.EstudioMapper;
import mercadeoucab.mappers.UsuarioMapper;
import mercadeoucab.responses.ResponseEstudio;
import mercadeoucab.responses.ResponseGeneral;
import mercadeoucab.responses.ResponseUsuario;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Daren Gonzalez
 * @version 1.0
 * @since 2020-12-18
 */
@Path( "/estudios" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioEstudio {

    /**
     * Metodo para listar todos los Estudios registrados
     * @return regresa la lista de los Estudios, respuesta que no se encontro
     *      o mensaje que ha ocurrido un error
     */
    @GET
    @Path("/")
    public Response listarEstudios(){
        JsonArrayBuilder estudiosList = Json.createArrayBuilder();
        Response resultado = null;
        try {
            DaoEstudio dao = new DaoEstudio();
            List<Estudio> estudios = dao.findAll(Estudio.class);
            if ( !estudios.isEmpty()) {
                for (Estudio estudio : estudios) {

                    if (estudio.getActivo() == 1) {
                        ResponseEstudio responseEstudio = new ResponseEstudio();
                        DtoEstudio dtoEstudio = EstudioMapper.mapEntitytoDto(estudio);
                        JsonObject agregar = responseEstudio.generate(dtoEstudio);
                        estudiosList.add(agregar);
                    }
                }
                resultado = ResponseGeneral.Succes(estudiosList);
            }else{
                resultado = ResponseGeneral.NoData();
            }
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para crear un Estudio
     * @param dtoEstudio Objeto que se desea crear
     * @return regresa mensaje de exito en caso de agregarse exitosamente o
     *   mensaje de error
     */
    @POST
    @Path("/")
    public Response agregarEstudio(DtoEstudio dtoEstudio){
        Response resultado = null;
        try {
            DaoEstudio dao = new DaoEstudio();
            Estudio estudio = new Estudio();
            estudio.setEstado(dtoEstudio.getEstado());
            estudio.setTipo(dtoEstudio.getTipo());
            estudio.setEncuestasEsperadas(dtoEstudio.getEncuestasEsperadas());
            estudio.setActivo(1);
            estudio.setCreado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Solicitud solicitud = new Solicitud(dtoEstudio.getSolicitud().get_id());
            estudio.setSolicitud( solicitud );
            Usuario usuario = new Usuario(dtoEstudio.getFk_usuario().get_id());
            estudio.setFk_usuario( usuario );
            for(DtoPregunta pregunta: dtoEstudio.getPreguntas()){
                Pregunta pregunta1 = new Pregunta(pregunta.get_id());
                estudio.addpregunta(pregunta1);
            }

            Estudio resul = dao.insert(estudio);
            resultado = ResponseGeneral.SuccesCreate( resul.get_id());
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para consultar un Estudio dado un identificador
     * @param id Identificador del Estudio que se desea consultar
     * @return regresa el Estudio consultado, tambien en caso de no existir
     *      respuesta que no se encontro o mensaje que ha ocurrido un error
     */
    @GET
    @Path("/{id}")
    public Response consultarEstudio(@PathParam("id") long id){
        JsonObject estudioJson;
        Response resultado = null;
        try {

            DaoEstudio dao = new DaoEstudio();
            Estudio estudio = dao.find(id, Estudio.class);
            if ( Objects.nonNull( estudio) && estudio.getActivo() == 1){
                ResponseEstudio responseEstudio = new ResponseEstudio();
                DtoEstudio dtoEstudio = EstudioMapper.mapEntitytoDto( estudio);
                estudioJson = responseEstudio.generate( dtoEstudio);
                resultado = ResponseGeneral.Succes( estudioJson);
            }else {
                resultado = ResponseGeneral.NoData();
            }
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para eliminar un Estudio dado un identificador
     * @param id Identificador del Estudio que se desea eliminar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/{id}/eliminar")
    public Response eliminarEstudio(@PathParam("id") long id){
        Response resultado = null;
        try {
            DaoEstudio dao = new DaoEstudio();
            Estudio estudio = dao.find(id, Estudio.class);
            estudio.setActivo(0);
            estudio.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Estudio resul = dao.update( estudio );
            resultado = ResponseGeneral.SuccesMessage();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return  resultado;
    }

    /**
     * Metodo para actualizar un Estudio dado un identificador
     * @param id Identificador del Estudio que se desea actualizar
     * @param dtoEstudio Objeto que se desea actualizar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/{id}")
    public Response actualizarEstudio(@PathParam("id") long id, DtoEstudio dtoEstudio){
        Response resultado = null;
        try {
            DaoEstudio dao = new DaoEstudio();
            Estudio estudio = dao.find(id, Estudio.class);
            estudio.setEstado(dtoEstudio.getEstado());
            estudio.setTipo(dtoEstudio.getTipo());
            estudio.setEncuestasEsperadas(dtoEstudio.getEncuestasEsperadas());
            estudio.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Estudio resul = dao.update(estudio);
            resultado = ResponseGeneral.SuccesMessage();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para listar los usuarios que han respondido a la encuesta
     * de un estudio
     * @param id Identificador del Estudio
     * @return devuelve la lista de usuarios o
     *      un mensaje que no se encontro
     */
    @GET
    @Path("/{id}/usuarios_respondieron")
    public Response usuariosRespondieronEncuesta(@PathParam("id") long id){
        JsonArrayBuilder usuariosList = Json.createArrayBuilder();
        Response resultado = null;
        try {
            DaoRespuesta daoRespuesta = new DaoRespuesta();
            DaoEstudio daoEstudio = new DaoEstudio();
            DaoUsuario daoUsuario = new DaoUsuario();
            ResponseUsuario responseUsuario = new ResponseUsuario();
            List<Long> ids = daoRespuesta.usuariosRespondidoEncuesta(daoEstudio.find(id, Estudio.class));
            if(!(ids.isEmpty())){
                for( int i = 0; i < ids.size(); i++){

                    Usuario add = daoUsuario.find(ids.get(i),Usuario.class);
                    DtoUsuario dtoUsuario = UsuarioMapper.mapEntityToDto( add);
                    JsonObject agregar = responseUsuario.generate( dtoUsuario);
                    usuariosList.add(agregar);
                }
                resultado = ResponseGeneral.Succes( usuariosList);
            }
            else{
                resultado = ResponseGeneral.NoData();
            }
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para listar los usuarios que han califican a responder
     *      la encuesta de un estudio
     * @param id Identificador del Estudio
     * @return devuelve la lista de usuarios o
     *      un mensaje que no se encontro
     */
    @GET
    @Path("/{id}/usuarios_aplican")
    public Response usuariosAplicanEncuesta(@PathParam("id") long id){
        JsonArrayBuilder usuariosList = Json.createArrayBuilder();
        Response resultado = null;
        try {
            DaoEstudio daoEstudio = new DaoEstudio();
            ResponseUsuario responseUsuario = new ResponseUsuario();
            List<Usuario> usuarios = daoEstudio.personasAplicanEstudio(daoEstudio.find(id, Estudio.class));
            if (!(usuarios.isEmpty())){
                for(Usuario usuario: usuarios){
                    if(usuario.getActivo() == 1) {
                        DtoUsuario dtoUsuario = UsuarioMapper.mapEntityToDto( usuario);
                        JsonObject agregar = responseUsuario.generate( dtoUsuario);
                        usuariosList.add(agregar);
                    }
                }
                resultado = ResponseGeneral.Succes( usuariosList);
            }
            else{
                resultado = ResponseGeneral.NoData();
            }
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }
}
