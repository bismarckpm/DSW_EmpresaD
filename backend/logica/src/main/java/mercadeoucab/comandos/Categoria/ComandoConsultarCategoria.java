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

import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import java.util.Objects;

public class ComandoConsultarCategoria extends ComandoAbstracto implements ComandoBase {
    private Response resultado;
    private long id;
    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {

        try {
            FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.CATEGORIA);
            DaoCategoria dao = (DaoCategoria) fabrica.generarDao();
            Categoria resul = dao.find(id, Categoria.class);
            ResponseCategoria responseCategoria = (ResponseCategoria) fabrica.generarResponse();
            DtoCategoria dtoCategoria = CategoriaMapper.mapEntitytoDto( resul);
            if (Objects.nonNull( dtoCategoria)){
                JsonObject categoria = responseCategoria.generate( dtoCategoria);
                resultado = ResponseGeneral.Succes( categoria);
            }else{
                resultado = ResponseGeneral.NoData();
            }
        }
        catch (Exception e){
            this.resultado = ResponseGeneral.Failure( "Ha ocurrido un error");
        }
    }

    /**
     * Name: getResult
     *
     * @return Response
     */
    @Override
    public Response getResult() { return this.resultado; }

    public void setId(long id) { this.id = id; }
}
