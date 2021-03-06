package mercadeoucab.comandos.SubCategoria;

import mercadeoucab.accesodatos.DaoSubCategoria;
import mercadeoucab.comandos.ComandoAbstracto;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.entidades.SubCategoria;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.responses.ResponseGeneral;

import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;

public class ComandoEliminarSubCategoria extends ComandoAbstracto implements ComandoBase {
    private Response result;
    private long id;

    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try{
            FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.SUBCATEGORIA);
            DaoSubCategoria dao = (DaoSubCategoria) fabrica.generarDao();
            SubCategoria subCategoria = dao.find( id, SubCategoria.class);
            subCategoria.setActivo( 0);
            subCategoria.setModificado_el(
                    new Date(Calendar
                            .getInstance()
                            .getTime()
                            .getTime())
            );
            dao.update( subCategoria);
            result = ResponseGeneral.SuccesMessage();
        }catch (Exception e) {
            this.result = ResponseGeneral.Failure("Ocurrio un error al eliminar la subcategoria");
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
}
