package mercadeoucab.responses;

import mercadeoucab.dtos.DtoPresentacion;
import mercadeoucab.dtos.DtoSolicitud;
import mercadeoucab.dtos.DtoSubCategoria;
import mercadeoucab.dtos.DtoTipo;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;

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
        FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.USUARIO);
        ResponseUsuario responseUsuario = (ResponseUsuario) fabrica.generarResponse();
        usuario = responseUsuario.generate( dtoSolicitud.getUsuario());

        FabricaAbstracta fabrica1 = FabricaAbstracta.getFactory(Fabricas.PRESENTACION);
        JsonArrayBuilder presentacionlist = Json.createArrayBuilder();

        if ( Objects.nonNull( dtoSolicitud.getPresentaciones())) {
            for (DtoPresentacion presentacion : dtoSolicitud.getPresentaciones()) {
                ResponsePresentacion responsePresentacion = (ResponsePresentacion) fabrica1.generarResponse();
                JsonObject objeto = responsePresentacion.generate(presentacion);
                presentacionlist.add(objeto);
            }
        }

        resultado = Json.createObjectBuilder()
                        .add("_id", dtoSolicitud.get_id())
                        .add("estado",dtoSolicitud.getEstado())
                        .add("usuario", usuario)
                        .add("marca", dtoSolicitud.getMarca())
                        .add("comentarios", dtoSolicitud.getComentarios())
                        .add("presentaciones", presentacionlist)
                        .build();
        return resultado;
    }
}
