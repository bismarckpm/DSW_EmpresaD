package mercadeoucab.mappers;

import mercadeoucab.dtos.DtoEstado;
import mercadeoucab.entidades.Estado;

import java.util.Objects;

public class EstadoMapper {

    public static Estado mapdtotoEntity(DtoEstado dto){
        Estado entity = new Estado();
        entity.set_id( dto.get_id() );
        entity.setCreado_el( dto.getCreado_el() );
        entity.setModificado_el( dto.getModificado_el() );
        entity.setNombre( dto.getNombre() );
        entity.setActivo( dto.getActivo() );
        if(Objects.nonNull( dto.getFk_pais()))
            entity.setFk_pais(PaisMapper.mapDtoToEntity(dto.getFk_pais()));
        return entity;
    }

    public static DtoEstado mapentitytoDto(Estado entity) throws Exception {
        DtoEstado dto = new DtoEstado();
        dto.set_id( entity.get_id() );
        dto.setCreado_el( entity.getCreado_el() );
        dto.setModificado_el( entity.getModificado_el() );
        dto.setNombre( entity.getNombre() );
        dto.setActivo( entity.getActivo() );
        if(Objects.nonNull( entity.getFk_pais()))
            dto.setFk_pais(PaisMapper.mapEntityToDto(entity.getFk_pais()));
        return dto;
    }

}
