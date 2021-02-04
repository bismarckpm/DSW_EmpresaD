package mercadeoucab.responses;

import mercadeoucab.dtos.DtoPresentacion;
import mercadeoucab.dtos.DtoSolicitud;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.Objects;

public class ResponseSolicitud implements ResponseBase<DtoSolicitud> {

    private  final FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.USUARIO);
    private  final FabricaAbstracta fabrica1 = FabricaAbstracta.getFactory(Fabricas.PRESENTACION);
    private  final FabricaAbstracta fabrica2 = FabricaAbstracta.getFactory(Fabricas.MUESTRAPOBLACION);
    /**
     * @param dtoSolicitud Objeto que se desea convertir en Json
     * @return se retorna el Json
     */
    @Override
    public JsonObject generate(DtoSolicitud dtoSolicitud) throws Exception {
        JsonObject resultado = null;
        JsonObject usuario;
        JsonObject muestraPoblacion;
        try {

            ResponseUsuario responseUsuario = (ResponseUsuario) fabrica.generarResponse();
            usuario = responseUsuario.generate(dtoSolicitud.getUsuario());


            JsonArrayBuilder presentacionlist = Json.createArrayBuilder();

            if (Objects.nonNull(dtoSolicitud.getPresentaciones()) && dtoSolicitud.getPresentaciones().size() > 0) {
                for (DtoPresentacion presentacion : dtoSolicitud.getPresentaciones()) {
                    ResponsePresentacion responsePresentacion = (ResponsePresentacion) fabrica1.generarResponse();
                    JsonObject objeto = responsePresentacion.generate(presentacion);
                    presentacionlist.add(objeto);
                }
            }


            ResponseMuestraPoblacion responseMuestraPoblacion = (ResponseMuestraPoblacion) fabrica2.generarResponse();
            muestraPoblacion = responseMuestraPoblacion.generate(dtoSolicitud.getMuestraPoblacion());


            resultado = Json.createObjectBuilder()
                    .add("_id", dtoSolicitud.get_id())
                    .add("estado", dtoSolicitud.getEstado())
                    .add("usuario", usuario)
                    .add("marca", dtoSolicitud.getMarca())
                    .add("comentarios", dtoSolicitud.getComentarios())
                    .add("presentaciones", presentacionlist)
                    .add("muestraPoblacion", muestraPoblacion)
                    .build();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return resultado;
    }
}
