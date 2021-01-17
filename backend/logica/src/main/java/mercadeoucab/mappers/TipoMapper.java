package mercadeoucab.mappers;

import mercadeoucab.dtos.DtoTipo;
import mercadeoucab.entidades.Tipo;

public class TipoMapper {

    public static Tipo mapDtoToEntity(DtoTipo dtoTipo){
        Tipo tipo = new Tipo( dtoTipo.get_id());

        tipo.setNombre( dtoTipo.getNombre());
        tipo.setActivo( dtoTipo.getActivo());
        tipo.setModificado_el( dtoTipo.getModificado_el());
        tipo.setCreado_el( dtoTipo.getCreado_el());

        return tipo;
    }

    public static DtoTipo mapEntityToDto( Tipo tipo) throws Exception {
        DtoTipo dtoTipo = new DtoTipo( tipo.get_id());
        dtoTipo.setNombre( tipo.getNombre());
        dtoTipo.setActivo( tipo.getActivo());
        dtoTipo.setCreado_el( tipo.getCreado_el());
        dtoTipo.setModificado_el( tipo.getModificado_el());

        return dtoTipo;
    }
}
