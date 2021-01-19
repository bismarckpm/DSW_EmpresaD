package mercadeoucab.fabricas;

import mercadeoucab.dtos.*;
import mercadeoucab.entidades.EntidadBase;

/**
 * Name: DtosFactory
 */
public class DtosFactory extends FabricaAbstracta{

    /**
     * Name: generarEntidad
     * Description: Crea una entidad
     * @param entidad
     * @return
     */
    @Override
    public EntidadBase generarEntidad(Entidades entidad) {
        return null;
    }
    /**
     * Name: DtoBase
     * Description: Genera un Dto
     * @param dtos
     * @return
     */
    @Override
    public DtoBase generarDto(Dtos dtos) {
        DtoBase dto = null;
        switch (dtos){
            case DTOHIJO:
                dto = new DtoHijo();
                break;
            case DTOPAIS:
                dto = new DtoPais();
                break;
            case DTOTIPO:
                dto = new DtoTipo();
                break;
            case DTOMARCA:
                dto = new DtoMarca();
                break;
            case DTOESTADO:
                dto = new DtoEstado();
                break;
            case DTOOPCION:
                dto = new DtoOpcion();
                break;
            case DTOESTUDIO:
                dto = new DtoEstudio();
                break;
            case DTOUSUARIO:
                dto = new DtoUsuario();
                break;
            case DTOPREGUNTA:
                dto = new DtoPregunta();
                break;
            case DTOTELEFONO:
                dto = new DtoTelefono();
                break;
            case DTOCATEGORIA:
                dto = new DtoCategoria();
                break;
            case DTOMUNICIPIO:
                dto = new DtoMunicipio();
                break;
            case DTOOCUPACION:
                dto = new DtoOcupacion();
                break;
            case DTOPARROQUIA:
                dto = new DtoParroquia();
                break;
            case DTOSOLICITUD:
                dto = new DtoSolicitud();
                break;
            case DTOPRESENTACION:
                dto = new DtoPresentacion();
                break;
            case DTOSUBCATEGORIA:
                dto = new DtoSubCategoria();
                break;
            case DTODATOENCUESTADO:
                dto = new DtoDatoEncuestado();
                break;
            case DTOMUESTRAPOBLACION:
                dto = new DtoMuestraPoblacion();
                break;
            case DTOENCUESTAESTUDIO:
                dto = new DtoEncuestaEstudio();
                break;
            case DTODIRECTORIOAUSER: //Hay que resolver que hacer con estos dos ya que no extienden de DTO base
                //dto = new DtoDirectorioAUser();
                break;
            case MAIL:
                //dto = new DtoMail();
                break;
        }
        return dto;
    }
}
