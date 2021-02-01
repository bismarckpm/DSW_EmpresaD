package mercadeoucab.comandos.subcategoria;

import mercadeoucab.accesodatos.DaoSubCategoria;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoSubCategoria;
import mercadeoucab.entidades.SubCategoria;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.mappers.SubCategoriaMapper;
import mercadeoucab.responses.ResponseGeneral;
import mercadeoucab.responses.ResponseSubCategoria;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import java.util.List;

public class ComandoListarSubcategorias implements ComandoBase {
    private Response result;

    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try {
            FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.SUBCATEGORIA);
            JsonArrayBuilder subcategoriasList = Json.createArrayBuilder();
            DaoSubCategoria dao = (DaoSubCategoria) fabrica.generarDao();
            List<SubCategoria> subCategorias = dao.findAll(SubCategoria.class);
            ResponseSubCategoria responseSubCategoria = (ResponseSubCategoria) fabrica.generarResponse();
            if ( subCategorias.size() > 0) {
                for (SubCategoria subCategoria : subCategorias) {
                    if (subCategoria.getActivo() == 1) {
                        DtoSubCategoria dtoSubCategoria = SubCategoriaMapper.mapEntityToDto(subCategoria);
                        JsonObject objeto = responseSubCategoria.generate(dtoSubCategoria);
                        subcategoriasList.add(objeto);
                    }
                }
                this.result = ResponseGeneral.Succes( subcategoriasList);
            }else{
                this.result = ResponseGeneral.NoData();
            }
        }
        catch (Exception e){
            this.result = ResponseGeneral.Failure("Ha ocurrido un error al listar las subcategorias");
        }
    }

    /**
     * Name: getResult
     *
     * @return Response
     */
    @Override
    public Response getResult() { return this.result; }
}
