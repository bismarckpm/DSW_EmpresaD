package mercadeoucab.comandos.subcategoria;

import mercadeoucab.accesodatos.DaoSubCategoria;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoSubCategoria;
import mercadeoucab.entidades.SubCategoria;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.responses.ResponseGeneral;

import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;

public class ComandoActualizarSubCategoria implements ComandoBase {
    private Response result;
    private long id;
    private DtoSubCategoria dtoSubCategoria;
    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try{
            FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.SUBCATEGORIA);
            DaoSubCategoria dao = (DaoSubCategoria) fabrica.generarDao();
            SubCategoria subCategoria = dao.find( id, SubCategoria.class);
            subCategoria.setNombre( dtoSubCategoria.getNombre());
            subCategoria.setModificado_el(
                    new Date(Calendar
                            .getInstance()
                            .getTime()
                            .getTime())
            );
            dao.update( subCategoria);
            result = ResponseGeneral.SuccesMessage();
        }
        catch (Exception e) {
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

    public void setId(long id) { this.id = id; }

    public void setDtoSubCategoria(DtoSubCategoria dtoSubCategoria) { this.dtoSubCategoria = dtoSubCategoria; }
}
