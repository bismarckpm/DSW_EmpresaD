package mercadeoucab.servicio;

import mercadeoucab.comandos.Pais.*;
import mercadeoucab.dtos.DtoPais;
import mercadeoucab.fabricas.Enums.Comandos;
import mercadeoucab.fabricas.FabricaComandosAbstractos;
import mercadeoucab.fabricas.fabricasComandoConcretos.FabricaComandosPais;
import mercadeoucab.responses.ResponseGeneral;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Daren Gonzalez
 * @version 1.0
 * @since 2020-12-18
 */
@Path( "/paises" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioPais extends AplicacionBase{

    private final FabricaComandosPais fabricaComandosPais = (FabricaComandosPais) FabricaComandosAbstractos.getFactory(Comandos.PAIS);

    /**
     * Metodo para listar todos los Paises registrados
     * @return regresa la lista de los estudios, respuesta que no se encontro
     *      o mensaje que ha ocurrido un error
     */
    @GET
    @Path("/")
    public Response listar_paises(@HeaderParam("Authorization") String token){
        Response resultado = null;
        try{
            validateToken(token);
            ComandoListarPaises comandoListarPaises = (ComandoListarPaises) fabricaComandosPais.comandoListar();
            comandoListarPaises.execute();
            resultado = comandoListarPaises.getResult();
        }catch (Exception e) {
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para consultar un Pais dado un identificador
     * @param id Identificador del Pais que se desea consultar
     * @return regresa el Pais consultado, tambien en caso de no existir
     *      respuesta que no se encontro o mensaje que ha ocurrido un error
     */
    @GET
    @Path("/{id}")
    public Response obtenerPais(@HeaderParam("Authorization") String token, @PathParam("id") long id){
        Response resultado = null;
        try{
            validateToken(token);
            verifyParams( id);
            ComandoObtenerPais comandoObtenerPais = (ComandoObtenerPais) fabricaComandosPais.comandoConsultar();
            comandoObtenerPais.setId( id);
            comandoObtenerPais.execute();
            resultado = comandoObtenerPais.getResult();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para crear un Pais
     * @param dtoPais Objeto que se desea crear
     * @return regresa mensaje de exito en caso de agregarse exitosamente o
     *   mensaje de error
     */
    @POST
    @Path("/")
    public Response agregarPais(@HeaderParam("Authorization") String token, DtoPais dtoPais){
        Response resultado = null;
        try{
            validateToken(token);
            verifyParams( dtoPais);
            ComandoAgregarPais comandoAgregarPais = (ComandoAgregarPais) fabricaComandosPais.comandoCrear();
            comandoAgregarPais.setDtoPais( dtoPais);
            comandoAgregarPais.execute();
            resultado = comandoAgregarPais.getResult();
        }
        catch(Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para actualizar un Pais dado un identificador
     * @param id Identificador del Pais que se desea actualizar
     * @param dtoPais Objeto que se desea actualizar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/{id}")
    public Response actualizarPais(@HeaderParam("Authorization") String token, @PathParam("id") long id,DtoPais dtoPais){
        Response resultado = null;
        try {
            validateToken(token);
            verifyParams( id);
            verifyParams( dtoPais);
            ComandoActualizarPais comandoActualizarPais = (ComandoActualizarPais) fabricaComandosPais.comandoModificar();
            comandoActualizarPais.setDtoPais( dtoPais);
            comandoActualizarPais.setId( id);
            comandoActualizarPais.execute();
            resultado = comandoActualizarPais.getResult();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para eliminar un Pais dado un identificador
     * @param id Identificador del Pais que se desea eliminar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/{id}/eliminar")
    public  Response eliminarPais(@HeaderParam("Authorization") String token, @PathParam("id") long id){
        Response resultado = null;
        try {
            validateToken(token);
            verifyParams( id);
            ComandoEliminarPais comandoEliminarPais = (ComandoEliminarPais) fabricaComandosPais.comandoEliminar();
            comandoEliminarPais.setId( id);
            comandoEliminarPais.execute();
            resultado = comandoEliminarPais.getResult();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

}
