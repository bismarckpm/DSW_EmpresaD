package mercadeoucab.fabricas;

import mercadeoucab.dtos.DtoBase;
import mercadeoucab.entidades.EntidadBase;

/**
 * Name: FabricaAbstracta
 */
public abstract class FabricaAbstracta {
    private static final EntityFactory ENTITY_FACTORY = new EntityFactory();
    private static final DtosFactory DTOS_FACTORY = new DtosFactory();

    /**
     * Name: getFactory
     * Description: retorna una fabrica concreta
     * @param tipo
     * @return fabrica
     */
    static FabricaAbstracta getFactory(Fabricas tipo){
        FabricaAbstracta fabrica = null;
        switch (tipo){
            case ENTIDADES:
                fabrica = ENTITY_FACTORY;
                break;
            case DTOS:
                fabrica = DTOS_FACTORY;
                break;
        }
        return fabrica;
    }

    /**
     * Name: generarEntidad
     * Description: Crea una entidad
     * @param entidad
     * @return
     */
    public abstract EntidadBase generarEntidad(Entidades entidad);

    /**
     * Name: DtoBase
     * Description: Genera un Dto
     * @param dtos
     * @return
     */
    public abstract DtoBase generarDto(Dtos dtos);
}
