package mercadeoucab.comandos.subcategoria;

import mercadeoucab.accesodatos.DaoSubCategoria;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoSubCategoria;
import mercadeoucab.entidades.Categoria;
import mercadeoucab.entidades.SubCategoria;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.responses.ResponseGeneral;

import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;

public class ComandoRegistrarSubCategoria implements ComandoBase {
    private Response result;
    private DtoSubCategoria dtoSubCategoria;
    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try{
            FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.SUBCATEGORIA);
            FabricaAbstracta fabrica2 = FabricaAbstracta.getFactory(Fabricas.CATEGORIA);
            DaoSubCategoria dao = (DaoSubCategoria) fabrica.generarDao();
            SubCategoria subCategoria = (SubCategoria) fabrica.generarEntidad();
            subCategoria.setNombre( dtoSubCategoria.getNombre() );
            Categoria categoria = ( Categoria ) fabrica2.generarEntidad();
            categoria.set_id(dtoSubCategoria.getCategoria().get_id());
            subCategoria.setCategoria( categoria);
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
