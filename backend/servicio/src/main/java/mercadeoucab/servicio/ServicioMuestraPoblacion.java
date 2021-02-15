package mercadeoucab.servicio;

import mercadeoucab.comandos.MuestraPoblacion.*;
import mercadeoucab.dtos.DtoMuestraPoblacion;
import mercadeoucab.entidades.MuestraPoblacion;
import mercadeoucab.fabricas.Enums.Comandos;
import mercadeoucab.fabricas.FabricaComandosAbstractos;
import mercadeoucab.fabricas.fabricasComandoConcretos.FabricaComandosMuestraPoblacion;
import mercadeoucab.responses.ResponseGeneral;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 *
 * @author Daren Gonzalez
 * @version 1.0
 * @since 2020-12-18
 */
@Path( "/muestrasPoblaciones" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioMuestraPoblacion extends AplicacionBase{

    private final FabricaComandosMuestraPoblacion fabricaComandosMuestraPoblacion = (FabricaComandosMuestraPoblacion) FabricaComandosAbstractos.getFactory(Comandos.MUESTRAPOBLACION);

    @GET
    @Path("/")
    public List<MuestraPoblacion> listarMuestrasPoblaciones(@HeaderParam("Authorization") String token){
        List<MuestraPoblacion> muestraPoblacions = null;
        try {
            validateToken(token);
            ComandoListarMuestraPoblacion comandoListarMuestraPoblacion = (ComandoListarMuestraPoblacion) fabricaComandosMuestraPoblacion.comandoListar();
            comandoListarMuestraPoblacion.execute();
            muestraPoblacions = comandoListarMuestraPoblacion.getMuestraPoblacionList();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
        }
        return muestraPoblacions;
    }

    /**
     * Metodo para crear una Muestra Poblacion
     * @param dtoMuestraPoblacion Objeto que se desea crear
     * @return regresa mensaje de exito en caso de agregarse exitosamente o
     *   mensaje de error
     */
    @POST
    @Path("/")
    public  Response registrarMuestraPoblacion(@HeaderParam("Authorization") String token, DtoMuestraPoblacion dtoMuestraPoblacion){
        Response resultado = null;
        try {
            validateToken(token);
            verifyParams(dtoMuestraPoblacion);
            ComandoRegistrarMuestraPoblacion comandoRegistrarMuestraPoblacion = (ComandoRegistrarMuestraPoblacion) fabricaComandosMuestraPoblacion.comandoCrear();
            comandoRegistrarMuestraPoblacion.setDtoMuestraPoblacion(dtoMuestraPoblacion);
            comandoRegistrarMuestraPoblacion.execute();
            resultado = comandoRegistrarMuestraPoblacion.getResult();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    @GET
    @Path("/{id}")
    public MuestraPoblacion consultarMuestraPoblacion(@HeaderParam("Authorization") String token, @PathParam("id") long id){
        MuestraPoblacion muestraPoblacion = null;
        try {
            validateToken(token);
            verifyParams(id);
            ComandoConsultarMuestraPoblacion comandoConsultarMuestraPoblacion = (ComandoConsultarMuestraPoblacion) fabricaComandosMuestraPoblacion.comandoConsultar();
            comandoConsultarMuestraPoblacion.setId(id);
            comandoConsultarMuestraPoblacion.execute();
            muestraPoblacion = comandoConsultarMuestraPoblacion.getMuestraPoblacion();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
        }
        return muestraPoblacion;
    }

    /**
     * Metodo para eliminar una Muestra Poblacion dado un identificador
     * @param id Identificador de la Muestra Poblacion que se desea eliminar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/eliminar/{id}")
    public Response eliminarMuestraPoblacion(@HeaderParam("Authorization") String token, @PathParam("id") long id){
        Response resultado = null;
        try {
            verifyParams(id);
            validateToken(token);
            ComandoEliminarMuestraPoblacion comandoEliminarMuestraPoblacion = (ComandoEliminarMuestraPoblacion) fabricaComandosMuestraPoblacion.comandoEliminar();
            comandoEliminarMuestraPoblacion.setId(id);
            comandoEliminarMuestraPoblacion.execute();
            resultado = comandoEliminarMuestraPoblacion.getResult();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

    /**
     * Metodo para actualizar una Muestra Poblacion dado un identificador
     * @param id Identificador de la Muestra Poblacion que se desea actualizar
     * @param dtoMuestraPoblacion Objeto que se desea actualizar
     * @return regresa mensaje de exito o mensaje que ha ocurrido un error
     */
    @PUT
    @Path("/{id}")
    public Response actualizarMuestraPoblacion(@HeaderParam("Authorization") String token, @PathParam("id") long id, DtoMuestraPoblacion dtoMuestraPoblacion){
        Response resultado = null;
        try {
            validateToken(token);
            verifyParams(id);
            verifyParams(dtoMuestraPoblacion);
            ComandoActualizarMuestraPoblacion comandoActualizarMuestraPoblacion = (ComandoActualizarMuestraPoblacion) fabricaComandosMuestraPoblacion.comandoModificar();
            comandoActualizarMuestraPoblacion.setId(id);
            comandoActualizarMuestraPoblacion.setDtoMuestraPoblacion(dtoMuestraPoblacion);
            comandoActualizarMuestraPoblacion.execute();
            resultado = comandoActualizarMuestraPoblacion.getResult();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            resultado = ResponseGeneral.Failure("Ha ocurrido un error");
        }
        return resultado;
    }

}
