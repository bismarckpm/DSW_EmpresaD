package mercadeoucab.comandos.Estado;

import mercadeoucab.accesodatos.DaoEstado;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoEstado;
import mercadeoucab.entidades.Estado;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.mappers.EstadoMapper;
import mercadeoucab.responses.ResponseEstado;
import mercadeoucab.responses.ResponseGeneral;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Oscar Marquez
 * @version 1.0
 * @since 2021-01-29
 */
public class ComandoListarEstados implements ComandoBase {
    private Response result;

    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try {
            JsonArrayBuilder estados = Json.createArrayBuilder();
            FabricaAbstracta fabricaEstado = FabricaAbstracta.getFactory( Fabricas.ESTADO);
            DaoEstado dao = (DaoEstado) fabricaEstado.generarDao();
            List<Estado> estadosObtenidos = dao.findAll( Estado.class);
            ResponseEstado responseEstado = (ResponseEstado)fabricaEstado.generarResponse();
            if (Objects.nonNull(estadosObtenidos) && estadosObtenidos.size()> 0 ) {
                for (Estado estado : estadosObtenidos) {
                    if (estado.getActivo() != 0) {
                        DtoEstado dtoEstado = EstadoMapper.mapentitytoDto(estado);
                        JsonObject objeto = responseEstado.generate(dtoEstado);
                        estados.add(objeto);
                    }
                }
                this.result = ResponseGeneral.Succes( estados);
            }else {
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
}
