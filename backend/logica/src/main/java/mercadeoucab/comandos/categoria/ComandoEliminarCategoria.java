package mercadeoucab.comandos.categoria;

import mercadeoucab.accesodatos.DaoCategoria;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.entidades.Categoria;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.responses.ResponseGeneral;

import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;

public class ComandoEliminarCategoria implements ComandoBase {

    private Response result;
    private long id;

    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try {
            FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.CATEGORIA);
            DaoCategoria dao = (DaoCategoria) fabrica.generarDao();
            Categoria categoria = dao.find(id, Categoria.class);
            categoria.setActivo(0);
            categoria.setModificado_el(new Date(Calendar.getInstance().getTime().getTime()));
            Categoria resul = dao.update(categoria);
            result = ResponseGeneral.SuccesMessage();
        }
        catch (Exception e){
            this.result = ResponseGeneral.Failure("Ocurrio un error al eliminar la categoria");
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
