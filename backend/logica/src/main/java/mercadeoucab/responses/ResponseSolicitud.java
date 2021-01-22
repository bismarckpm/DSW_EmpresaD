package mercadeoucab.responses;

import mercadeoucab.dtos.DtoPresentacion;
import mercadeoucab.dtos.DtoSolicitud;
import mercadeoucab.dtos.DtoSubCategoria;
import mercadeoucab.dtos.DtoTipo;
import mercadeoucab.entidades.Presentacion;
import mercadeoucab.entidades.SubCategoria;
import mercadeoucab.entidades.Tipo;
import mercadeoucab.mappers.TipoMapper;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

public class ResponseSolicitud implements ResponseBase<DtoSolicitud> {

    /**
     * @param dtoSolicitud Objeto que se desea convertir en Json
     * @return se retorna el Json
     */
    @Override
    public JsonObject generate(DtoSolicitud dtoSolicitud) throws Exception {
        JsonArrayBuilder tiposList = Json.createArrayBuilder();
        for(DtoTipo tipo: dtoSolicitud.getTipos()){
            ResponseTipo responseTipo = new ResponseTipo();
            JsonObject objecto = responseTipo.generate( tipo);
            tiposList.add(objecto);
        }

        JsonArrayBuilder presentacionlist = Json.createArrayBuilder();
        for(DtoPresentacion presentacion: dtoSolicitud.getPresentaciones()){
            ResponsePresentacion responsePresentacion = new ResponsePresentacion();
            JsonObject objecto = responsePresentacion.generate( presentacion);
            presentacionlist.add(objecto);
        }

        JsonArrayBuilder subCategoriaslist = Json.createArrayBuilder();
        for(DtoSubCategoria subCategoria: dtoSolicitud.getSubCategorias()){
            ResponseSubCategoria responseSubCategoria = new ResponseSubCategoria();
            JsonObject objecto = responseSubCategoria.generate( subCategoria);
            subCategoriaslist.add(objecto);
        }
        ResponseUsuario responseUsuario = new ResponseUsuario();
        JsonObject usuario = responseUsuario.generate( dtoSolicitud.getUsuario());
        return Json.createObjectBuilder()
                .add("_id", dtoSolicitud.get_id())
                .add("estado",dtoSolicitud.getEstado())
                .add("usuario",usuario)
                .add("tipos", tiposList)
                .add("presentaciones", presentacionlist)
                .add("subcategorias", subCategoriaslist)
                .build();
    }
}
