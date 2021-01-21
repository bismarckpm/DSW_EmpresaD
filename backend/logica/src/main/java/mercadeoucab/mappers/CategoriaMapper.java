package mercadeoucab.mappers;

import mercadeoucab.dtos.DtoCategoria;
import mercadeoucab.entidades.Categoria;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;


public class CategoriaMapper {

    public static Categoria mapDtotoEntity(DtoCategoria dto){
        FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.CATEGORIA);
        Categoria entity = (Categoria) fabrica.generarEntidad();
        entity.set_id( dto.get_id() );
        entity.setNombre( dto.getNombre() );
        entity.setActivo( dto.getActivo() );
        entity.setCreado_el( dto.getCreado_el() );
        entity.setModificado_el( dto.getModificado_el() );
        return entity;
    }

    public static DtoCategoria mapEntitytoDto(Categoria entity){
        DtoCategoria dto = new DtoCategoria();
        dto.set_id( entity.get_id() );
        dto.setNombre( entity.getNombre() );
        dto.setActivo( entity.getActivo() );
        dto.setCreado_el( entity.getCreado_el() );
        dto.setModificado_el( entity.getModificado_el() );
        return dto;
    }
}
