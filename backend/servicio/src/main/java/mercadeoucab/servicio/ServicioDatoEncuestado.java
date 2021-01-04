package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoDatoEncuestado;
import mercadeoucab.accesodatos.DaoUsuario;
import mercadeoucab.dtos.*;
import mercadeoucab.entidades.*;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.List;

@Path( "/datos_encuestados" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioDatoEncuestado extends AplicacionBase{
    
    @GET
    @Path("/")
    public Response listarDatosEncuestado(){
        JsonObject data;
        JsonArrayBuilder datoEncuestados = Json.createArrayBuilder();
        Response resultado = null;
        try {
            DaoDatoEncuestado dao = new DaoDatoEncuestado();
            List<DatoEncuestado> datosEncuestadosObtenidos = dao.findAll( DatoEncuestado.class);
            for (DatoEncuestado datoEncuestado: datosEncuestadosObtenidos){
                if ( datoEncuestado.getActivo() != 0 ){
                    LocalDate ahora = LocalDate.now();
                    JsonArrayBuilder listaTelefonos = Json.createArrayBuilder();
                    JsonArrayBuilder listaHijos = Json.createArrayBuilder();
                    for (Telefono telefono: datoEncuestado.getTelefonos()){
                        JsonObject objetoTelefono = Json.createObjectBuilder()
                                                        .add("_id", telefono.get_id())
                                                        .add("telefono", telefono.getTelefono())
                                                        .build();
                        listaTelefonos.add(objetoTelefono);
                    }
                    for (Hijo hijo: datoEncuestado.getHijos()){
                        Period periodoHijo = Period.between( hijo.getEdad().toLocalDate(),ahora);
                        JsonObject objetoHijo = Json.createObjectBuilder()
                                                    .add("_id", hijo.get_id())
                                                    .add("genero", hijo.getGenero())
                                                    .add("edad", periodoHijo.getYears())
                                                    .build();
                        listaHijos.add(objetoHijo);
                    }
                    Ocupacion ocupacion = datoEncuestado.getOcupacion();
                    JsonObject objetoOcupacion = Json.createObjectBuilder()
                                                        .add("_id", ocupacion.get_id())
                                                        .add("nombre", ocupacion.getNombre())
                                                        .build();
                    Usuario usuario = datoEncuestado.getUsuario();
                    JsonObject objetoUsuario = Json.createObjectBuilder()
                            .add("_id", usuario.get_id())
                            .add("nombre", usuario.getNombre())
                            .add("apellido", usuario.getApellido())
                            .add("rol", usuario.getRol())
                            .add("estado", usuario.getEstado())
                            .add("correo", usuario.getCorreo())
                            .build();
                    Parroquia parroquia = datoEncuestado.getFk_lugar();
                    Municipio municipio = parroquia.getFk_municipio();
                    Estado estado = municipio.getFk_estado();
                    JsonObject objetoPais = Json.createObjectBuilder()
                            .add("_id", estado.getFk_pais().get_id())
                            .add("nombre", estado.getFk_pais().getNombre())
                            .build();
                    JsonObject objetoEstado = Json.createObjectBuilder()
                            .add("_id", estado.get_id())
                            .add("nombre", estado.getNombre())
                            .add("pais", objetoPais)
                            .build();
                    JsonObject objetoMunicipio = Json.createObjectBuilder()
                            .add("_id", municipio.get_id())
                            .add("nombre",municipio.getNombre())
                            .add("estado", objetoEstado)
                            .build();
                    JsonObject objetoParroquia = Json.createObjectBuilder()
                            .add("_id", parroquia.get_id())
                            .add("nombre",parroquia.getNombre())
                            .add("valorSocioEconomico", parroquia.getValor_socio_economico())
                            .add("municipio", objetoMunicipio)
                            .build();
                    Period periodo = Period.between( datoEncuestado.getEdad().toLocalDate(),ahora);
                    JsonObject objeto = Json.createObjectBuilder()
                                            .add("_id", datoEncuestado.get_id())
                                            .add("segundoNombre", datoEncuestado.getSegundoNombre())
                                            .add("segundoApellido", datoEncuestado.getSegundoapellido())
                                            .add("cedula", datoEncuestado.getCedula())
                                            .add("medioConexion", datoEncuestado.getMedioConexion())
                                            .add("edad", periodo.getYears())
                                            .add("genero", datoEncuestado.getGenero())
                                            .add("nivelEconomico", datoEncuestado.getNive_economico())
                                            .add("nivelAcademico", datoEncuestado.getNivelAcademico())
                                            .add("personasHogar", datoEncuestado.getPersonasHogar())
                                            .add("ocupacion", objetoOcupacion)
                                            .add("parroquia", objetoParroquia)
                                            .add("usuario", objetoUsuario)
                                            .add("hijos", listaHijos)
                                            .add("telefonos", listaTelefonos)
                                            .build();
                    datoEncuestados.add( objeto);
                }
            }
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("data", datoEncuestados)
                    .build();
            resultado = Response.status(Response.Status.OK)
                    .entity(data)
                    .build();
        }catch (Exception e) {
            String problema = e.getMessage();
            data = Json.createObjectBuilder()
                    .add("status", 400)
                    .add("message", problema)
                    .build();
            resultado = Response.status(Response.Status.BAD_REQUEST)
                    .entity(data)
                    .build();
        }
        return  resultado;

    }

    @POST
    @Path("/")
    public Response registrarDatoEncuestado(DtoDatoEncuestado dtoDatoEncuestado){
        JsonObject data;
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
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("message", "Agregado exitosamente")
                    .build();
            resultado = Response.status(Response.Status.OK)
                    .entity(data)
                    .build();
        }
        catch (Exception e){
            String problema = e.getMessage();
            data = Json.createObjectBuilder()
                    .add("status", 400)
                    .add("message", problema)
                    .build();
            resultado = Response.status(Response.Status.BAD_REQUEST)
                    .entity(data)
                    .build();
        }
        return  resultado;
    }

    @GET
    @Path("/{id}")
    public Response consultarDatoEncuestado(@PathParam("id") long id){
        JsonObject data;
        Response resultado = null;
        try{
            DaoDatoEncuestado dao = new DaoDatoEncuestado();
            DatoEncuestado resul = dao.find(id, DatoEncuestado.class);
            if ( resul.getActivo() !=0 ){
                LocalDate ahora = LocalDate.now();
                JsonArrayBuilder listaTelefonos = Json.createArrayBuilder();
                JsonArrayBuilder listaHijos = Json.createArrayBuilder();
                for (Telefono telefono: resul.getTelefonos()){
                    JsonObject objetoTelefono = Json.createObjectBuilder()
                            .add("_id", telefono.get_id())
                            .add("telefono", telefono.getTelefono())
                            .build();
                    listaTelefonos.add(objetoTelefono);
                }
                for (Hijo hijo: resul.getHijos()){
                    Period periodoHijo = Period.between( hijo.getEdad().toLocalDate(),ahora);
                    JsonObject objetoHijo = Json.createObjectBuilder()
                            .add("_id", hijo.get_id())
                            .add("genero", hijo.getGenero())
                            .add("edad", periodoHijo.getYears())
                            .build();
                    listaHijos.add(objetoHijo);
                }
                Ocupacion ocupacion = resul.getOcupacion();
                JsonObject objetoOcupacion = Json.createObjectBuilder()
                        .add("_id", ocupacion.get_id())
                        .add("nombre", ocupacion.getNombre())
                        .build();
                Usuario usuario = resul.getUsuario();
                JsonObject objetoUsuario = Json.createObjectBuilder()
                        .add("_id", usuario.get_id())
                        .add("nombre", usuario.getNombre())
                        .add("apellido", usuario.getApellido())
                        .add("rol", usuario.getRol())
                        .add("estado", usuario.getEstado())
                        .add("correo", usuario.getCorreo())
                        .build();
                Parroquia parroquia = resul.getFk_lugar();
                Municipio municipio = parroquia.getFk_municipio();
                Estado estado = municipio.getFk_estado();
                JsonObject objetoPais = Json.createObjectBuilder()
                        .add("_id", estado.getFk_pais().get_id())
                        .add("nombre", estado.getFk_pais().getNombre())
                        .build();
                JsonObject objetoEstado = Json.createObjectBuilder()
                        .add("_id", estado.get_id())
                        .add("nombre", estado.getNombre())
                        .add("pais", objetoPais)
                        .build();
                JsonObject objetoMunicipio = Json.createObjectBuilder()
                        .add("_id", municipio.get_id())
                        .add("nombre",municipio.getNombre())
                        .add("estado", objetoEstado)
                        .build();
                JsonObject objetoParroquia = Json.createObjectBuilder()
                        .add("_id", parroquia.get_id())
                        .add("nombre",parroquia.getNombre())
                        .add("valorSocioEconomico", parroquia.getValor_socio_economico())
                        .add("municipio", objetoMunicipio)
                        .build();
                Period periodo = Period.between( resul.getEdad().toLocalDate(),ahora);
                JsonObject objeto = Json.createObjectBuilder()
                        .add("_id", resul.get_id())
                        .add("segundoNombre", resul.getSegundoNombre())
                        .add("segundoApellido", resul.getSegundoapellido())
                        .add("cedula", resul.getCedula())
                        .add("medioConexion", resul.getMedioConexion())
                        .add("edad", periodo.getYears())
                        .add("genero", resul.getGenero())
                        .add("nivelEconomico", resul.getNive_economico())
                        .add("nivelAcademico", resul.getNivelAcademico())
                        .add("personasHogar", resul.getPersonasHogar())
                        .add("ocupacion", objetoOcupacion)
                        .add("parroquia", objetoParroquia)
                        .add("usuario", objetoUsuario)
                        .add("hijos", listaHijos)
                        .add("telefonos", listaTelefonos)
                        .build();
                data = Json.createObjectBuilder()
                        .add("status", 200)
                        .add("data", objeto)
                        .build();
            }
            else
                {
                data = Json.createObjectBuilder()
                        .add("status", 200)
                        .add("message", "Dato Encuestado no se encuentra activo")
                        .build();
            }
            resultado = Response.status(Response.Status.OK)
                    .entity(data)
                    .build();
        }catch (Exception e) {
            String problema = e.getMessage();
            data = Json.createObjectBuilder()
                    .add("status", 400)
                    .add("message", problema)
                    .build();
            resultado = Response.status(Response.Status.BAD_REQUEST)
                    .entity(data)
                    .build();
        }
        return resultado;
    }

    @PUT
    @Path("/{id}")
    public Response actualizarDatoEncuestado(@PathParam("id") long id, DtoDatoEncuestado dtoDatoEncuestado){
        JsonObject data;
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
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("message", "Actualizado exitosamente")
                    .build();
            resultado = Response.status(Response.Status.OK)
                    .entity(data)
                    .build();
        }
        catch (Exception e){
            String problema = e.getMessage();
            data = Json.createObjectBuilder()
                    .add("status", 400)
                    .add("message", problema)
                    .build();
            resultado = Response.status(Response.Status.BAD_REQUEST)
                    .entity(data)
                    .build();
        }
        return resultado;
    }

    @PUT
    @Path("/eliminar/{id}")
    public Response eliminarDatoEncuestado(@PathParam("id") long id){
        JsonObject data;
        Response resultado = null;
        try {
            DaoDatoEncuestado dao = new DaoDatoEncuestado();
            DatoEncuestado datoEncuestado = dao.find(id, DatoEncuestado.class);
            datoEncuestado.setActivo(0);
            datoEncuestado.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            DatoEncuestado resul = dao.update(datoEncuestado);
            data = Json.createObjectBuilder()
                    .add("status", 200)
                    .add("message", "Eliminado exitosamente")
                    .build();
            resultado = Response.status(Response.Status.OK)
                    .entity(data)
                    .build();
        }
        catch (Exception e){
            String problema = e.getMessage();
            data = Json.createObjectBuilder()
                    .add("status", 400)
                    .add("message",problema)
                    .build();
            resultado = Response.status(Response.Status.BAD_REQUEST)
                    .entity(data)
                    .build();
        }
        return  resultado;
    }
}
