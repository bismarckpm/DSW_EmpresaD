package mercadeoucab.comandos.Categoria;

import mercadeoucab.accesodatos.DaoCategoria;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoCategoria;
import mercadeoucab.entidades.Categoria;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.mappers.CategoriaMapper;
import mercadeoucab.responses.ResponseGeneral;

import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;

public class ComandoAgregarCategoria implements ComandoBase {
    private Response result;
    private DtoCategoria dtoCategoria;

    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try{
            FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.CATEGORIA);
            DaoCategoria dao = (DaoCategoria) fabrica.generarDao();
            Categoria categoria = CategoriaMapper.mapDtotoEntity(this.dtoCategoria);
            categoria.setActivo(1);
            categoria.setCreado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Categoria resul = dao.insert(categoria);
            result = ResponseGeneral.SuccesCreate( resul.get_id());
        }
        catch (Exception e){
            this.result = ResponseGeneral.Failure("Ocurrio un error al agregar la categoria");
        }
    }

    /**
     * Name: getResult
     *
     * @return Response
     */
    @Override
    public Response getResult() { return this.result; }

    public void setDtoCategoria(DtoCategoria dtoCategoria) { this.dtoCategoria = dtoCategoria; }
}
