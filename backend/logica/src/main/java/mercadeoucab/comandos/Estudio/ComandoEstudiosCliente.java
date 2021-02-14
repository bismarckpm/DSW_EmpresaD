package mercadeoucab.comandos.Estudio;

import mercadeoucab.accesodatos.DaoEstudio;
import mercadeoucab.accesodatos.DaoUsuario;
import mercadeoucab.comandos.ComandoAbstracto;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ComandoEstudiosCliente extends ComandoAbstracto implements ComandoBase {

    private Response result;
    private long id;
    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try {
            JsonArrayBuilder estudiosList = Json.createArrayBuilder();
            FabricaAbstracta fabricaUsuario = FabricaAbstracta.getFactory(Fabricas.USUARIO);
            FabricaAbstracta fabricaEstudio = FabricaAbstracta.getFactory(Fabricas.ESTUDIO);
            DaoUsuario daoUsuario = (DaoUsuario) fabricaUsuario.generarDao();
            DaoEstudio daoEstudio = (DaoEstudio) fabricaEstudio.generarDao();
            Usuario usuario = daoUsuario.find(id, Usuario.class);
            List<Estudio> estudios = daoEstudio.estudiosCliente(usuario);
            if (Objects.nonNull(estudios) && estudios.size() > 0) {
                for (Estudio estudio : estudios) {
                    if (estudio.getActivo() == 1) {
                        ResponseEstudio responseEstudio = (ResponseEstudio) fabricaEstudio.generarResponse();
                        DtoEstudio dtoEstudio = EstudioMapper.mapEntitytoDto(estudio);
                        JsonObject agregar = responseEstudio.generate(dtoEstudio);
                        estudiosList.add(agregar);
                    }
                }
                result = ResponseGeneral.Succes(estudiosList);
            } else
                result = ResponseGeneral.NoData();
        }
        catch (Exception e){
            System.out.println(e);
            result = ResponseGeneral.Failure("Ha ocurrido un error al listar los estudios");
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
