package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoDatoEncuestado;
import mercadeoucab.dtos.DtoDatoEncuestado;
import mercadeoucab.dtos.DtoHijo;
import mercadeoucab.dtos.DtoTelefono;
import mercadeoucab.entidades.*;
import mercadeoucab.mappers.DatoEncuestadoMapper;
import mercadeoucab.responses.ResponseDatoEncuestado;
import mercadeoucab.responses.ResponseGeneral;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Daren Gonzalez
 * @version 1.0
 * @since 2020-12-18
 */
@Path( "/datos_encuestados" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioDatoEncuestado extends AplicacionBase{

    /**
     * Metodo para listar todos los Datos Encuestados registrados
     * @return regresa la lista de las Datos Encuestados o respuesta que no se encontro
     */
    @GET
    @Path("/")
    public Response listarDatosEncuestado(){
        JsonObject data;
        JsonArrayBuilder datoEncuestados = Json.createArrayBuilder();
        Response resultado = null;
        try {
            DaoDatoEncuestado dao = new DaoDatoEncuestado();
            List<DatoEncuestado> datosEncuestadosObtenidos = dao.findAll( DatoEncuestado.class);
            if ( datosEncuestadosObtenidos.size() > 0) {
                for (DatoEncuestado datoEncuestado : datosEncuestadosObtenidos) {
                    if (datoEncuestado.getActivo() != 0) {
                        ResponseDatoEncuestado responseDatoEncuestado = new ResponseDatoEncuestado();
                        DtoDatoEncuestado dtoDatoEncuestado = DatoEncuestadoMapper.mapEntitytoDto(datoEncuestado);
                        JsonObject objeto = responseDatoEncuestado.generate(dtoDatoEncuestado);
                        datoEncuestados.add(objeto);
                    }
                }
                resultado = ResponseGeneral.Succes(datoEncuestados);
            }else{
                resultado = ResponseGeneral.NoData();
            }
        }catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return  resultado;

    }

    /**
     * Metodo para registrar un Dato Encuestado a un usuario con rol encuestado
     * @param dtoDatoEncuestado Objeto que se desea crear
     * @return regresa mensaje de exito en caso de agregarse exitosamente o
     *      *   mensaje de error
     */
    @POST
    @Path("/")
    public Response registrarDatoEncuestado(DtoDatoEncuestado dtoDatoEncuestado){
        Response resultado = null;
        try {
            DaoDatoEncuestado dao = new DaoDatoEncuestado();
            DatoEncuestado datoEncuestado = new DatoEncuestado();
            datoEncuestado.setSegundoNombre(dtoDatoEncuestado.getSegundoNombre());
            datoEncuestado.setSegundoapellido(dtoDatoEncuestado.getSegundoapellido());
            datoEncuestado.setEdad(dtoDatoEncuestado.getEdad());
            datoEncuestado.setGenero(dtoDatoEncuestado.getGenero());
            datoEncuestado.setMedioConexion(dtoDatoEncuestado.getMedioConexion());
            datoEncuestado.setNive_economico(dtoDatoEncuestado.getNive_economico());
            datoEncuestado.setNivelAcademico(dtoDatoEncuestado.getNivelAcademico());
            datoEncuestado.setPersonasHogar(dtoDatoEncuestado.getPersonasHogar());
            datoEncuestado.setCedula(dtoDatoEncuestado.getCedula());
            datoEncuestado.setActivo(1);
            datoEncuestado.setCreado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Parroquia parroquia = new Parroquia(dtoDatoEncuestado.getFk_lugar().get_id());
            datoEncuestado.setFk_lugar(parroquia);
            Usuario usuario = new Usuario(dtoDatoEncuestado.getUsuario().get_id());
            datoEncuestado.setUsuario( usuario );
            Ocupacion ocupacion = new Ocupacion(dtoDatoEncuestado.getOcupacion().get_id());
            datoEncuestado.setOcupacion( ocupacion );
            for ( DtoTelefono telefono: dtoDatoEncuestado.getTelefonos()){
                Telefono paraInsertar = new Telefono();
                paraInsertar.setTelefono( telefono.getTelefono());
                paraInsertar.setActivo( 1);
                paraInsertar.setCreado_el( new Date(Calendar.getInstance().getTime().getTime()));
                datoEncuestado.addTelefono( paraInsertar);
            }
            for (DtoHijo hijo: dtoDatoEncuestado.getHijos()){
                Hijo paraInsertar = new Hijo();
                paraInsertar.setEdad( hijo.getEdad());
                paraInsertar.setGenero( hijo.getGenero());
                paraInsertar.setActivo( 1);
                paraInsertar.setCreado_el( new Date(Calendar.getInstance().getTime().getTime()));
                datoEncuestado.addHijo( paraInsertar);
            }
            DatoEncuestado resul = dao.insert(datoEncuestado);
            resultado = ResponseGeneral.SuccesCreate( resul.get_id());
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return  resultado;
    }

    /**
     * Metodo para consultar un Dato Encuestado dado su identificador
     * @param id Identificador del Dato Encuestado a consultar
     * @return regresa el Dato Encuestado consultado, respuesta que no se encontro
     *      o mensaje de error
     */
    @GET
    @Path("/{id}")
    public Response consultarDatoEncuestado(@PathParam("id") long id){
        Response resultado = null;
        try{
            DaoDatoEncuestado dao = new DaoDatoEncuestado();
            DatoEncuestado resul = dao.find(id, DatoEncuestado.class);
            if ( resul.getActivo() !=0 ){
                ResponseDatoEncuestado responseDatoEncuestado = new ResponseDatoEncuestado();
                DtoDatoEncuestado dtoDatoEncuestado = DatoEncuestadoMapper.mapEntitytoDto( resul);
                JsonObject objeto = responseDatoEncuestado.generate( dtoDatoEncuestado);
                resultado = ResponseGeneral.Succes( objeto);
            }
            else{
                resultado = ResponseGeneral.NoData();
            }
        }catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para actualizar un Dato Encuestado dado su identificador
     * @param id Identificador del Dato Encuestado a actualizar
     * @param dtoDatoEncuestado Objeto que se desea actualizar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/{id}")
    public Response actualizarDatoEncuestado(@PathParam("id") long id, DtoDatoEncuestado dtoDatoEncuestado){
        Response resultado = null;
        try {
            DaoDatoEncuestado dao = new DaoDatoEncuestado();
            DatoEncuestado datoEncuestado = dao.find(id, DatoEncuestado.class);
            datoEncuestado.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            datoEncuestado.setSegundoNombre(dtoDatoEncuestado.getSegundoNombre());
            datoEncuestado.setSegundoapellido(dtoDatoEncuestado.getSegundoapellido());
            datoEncuestado.setEdad(dtoDatoEncuestado.getEdad());
            datoEncuestado.setGenero(dtoDatoEncuestado.getGenero());
            datoEncuestado.setMedioConexion(dtoDatoEncuestado.getMedioConexion());
            datoEncuestado.setNive_economico(dtoDatoEncuestado.getNive_economico());
            datoEncuestado.setNivelAcademico(dtoDatoEncuestado.getNivelAcademico());
            datoEncuestado.setPersonasHogar(dtoDatoEncuestado.getPersonasHogar());
            DatoEncuestado resul = dao.update(datoEncuestado);
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
     * Metodo para eliminar un Dato Encuestado dado su identificador
     * @param id Identificador del Dato Encuestado a eliminar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/eliminar/{id}")
    public Response eliminarDatoEncuestado(@PathParam("id") long id){
        Response resultado = null;
        try {
            DaoDatoEncuestado dao = new DaoDatoEncuestado();
            DatoEncuestado datoEncuestado = dao.find(id, DatoEncuestado.class);
            datoEncuestado.setActivo(0);
            datoEncuestado.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            DatoEncuestado resul = dao.update(datoEncuestado);
            resultado = ResponseGeneral.SuccesMessage();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return  resultado;
    }
}
