package mercadeoucab.comandos.Pais;

import mercadeoucab.accesodatos.DaoPais;
import mercadeoucab.comandos.ComandoAbstracto;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoPais;
import mercadeoucab.entidades.Pais;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.mappers.PaisMapper;
import mercadeoucab.responses.ResponseGeneral;
import mercadeoucab.responses.ResponsePais;

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
public class ComandoListarPaises extends ComandoAbstracto implements ComandoBase {
    private Response result;

    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try {
            FabricaAbstracta fabricaPais = FabricaAbstracta.getFactory( Fabricas.PAIS);
            ResponsePais responsePais = (ResponsePais) fabricaPais.generarResponse();
            JsonArrayBuilder paises = Json.createArrayBuilder();
            DaoPais dao = (DaoPais)fabricaPais.generarDao();
            List<Pais> paisesObtenidos = dao.findAll( Pais.class);
            if (Objects.nonNull(paisesObtenidos) && paisesObtenidos.size() >0 ){
                for (Pais pais: paisesObtenidos){
                    if (pais.getActivo()!= 0){
                        DtoPais dtoPais = PaisMapper.mapEntityToDto( pais);
                        JsonObject objeto = responsePais.generate( dtoPais);
                        paises.add( objeto);
                    }
                }
                this.result = ResponseGeneral.Succes(paises);
            }else{
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
