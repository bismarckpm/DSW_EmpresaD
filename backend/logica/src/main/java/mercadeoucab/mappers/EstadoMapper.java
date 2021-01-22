package mercadeoucab.mappers;

import mercadeoucab.dtos.DtoEstado;
import mercadeoucab.entidades.Estado;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;

import java.util.Objects;

public class EstadoMapper {

    public static Estado mapdtotoEntity(DtoEstado dto){
        FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.ESTADO);
        Estado entity = (Estado) fabrica.generarEntidad();
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
        FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.ESTADO);
        DtoEstado dto = (DtoEstado) fabrica.generarDto();
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
