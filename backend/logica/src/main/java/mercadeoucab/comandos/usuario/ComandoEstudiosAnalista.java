package mercadeoucab.comandos.usuario;

import mercadeoucab.accesodatos.DaoEstudio;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoEstudio;
import mercadeoucab.entidades.Estudio;
import mercadeoucab.entidades.Usuario;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.mappers.EstudioMapper;
import mercadeoucab.responses.ResponseEstudio;
import mercadeoucab.responses.ResponseGeneral;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 *
 * @author Oscar Marquez
 * @version 1.0
 * @since 2021-01-29
 */
public class ComandoEstudiosAnalista implements ComandoBase {
    private Response result;
    private long id;

    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        JsonArrayBuilder estudiosList = Json.createArrayBuilder();
        try {
            FabricaAbstracta fabricaEstudio = FabricaAbstracta.getFactory( Fabricas.ESTUDIO);
            FabricaAbstracta fabricaUsuario = FabricaAbstracta.getFactory( Fabricas.USUARIO);
            DaoEstudio dao = (DaoEstudio) fabricaEstudio.generarDao();
            Usuario usuario = (Usuario) fabricaUsuario.generarEntidad();
            usuario.set_id( id);
            List<Estudio> estudios = dao.estudiosAnalista(
                    usuario
            );
            ResponseEstudio responseEstudio =  (ResponseEstudio)fabricaEstudio.generarResponse();
            if (!(estudios.isEmpty())) {
                for (Estudio estudio : estudios) {

                    if (estudio.getActivo() == 1) {
                        DtoEstudio dtoEstudio = EstudioMapper.mapEntitytoDto(estudio);
                        JsonObject agregar = responseEstudio.generate(dtoEstudio);
                        estudiosList.add(agregar);
                    }
                }//final del for
                this.result = ResponseGeneral.Succes(estudiosList);
            }//Final IF
            else {
                this.result = ResponseGeneral.NoData();
            }
        }catch (Exception e){
            this.result = ResponseGeneral.Failure("ERROR");
        }
    }

    @Override
    public Response getResult() {
        return this.result;
    }

    public void setId(long id) {
        this.id = id;
    }
}
