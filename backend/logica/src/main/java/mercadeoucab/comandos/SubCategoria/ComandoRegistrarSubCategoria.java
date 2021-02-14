package mercadeoucab.comandos.SubCategoria;

import mercadeoucab.accesodatos.DaoSubCategoria;
import mercadeoucab.comandos.ComandoAbstracto;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoSubCategoria;
import mercadeoucab.entidades.SubCategoria;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.mappers.SubCategoriaMapper;
import mercadeoucab.responses.ResponseGeneral;

import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;

public class ComandoRegistrarSubCategoria extends ComandoAbstracto implements ComandoBase {
    private Response result;
    private DtoSubCategoria dtoSubCategoria;
    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try{
            FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.SUBCATEGORIA);
            DaoSubCategoria dao = (DaoSubCategoria) fabrica.generarDao();
            SubCategoria subCategoria = SubCategoriaMapper.mapDtoToEntity(this.dtoSubCategoria);
            subCategoria.setActivo( 1);
            subCategoria.setCreado_el(
                    new Date(Calendar
                            .getInstance()
                            .getTime()
                            .getTime())
            );
            SubCategoria resul = dao.insert( subCategoria);
            result = ResponseGeneral.SuccesCreate( resul.get_id());
        }catch (Exception e) {
            this.result = ResponseGeneral.Failure("Ha ocurrido un error al registrar la subcategoria");
        }
    }

    /**
     * Name: getResult
     *
     * @return Response
     */
    @Override
    public Response getResult() { return this.result; }

    public void setDtoSubCategoria(DtoSubCategoria dtoSubCategoria) { this.dtoSubCategoria = dtoSubCategoria; }
}
