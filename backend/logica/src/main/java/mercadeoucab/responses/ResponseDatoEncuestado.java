package mercadeoucab.responses;

import mercadeoucab.dtos.*;
import mercadeoucab.entidades.*;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.time.LocalDate;
import java.time.Period;

public class ResponseDatoEncuestado {

    public static JsonObject generate(DtoDatoEncuestado dtoDatoEncuestado){
        JsonObject data;
        LocalDate ahora = LocalDate.now();
        JsonArrayBuilder listaTelefonos = Json.createArrayBuilder();
        JsonArrayBuilder listaHijos = Json.createArrayBuilder();
        for (DtoTelefono telefono: dtoDatoEncuestado.getTelefonos()){
            JsonObject objetoTelefono = Json.createObjectBuilder()
                    .add("_id", telefono.get_id())
                    .add("telefono", telefono.getTelefono())
                    .build();
            listaTelefonos.add(objetoTelefono);
        }
        for (DtoHijo hijo: dtoDatoEncuestado.getHijos()){
            Period periodoHijo = Period.between( hijo.getEdad().toLocalDate(),ahora);
            JsonObject objetoHijo = Json.createObjectBuilder()
                    .add("_id", hijo.get_id())
                    .add("genero", hijo.getGenero())
                    .add("edad", periodoHijo.getYears())
                    .build();
            listaHijos.add(objetoHijo);
        }
        DtoOcupacion ocupacion = dtoDatoEncuestado.getOcupacion();
        JsonObject objetoOcupacion = Json.createObjectBuilder()
                .add("_id", ocupacion.get_id())
                .add("nombre", ocupacion.getNombre())
                .build();
        DtoUsuario usuario = dtoDatoEncuestado.getUsuario();
        JsonObject objetoUsuario = Json.createObjectBuilder()
                .add("_id", usuario.get_id())
                .add("nombre", usuario.getNombre())
                .add("apellido", usuario.getApellido())
                .add("rol", usuario.getRol())
                .add("estado", usuario.getEstado())
                .add("correo", usuario.getCorreo())
                .build();
        DtoParroquia parroquia = dtoDatoEncuestado.getFk_lugar();
        DtoMunicipio municipio = parroquia.getFk_municipio();
        DtoEstado estado = municipio.getFk_estado();
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
        Period periodo = Period.between( dtoDatoEncuestado.getEdad().toLocalDate(),ahora);
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
        return data;
    }
}
