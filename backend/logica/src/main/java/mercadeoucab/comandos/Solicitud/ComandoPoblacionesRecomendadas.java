package mercadeoucab.comandos.Solicitud;

import mercadeoucab.accesodatos.DaoEstudio;
import mercadeoucab.accesodatos.DaoSolicitud;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoMuestraPoblacion;
import mercadeoucab.entidades.MuestraPoblacion;
import mercadeoucab.entidades.Solicitud;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.mappers.MuestraPoblacionMapper;
import mercadeoucab.responses.ResponseGeneral;
import mercadeoucab.responses.ResponseMuestraPoblacion;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import java.util.List;

public class ComandoPoblacionesRecomendadas implements ComandoBase {

    private Response result;
    private long id;
    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try {
            JsonArrayBuilder muestrasList = Json.createArrayBuilder();
            FabricaAbstracta fabricaSolicitud = FabricaAbstracta.getFactory(Fabricas.SOLICITUD);
            FabricaAbstracta fabricaEstudio = FabricaAbstracta.getFactory(Fabricas.ESTUDIO);
            FabricaAbstracta fabricaMuestraPoblacion = FabricaAbstracta.getFactory(Fabricas.MUESTRAPOBLACION);
            DaoSolicitud daoSolicitud = (DaoSolicitud) fabricaSolicitud.generarDao();
            DaoEstudio daoEstudio = (DaoEstudio) fabricaEstudio.generarDao();
            List<MuestraPoblacion> muestras = daoEstudio.poblacionesSimilares(
                    daoSolicitud.find(id, Solicitud.class)
            );
            ResponseMuestraPoblacion responseMuestraPoblacion = (ResponseMuestraPoblacion) fabricaMuestraPoblacion.generarResponse();
            if(muestras.size() > 0){
                for(MuestraPoblacion muestra: muestras){
                    if (muestra.getActivo() == 1) {
                        DtoMuestraPoblacion dtoMuestraPoblacion = MuestraPoblacionMapper.mapEntitytoDto(
                                muestra
                        );
                        JsonObject agregar = responseMuestraPoblacion.generate( dtoMuestraPoblacion);
                        muestrasList.add(agregar);
                    }
                }
                result = ResponseGeneral.Succes( muestrasList);
            }
            else {
                result = ResponseGeneral.NoData();
            }
        }
        catch (Exception e){
            result = ResponseGeneral.Failure("Ha ocurrido un error al recomendar una poblacion");
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
