package mercadeoucab.comandos.Categoria;

import mercadeoucab.accesodatos.DaoCategoria;
import mercadeoucab.comandos.ComandoAbstracto;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoCategoria;
import mercadeoucab.entidades.Categoria;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.mappers.CategoriaMapper;
import mercadeoucab.responses.ResponseCategoria;
import mercadeoucab.responses.ResponseGeneral;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Objects;

public class ComandoListaReversa extends ComandoAbstracto implements ComandoBase {

    private Response result;
    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try {
            JsonArrayBuilder categoriasList = Json.createArrayBuilder();
            FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.CATEGORIA);
            DaoCategoria dao = (DaoCategoria) fabrica.generarDao();
            List<Categoria> categorias = dao.findAll(Categoria.class);
            if (Objects.nonNull(categorias) && categorias.size() > 0) {
                for (Categoria categoria : categorias) {
                    ResponseCategoria responseCategoria = (ResponseCategoria) fabrica.generarResponse();
                    if (categoria.getActivo() == 1) {
                        DtoCategoria dtoCategoria = CategoriaMapper.mapEntitytoDto(categoria);
                        JsonObject objeto = responseCategoria.generateReverse(dtoCategoria);
                        categoriasList.add(objeto);
                    }
                }
                this.result = ResponseGeneral.Succes(categoriasList);
            }else{
                this.result = ResponseGeneral.NoData();
            }
        }
        catch (Exception e){
            this.result = ResponseGeneral.Failure("Ha ocurrido un error al listar las categorias");
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
