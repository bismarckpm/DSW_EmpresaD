package mercadeoucab.responses;

import org.codehaus.jackson.JsonNode;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;

/**
 *
 * @author Oscar Marquez
 * @version 1.0
 * @since 2021-1-22
 */
public class ResponseGeneral {

    /**
     * Metodo para indicar el exito en la consulta de un objeto
     * @param data Objeto que se desea devolver
     * @return regresa respuesta exitosa
     */
    public static Response Succes(JsonObject data){
        JsonObject response = Json.createObjectBuilder()
                .add("status", 200)
                .add("data", data)
                .build();
        return Response.status(Response.Status.OK)
                .entity(response)
                .build();
    }

    /**
     * Metodo para indicar el exito en la consulta de un arreglo
     * de objetos
     * @param data Lista de objetos que se desea devolver
     * @return regresa respuesta exitosa
     */
    public static Response Succes(JsonArrayBuilder data){
        JsonObject response = Json.createObjectBuilder()
                .add("status", 200)
                .add("data", data)
                .build();
        return Response.status(Response.Status.OK)
                .entity(response)
                .build();
    }

    /**
     * Metodo para indicar el exito en la creacion de un objeto
     * @param id Identificador del objeto creado
     * @return regresa respuesta exitosa
     */
    public static Response SuccesCreate(long id){
        JsonObject response = Json.createObjectBuilder()
                .add("status", 200)
                .add("message", "Se ha creado exitosamente")
                .add("_id", id)
                .build();
        return Response.status(Response.Status.OK)
                .entity(response)
                .build();
    }

    /**
     * Metodo para indicar el exito en las operaciones de modificar y eliminar
     * un objeto en la base de datos
     * @return regresa respuesta exitosa
     */
    public static Response SuccesMessage(){
        JsonObject response = Json.createObjectBuilder()
                .add("status", 200)
                .add("message", "Se ha realizado la operacion exitosamente")
                .build();
        return Response.status(Response.Status.OK)
                .entity(response)
                .build();
    }

    /**
     * Metodo para indicar que no se posee registrado la consulta realizada
     * @return regresa respuesta de no se encontro
     */
    public static Response NoData(){
        JsonObject response = Json.createObjectBuilder()
                .add("status", 204)
                .add("message", "No se encuestra registrado lo consultado")
                .build();
        return Response.status(Response.Status.OK)
                .entity(response)
                .build();
    }

    /**
     * Metodo para indicar el fallo en una operacion en el servidor web
     * @param message Mensaje de error que se devuelve en la peticion
     * @return regresa respuesta de error
     */
    public static Response Failure(String message){
        JsonObject response = Json.createObjectBuilder()
                .add("status", 400)
                .add("message", message)
                .build();
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(response)
                .build();
    }



}
