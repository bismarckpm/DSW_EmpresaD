package mercadeoucab.comandos.Usuario;

import mercadeoucab.accesodatos.DaoDatoEncuestado;
import mercadeoucab.accesodatos.DaoEstudio;
import mercadeoucab.accesodatos.DaoUsuario;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoEstudio;
import mercadeoucab.entidades.DatoEncuestado;
import mercadeoucab.entidades.Estudio;
import mercadeoucab.entidades.Usuario;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.fabricas.FabricasConcretas.FabricaDatoEncuestado;
import mercadeoucab.fabricas.FabricasConcretas.FabricaEstudio;
import mercadeoucab.fabricas.FabricasConcretas.FabricaUsuario;
import mercadeoucab.mappers.EstudioMapper;
import mercadeoucab.responses.ResponseEstudio;
import mercadeoucab.responses.ResponseGeneral;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import java.util.List;

public class ComandoEstudiosAplicablesEncuestado implements ComandoBase {

    private Response result;
    private long id;

    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        JsonArrayBuilder estudiosList = Json.createArrayBuilder();
        try {
            FabricaEstudio fabricaEstudio = (FabricaEstudio) FabricaAbstracta.getFactory( Fabricas.ESTUDIO);
            FabricaUsuario fabricaUsuario = (FabricaUsuario) FabricaAbstracta.getFactory( Fabricas.USUARIO);
            FabricaDatoEncuestado fabricaDatoEncuestado = (FabricaDatoEncuestado) FabricaDatoEncuestado.getFactory( Fabricas.DATOENCUESTADO);
            DaoEstudio dao = (DaoEstudio) fabricaEstudio.generarDao();
            DaoUsuario daoUsuario = (DaoUsuario)fabricaUsuario.generarDao();
            DaoDatoEncuestado daoDatoEncuestado = (DaoDatoEncuestado)fabricaDatoEncuestado.generarDao();
            DatoEncuestado datoEncuestado = daoDatoEncuestado.datoEncuestado(daoUsuario.find(id, Usuario.class));
            List<Estudio> estudios = dao.estudiosAplicanUsuario(datoEncuestado);
            ResponseEstudio responseEstudio = (ResponseEstudio)fabricaEstudio.generarResponse();
            if(!(estudios.isEmpty())){
                for(Estudio estudio: estudios){
                    DtoEstudio dtoEstudio = EstudioMapper.mapEntitytoDto( estudio);
                    JsonObject agregar = responseEstudio.generate( dtoEstudio);
                    estudiosList.add(agregar);
                }
                this.result = ResponseGeneral.Succes( estudiosList);
            }
            else{
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
