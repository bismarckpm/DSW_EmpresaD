package mercadeoucab.fabricas;

import mercadeoucab.dtos.DtoBase;
import mercadeoucab.entidades.*;

/**
 * Name: EntityFactory
 */
public class EntityFactory extends FabricaAbstracta{
    /**
     * Name: generarEntidad
     * Description: Crea una entidad
     * @param entidad
     * @return
     */
    @Override
    public EntidadBase generarEntidad(Entidades entidad) {
        EntidadBase fabricado = null;
        switch (entidad){
            case CATEGORIA:
                fabricado = new Categoria();
                break;
            case DATOENCUESTADO:
                fabricado = new EntidadBase();
                break;
            case ESTADO:
                fabricado = new Estado();
                break;
            case ESTUDIO:
                fabricado = new Estudio();
                break;
            case HIJO:
                fabricado = new Hijo();
                break;
            case MARCA:
                fabricado = new Marca();
                break;
            case MUESTRAPOBLACION:
                fabricado = new MuestraPoblacion();
                break;
            case MUNICIPIO:
                fabricado = new Municipio();
                break;
            case OCUPACION:
                fabricado = new Ocupacion();
                break;
            case OPCION:
                fabricado = new Opcion();
                break;
            case PAIS:
                fabricado = new Pais();
                break;
            case PARROQUIA:
                fabricado = new Parroquia();
                break;
            case PREGUNTA:
                fabricado = new Pregunta();
                break;
            case TIPO:
                fabricado = new Tipo();
                break;
            case USUARIO:
                fabricado = new Usuario();
                break;
            case TELEFONO:
                fabricado = new Telefono();
                break;
            case RESPUESTA:
                fabricado = new Respuesta();
                break;
            case SOLICITUD:
                fabricado = new Solicitud();
                break;
            case PRESENTACION:
                fabricado = new Presentacion();
                break;
            case SUBCATEGORIA:
                fabricado = new SubCategoria();
                break;
        }
        return fabricado;
    }
    /**
     * Name: DtoBase
     * Description: Genera un Dto
     * @param dtos
     * @return
     */
    public DtoBase generarDto(Dtos dtos) {
        return null;
    }
}
