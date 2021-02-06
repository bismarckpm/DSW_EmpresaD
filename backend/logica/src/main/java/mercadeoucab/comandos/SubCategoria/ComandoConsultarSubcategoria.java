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
import mercadeoucab.responses.ResponseSubCategoria;

import javax.json.JsonObject;
import javax.ws.rs.core.Response;

public class ComandoConsultarSubcategoria extends ComandoAbstracto implements ComandoBase {
    private Response result;
    private long id;
    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try{
            JsonObject subcategoria;
            FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.SUBCATEGORIA);
            DaoSubCategoria dao = (DaoSubCategoria) fabrica.generarDao();
            SubCategoria resul = dao.find( id, SubCategoria.class);
            ResponseSubCategoria responseSubCategoria = (ResponseSubCategoria) fabrica.generarResponse();
            DtoSubCategoria dtoSubCategoria = SubCategoriaMapper.mapEntityToDto( resul);
            if ( dtoSubCategoria.getActivo() == 1 ){
                subcategoria = responseSubCategoria.generate( dtoSubCategoria);
                this.result = ResponseGeneral.Succes( subcategoria);

            }else{
                this.result = ResponseGeneral.NoData();
            }
        }catch (Exception e) {
            this.result = ResponseGeneral.Failure("Ha ocurrido un error al consultar la subcategorias");
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
