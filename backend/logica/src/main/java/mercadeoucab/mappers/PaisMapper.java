package mercadeoucab.mappers;

import mercadeoucab.dtos.DtoPais;
import mercadeoucab.entidades.Pais;

public class PaisMapper {

    public static Pais mapDtoToEntity(DtoPais dtoPais){
        Pais pais = new Pais( dtoPais.get_id());
        pais.setNombre( dtoPais.getNombre());
        pais.setActivo( dtoPais.getActivo());
        pais.setCreado_el( dtoPais.getCreado_el());
        pais.setModificado_el( dtoPais.getModificado_el());
        return pais;
    }

    public static DtoPais mapEntityToDto( Pais pais) throws Exception {
        DtoPais dtoPais = new DtoPais( pais.get_id());
        dtoPais.setNombre( pais.getNombre());
        dtoPais.setActivo( pais.getActivo());
        dtoPais.setCreado_el( pais.getCreado_el());
        dtoPais.setModificado_el( pais.getModificado_el());
        return dtoPais;
    }
}
