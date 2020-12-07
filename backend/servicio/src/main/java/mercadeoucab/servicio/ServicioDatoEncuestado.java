package mercadeoucab.servicio;

import mercadeoucab.accesodatos.DaoDatoEncuestado;
import mercadeoucab.accesodatos.DaoUsuario;
import mercadeoucab.dtos.DtoDatoEncuestado;
import mercadeoucab.dtos.DtoParroquia;
import mercadeoucab.dtos.DtoUsuario;
import mercadeoucab.entidades.DatoEncuestado;
import mercadeoucab.entidades.Parroquia;
import mercadeoucab.entidades.Usuario;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Path( "/datos_encuestados" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class ServicioDatoEncuestado extends AplicacionBase{
    
    @GET
    @Path("/")
    public List<DatoEncuestado> listarDatosEncuestado(){
        DaoDatoEncuestado dao = new DaoDatoEncuestado();
        return dao.findAll(DatoEncuestado.class);
    }

    @POST
    @Path("/")
    public DatoEncuestado registrarDatoEncuestado(DtoDatoEncuestado dtoDatoEncuestado){
        DatoEncuestado resultado = new DatoEncuestado();
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
            resultado = dao.insert(datoEncuestado);
        }
        catch (Exception e){
            String problema = e.getMessage();
            System.out.println(problema + "MAL INSERT");
        }
        return  resultado;
    }

    @GET
    @Path("/{id}")
    public DatoEncuestado consultarDatoEncuestado(@PathParam("id") long id){
        DaoDatoEncuestado dao = new DaoDatoEncuestado();
        return dao.find(id, DatoEncuestado.class);
    }

    @PUT
    @Path("/{id}")
    public DatoEncuestado actualizarDatoEncuestado(@PathParam("id") long id, DtoDatoEncuestado dtoDatoEncuestado){
        DatoEncuestado resultado = new DatoEncuestado();
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
            resultado = dao.update(datoEncuestado);
        }
        catch (Exception e){
            String problema = e.getMessage();
            System.out.println(problema + "*************");
        }
        return resultado;
    }

    @PUT
    @Path("/eliminar/{id}")
    public DatoEncuestado eliminarDatoEncuestado(@PathParam("id") long id){
        DatoEncuestado resultado = new DatoEncuestado();
        try {
            DaoDatoEncuestado dao = new DaoDatoEncuestado();
            DatoEncuestado datoEncuestado = dao.find(id, DatoEncuestado.class);
            datoEncuestado.setActivo(0);
            datoEncuestado.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            resultado = dao.update(datoEncuestado);
        }
        catch (Exception e){
            String problema = e.getMessage();
        }
        return  resultado;
    }
}
