package mercadeoucab.responses;

import mercadeoucab.dtos.DtoDatoEncuestado;
import mercadeoucab.dtos.DtoHijo;
import mercadeoucab.dtos.DtoTelefono;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.time.LocalDate;
import java.time.Period;

public class ResponseDatoEncuestado implements ResponseBase<DtoDatoEncuestado> {

    private  final FabricaAbstracta fabricaOcupacion = FabricaAbstracta.getFactory(Fabricas.OCUPACION);
    private  final FabricaAbstracta fabricaUsuario = FabricaAbstracta.getFactory(Fabricas.USUARIO);
    private  final FabricaAbstracta fabricaParroquia = FabricaAbstracta.getFactory(Fabricas.PARROQUIA);
    private  final FabricaAbstracta fabricaTelefono = FabricaAbstracta.getFactory(Fabricas.TELEFONO);
    private  final FabricaAbstracta fabricaHijo = FabricaAbstracta.getFactory(Fabricas.HIJO);

    /**
     * Metodo para generar el Json de la clase DtoDatoEncuestado
     * @param dtoDatoEncuestado Objeto Dato encuestado que se desea convertir
     * @return regresa un json del dtoDatoEncuestado
     */
    @Override
    public JsonObject generate(DtoDatoEncuestado dtoDatoEncuestado){
        JsonObject data;
        LocalDate ahora = LocalDate.now();
        JsonArrayBuilder listaTelefonos = Json.createArrayBuilder();
        JsonArrayBuilder listaHijos = Json.createArrayBuilder();
        ResponseOcupacion responseOcupacion = (ResponseOcupacion) fabricaOcupacion.generarResponse();
        JsonObject objetoOcupacion = responseOcupacion.generate( dtoDatoEncuestado.getOcupacion());
        ResponseUsuario responseUsuario = (ResponseUsuario) fabricaUsuario.generarResponse();
        JsonObject objetoUsuario = responseUsuario.generate( dtoDatoEncuestado.getUsuario());
        ResponseParroquia responseParroquia = (ResponseParroquia) fabricaParroquia.generarResponse();
        JsonObject objetoParroquia = responseParroquia.generate(dtoDatoEncuestado.getFk_lugar());
        Period periodo = Period.between( dtoDatoEncuestado.getEdad().toLocalDate(),ahora);
        for (DtoTelefono telefono: dtoDatoEncuestado.getTelefonos()){
            ResponseTelefono responseTelefono = (ResponseTelefono) fabricaTelefono.generarResponse();
            JsonObject objetoTelefono = responseTelefono.generate( telefono);
            listaTelefonos.add(objetoTelefono);
        }
        for (DtoHijo hijo: dtoDatoEncuestado.getHijos()){
            ResponseHijo responseHijo = (ResponseHijo) fabricaHijo.generarResponse();
            JsonObject objetoHijo = responseHijo.generate( hijo);
            listaHijos.add(objetoHijo);
        }
        if ( (dtoDatoEncuestado.getSegundoapellido() != null) && (dtoDatoEncuestado.getSegundoNombre() != null)){
            data = Json.createObjectBuilder()
                    .add("_id", dtoDatoEncuestado.get_id())
                    .add("segundoNombre", dtoDatoEncuestado.getSegundoNombre())
                    .add("segundoApellido", dtoDatoEncuestado.getSegundoapellido())
                    .add("cedula", dtoDatoEncuestado.getCedula())
                    .add("medioConexion", dtoDatoEncuestado.getMedioConexion())
                    .add("edad", periodo.getYears())
                    .add("genero", dtoDatoEncuestado.getGenero())
                    .add("nivelEconomico", dtoDatoEncuestado.getNive_economico())
                    .add("nivelAcademico", dtoDatoEncuestado.getNivelAcademico())
                    .add("personasHogar", dtoDatoEncuestado.getPersonasHogar())
                    .add("ocupacion", objetoOcupacion)
                    .add("parroquia", objetoParroquia)
                    .add("usuario", objetoUsuario)
                    .add("hijos", listaHijos)
                    .add("telefonos", listaTelefonos)
                    .build();
        }else if ( dtoDatoEncuestado.getSegundoapellido() != null){
            data = Json.createObjectBuilder()
                    .add("_id", dtoDatoEncuestado.get_id())
                    .add("segundoApellido", dtoDatoEncuestado.getSegundoapellido())
                    .add("cedula", dtoDatoEncuestado.getCedula())
                    .add("medioConexion", dtoDatoEncuestado.getMedioConexion())
                    .add("edad", periodo.getYears())
                    .add("genero", dtoDatoEncuestado.getGenero())
                    .add("nivelEconomico", dtoDatoEncuestado.getNive_economico())
                    .add("nivelAcademico", dtoDatoEncuestado.getNivelAcademico())
                    .add("personasHogar", dtoDatoEncuestado.getPersonasHogar())
                    .add("ocupacion", objetoOcupacion)
                    .add("parroquia", objetoParroquia)
                    .add("usuario", objetoUsuario)
                    .add("hijos", listaHijos)
                    .add("telefonos", listaTelefonos)
                    .build();
        }else{
            data = Json.createObjectBuilder()
                    .add("_id", dtoDatoEncuestado.get_id())
                    .add("segundoNombre", dtoDatoEncuestado.getSegundoNombre())
                    .add("cedula", dtoDatoEncuestado.getCedula())
                    .add("medioConexion", dtoDatoEncuestado.getMedioConexion())
                    .add("edad", periodo.getYears())
                    .add("genero", dtoDatoEncuestado.getGenero())
                    .add("nivelEconomico", dtoDatoEncuestado.getNive_economico())
                    .add("nivelAcademico", dtoDatoEncuestado.getNivelAcademico())
                    .add("personasHogar", dtoDatoEncuestado.getPersonasHogar())
                    .add("ocupacion", objetoOcupacion)
                    .add("parroquia", objetoParroquia)
                    .add("usuario", objetoUsuario)
                    .add("hijos", listaHijos)
                    .add("telefonos", listaTelefonos)
                    .build();
        }
        return data;
    }
}
