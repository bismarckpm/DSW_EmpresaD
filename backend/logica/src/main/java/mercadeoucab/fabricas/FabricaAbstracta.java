package mercadeoucab.fabricas;

import mercadeoucab.dtos.DtoBase;
import mercadeoucab.entidades.EntidadBase;
import mercadeoucab.fabricas.Enums.Dtos;
import mercadeoucab.fabricas.Enums.Entidades;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricasConcretas.FabricaDtos;
import mercadeoucab.fabricas.FabricasConcretas.FabricaEntidades;

/**
 * Name: FabricaAbstracta
 */
public abstract class FabricaAbstracta {
    private static final FabricaEntidades ENTITY_FACTORY = new FabricaEntidades();
    private static final FabricaDtos DTOS_FACTORY = new FabricaDtos();

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
     * @return EntidadBase
     */
    public abstract EntidadBase generarEntidad(Entidades entidad);

    /**
     * Name: DtoBase
     * Description: Genera un Dto
     * @param dtos
     * @return DtoBase
     */
    public abstract DtoBase generarDto(Dtos dtos);
}
