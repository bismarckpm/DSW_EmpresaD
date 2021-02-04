package mercadeoucab.comandos.DatoEncuestado;

import mercadeoucab.accesodatos.DaoDatoEncuestado;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoDatoEncuestado;
import mercadeoucab.entidades.DatoEncuestado;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.mappers.DatoEncuestadoMapper;
import mercadeoucab.responses.ResponseDatoEncuestado;
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
public class ComandoListarDatoEncuestados implements ComandoBase {
    private Response result;

    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try {
            JsonArrayBuilder datoEncuestados = Json.createArrayBuilder();
            FabricaAbstracta fabricaDatoEncuestado = FabricaAbstracta.getFactory( Fabricas.DATOENCUESTADO);
            DaoDatoEncuestado dao =  (DaoDatoEncuestado) fabricaDatoEncuestado.generarDao();
            List<DatoEncuestado> datosEncuestadosObtenidos = dao.findAll( DatoEncuestado.class);
            if (Objects.nonNull(datosEncuestadosObtenidos) && datosEncuestadosObtenidos.size() > 0) {
                for (DatoEncuestado datoEncuestado : datosEncuestadosObtenidos) {
                    if (datoEncuestado.getActivo() != 0) {
                        ResponseDatoEncuestado responseDatoEncuestado = (ResponseDatoEncuestado) fabricaDatoEncuestado.generarResponse();
                        DtoDatoEncuestado dtoDatoEncuestado = DatoEncuestadoMapper.mapEntitytoDto(datoEncuestado);
                        JsonObject objeto = responseDatoEncuestado.generate(dtoDatoEncuestado);
                        datoEncuestados.add(objeto);
                    }
                }
                this.result = ResponseGeneral.Succes(datoEncuestados);
            }else{
                this.result = ResponseGeneral.NoData();
            }
        }catch (Exception e){
            this.result = ResponseGeneral.Failure("ERRO");
        }
    }

    @Override
    public Response getResult() {
        return this.result;
    }
}
