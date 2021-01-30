package mercadeoucab.mappers;

import mercadeoucab.dtos.DtoOpcion;
import mercadeoucab.entidades.Opcion;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;

import java.util.Objects;

public class OpcionMapper {

    public static Opcion mapDtotoEntity(DtoOpcion dto){
        FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.OPCION);
        Opcion entity = (Opcion) fabrica.generarEntidad();
        entity.set_id(dto.get_id());
        entity.setCreado_el(dto.getCreado_el());
        entity.setModificado_el(dto.getModificado_el());
        entity.setActivo(dto.getActivo());
        entity.setNombre_opcion(dto.getNombre_opcion());
        if(Objects.nonNull(dto.get_Dtopregunta()))
            entity.setFk_pregunta(PreguntaMapper.mapDtoToEntity(dto.get_Dtopregunta()));
        return entity;
    }

    public static DtoOpcion mapEntitytoDto(Opcion entity) throws Exception {
        FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.OPCION);
        DtoOpcion dto = (DtoOpcion) fabrica.generarDto();
        dto.set_id(entity.get_id());
        dto.setCreado_el(entity.getCreado_el());
        dto.setModificado_el(entity.getModificado_el());
        dto.setActivo(entity.getActivo());
        dto.setNombre_opcion(entity.getNombre_opcion());
        return dto;
    }
}
