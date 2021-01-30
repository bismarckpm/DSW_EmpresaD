package mercadeoucab.comandos.Municipio;

import com.sun.org.apache.bcel.internal.generic.FADD;
import mercadeoucab.accesodatos.DaoMunicipio;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoMunicipio;
import mercadeoucab.entidades.Municipio;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.mappers.MunicipioMapper;
import mercadeoucab.responses.ResponseGeneral;
import mercadeoucab.responses.ResponseMunicipio;

import javax.json.JsonObject;
import javax.ws.rs.core.Response;

/**
 *
 * @author Oscar Marquez
 * @version 1.0
 * @since 2021-01-29
 */
public class ComandoObtenerMunicipio implements ComandoBase {
    private Response result;
    private long id;

    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try {
            JsonObject municipio;
            FabricaAbstracta fabricaMunicipio = FabricaAbstracta.getFactory( Fabricas.MUNICIPIO);
            DaoMunicipio dao = (DaoMunicipio) fabricaMunicipio.generarDao();
            Municipio resul = dao.find(id , Municipio.class);
            ResponseMunicipio responseMunicipio = (ResponseMunicipio) fabricaMunicipio.generarResponse();
            if ( resul.getActivo()!=0 ){
                DtoMunicipio dtoMunicipio = MunicipioMapper.mapEntitytoDto( resul);
                municipio = responseMunicipio.generate( dtoMunicipio);
                this.result = ResponseGeneral.Succes( municipio);
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

    public void setId(long id) {
        this.id = id;
    }
}
