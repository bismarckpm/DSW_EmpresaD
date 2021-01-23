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
        JsonObject resultado;
        JsonObject usuario;
        ResponseUsuario responseUsuario = new ResponseUsuario();
        usuario = responseUsuario.generate( dtoSolicitud.getUsuario());
        JsonArrayBuilder tiposList = Json.createArrayBuilder();
        if (Objects.nonNull( dtoSolicitud.getTipos())) {
            for (DtoTipo tipo : dtoSolicitud.getTipos()) {
                ResponseTipo responseTipo = new ResponseTipo();
                JsonObject objecto = responseTipo.generate(tipo);
                tiposList.add(objecto);
            }
        }
        JsonArrayBuilder presentacionlist = Json.createArrayBuilder();
        if ( Objects.nonNull( dtoSolicitud.getPresentaciones())) {
            for (DtoPresentacion presentacion : dtoSolicitud.getPresentaciones()) {
                ResponsePresentacion responsePresentacion = new ResponsePresentacion();
                JsonObject objecto = responsePresentacion.generate(presentacion);
                presentacionlist.add(objecto);
            }
        }
        JsonArrayBuilder subCategoriaslist = Json.createArrayBuilder();
        if ( Objects.nonNull( dtoSolicitud.getSubCategorias())) {
            for (DtoSubCategoria subCategoria : dtoSolicitud.getSubCategorias()) {
                ResponseSubCategoria responseSubCategoria = new ResponseSubCategoria();
                JsonObject objecto = responseSubCategoria.generate(subCategoria);
                subCategoriaslist.add(objecto);
            }
        }
        resultado = Json.createObjectBuilder()
                        .add("_id", dtoSolicitud.get_id())
                        .add("estado",dtoSolicitud.getEstado())
                        .add("usuario", usuario)
                        .add("tipos", tiposList)
                        .add("subcategorias", subCategoriaslist)
                        .add("presentaciones", presentacionlist)
                        .build();
        return resultado;
    }
}
