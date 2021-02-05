package mercadeoucab.responses;

import mercadeoucab.dtos.DtoOpcion;
import mercadeoucab.dtos.DtoPregunta;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

public class ResponsePregunta implements ResponseBase<DtoPregunta> {

    private final  FabricaAbstracta fabricaUsuario = FabricaAbstracta.getFactory(Fabricas.USUARIO);
    private  final  FabricaAbstracta fabricaOpcion = FabricaAbstracta.getFactory(Fabricas.OPCION);

    /**
     * Devuelve el Json de una pregunta abierta, boolean
     * @param dtoPregunta Objeto que se desea convertir en Json
     * @return se retorna el Json
     */
    @Override
    public JsonObject generate(DtoPregunta dtoPregunta) throws Exception {
        ResponseUsuario responseUsuario = (ResponseUsuario) fabricaUsuario.generarResponse();
        JsonObject usuario = responseUsuario.generate(dtoPregunta.getUsuarioDto());

        return Json.createObjectBuilder()
                .add("_id", dtoPregunta.get_id())
                .add("nombre", dtoPregunta.getNombre_pregunta())
                .add("tipo", dtoPregunta.getTipo())
                .add("usuario", usuario)
                .build();
    }

    public JsonObject generateWithOptions( DtoPregunta dtoPregunta) throws Exception{
        ResponseUsuario responseUsuario = (ResponseUsuario) fabricaUsuario.generarResponse();
        JsonObject usuario = responseUsuario.generate(dtoPregunta.getUsuarioDto());
        JsonArrayBuilder opcionesList = Json.createArrayBuilder();
        for(DtoOpcion opcion: dtoPregunta.getOpciones()){
            ResponseOpcion responseOpcion = (ResponseOpcion) fabricaOpcion.generarResponse();
            JsonObject option = responseOpcion.generate( opcion);
            opcionesList.add(option);
        }
        return Json.createObjectBuilder()
                .add("_id", dtoPregunta.get_id())
                .add("nombre", dtoPregunta.getNombre_pregunta())
                .add("tipo", dtoPregunta.getTipo())
                .add("usuario", usuario)
                .add("opciones", opcionesList)
                .build();
    }

    public JsonObject generateWithRango(DtoPregunta dtoPregunta) throws Exception {
        ResponseUsuario responseUsuario = (ResponseUsuario) fabricaUsuario.generarResponse();
        JsonObject usuario = responseUsuario.generate(dtoPregunta.getUsuarioDto());

        return Json.createObjectBuilder()
                .add("_id", dtoPregunta.get_id())
                .add("nombre", dtoPregunta.getNombre_pregunta())
                .add("tipo", dtoPregunta.getTipo())
                .add("rango", dtoPregunta.getRango())
                .add("usuario", usuario)
                .build();
    }
}
