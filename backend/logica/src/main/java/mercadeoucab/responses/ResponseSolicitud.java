package mercadeoucab.responses;

import mercadeoucab.dtos.DtoPresentacion;
import mercadeoucab.dtos.DtoSolicitud;
import mercadeoucab.dtos.DtoSubCategoria;
import mercadeoucab.dtos.DtoTipo;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonValue;
import java.util.Objects;

public class ResponseSolicitud implements ResponseBase<DtoSolicitud> {

    /**
     * @param dtoSolicitud Objeto que se desea convertir en Json
     * @return se retorna el Json
     */
    @Override
    public JsonObject generate(DtoSolicitud dtoSolicitud) throws Exception {
        JsonObject resultado = Json.createObjectBuilder()
                                    .add("_id", dtoSolicitud.get_id())
                                    .add("estado",dtoSolicitud.getEstado())
                                    .build();
        if ( Objects.nonNull( dtoSolicitud.getUsuario())){
            ResponseUsuario responseUsuario = new ResponseUsuario();
            JsonObject usuario = responseUsuario.generate( dtoSolicitud.getUsuario());
            resultado.put("usuario", usuario);
        }
        if (Objects.nonNull( dtoSolicitud.getTipos())) {
            JsonArrayBuilder tiposList = Json.createArrayBuilder();
            for (DtoTipo tipo : dtoSolicitud.getTipos()) {
                ResponseTipo responseTipo = new ResponseTipo();
                JsonObject objecto = responseTipo.generate(tipo);
                tiposList.add(objecto);
            }
            resultado.put( "tipos", (JsonValue) tiposList);
        }
        if ( Objects.nonNull( dtoSolicitud.getPresentaciones())) {
            JsonArrayBuilder presentacionlist = Json.createArrayBuilder();
            for (DtoPresentacion presentacion : dtoSolicitud.getPresentaciones()) {
                ResponsePresentacion responsePresentacion = new ResponsePresentacion();
                JsonObject objecto = responsePresentacion.generate(presentacion);
                presentacionlist.add(objecto);
            }
            resultado.put("presentaciones", (JsonValue) presentacionlist);
        }
        if ( Objects.nonNull( dtoSolicitud.getSubCategorias())) {
            JsonArrayBuilder subCategoriaslist = Json.createArrayBuilder();
            for (DtoSubCategoria subCategoria : dtoSolicitud.getSubCategorias()) {
                ResponseSubCategoria responseSubCategoria = new ResponseSubCategoria();
                JsonObject objecto = responseSubCategoria.generate(subCategoria);
                subCategoriaslist.add(objecto);
            }
            resultado.put("subcategorias", (JsonValue) subCategoriaslist);
        }
        return resultado;
    }
}
