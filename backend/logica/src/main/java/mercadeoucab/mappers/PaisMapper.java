package mercadeoucab.mappers;

import mercadeoucab.dtos.DtoPais;
import mercadeoucab.entidades.Pais;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;

public class PaisMapper {

    public static Pais mapDtoToEntity(DtoPais dtoPais){
        FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.PAIS);
        Pais pais = (Pais) fabrica.generarEntidad();
        pais.set_id(dtoPais.get_id());
        pais.setNombre( dtoPais.getNombre());
        pais.setActivo( dtoPais.getActivo());
        pais.setCreado_el( dtoPais.getCreado_el());
        pais.setModificado_el( dtoPais.getModificado_el());
        return pais;
    }

    public static DtoPais mapEntityToDto( Pais pais) throws Exception {
        FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.PAIS);
        DtoPais dtoPais = (DtoPais) fabrica.generarDto();
        dtoPais.setId(pais.get_id());
        dtoPais.setNombre( pais.getNombre());
        dtoPais.setActivo( pais.getActivo());
        dtoPais.setCreado_el( pais.getCreado_el());
        dtoPais.setModificado_el( pais.getModificado_el());
        return dtoPais;
    }
}
