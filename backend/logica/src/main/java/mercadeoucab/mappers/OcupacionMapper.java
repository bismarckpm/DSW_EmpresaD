package mercadeoucab.mappers;

import mercadeoucab.dtos.DtoOcupacion;
import mercadeoucab.entidades.Ocupacion;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;

public class OcupacionMapper {

    public static Ocupacion mapDtotoEntity(DtoOcupacion dto){
        FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.OCUPACION);
        Ocupacion entity = (Ocupacion) fabrica.generarEntidad();
        entity.set_id(dto.get_id());
        entity.setCreado_el(dto.getCreado_el());
        entity.setModificado_el(dto.getModificado_el());
        entity.setActivo(dto.getActivo());
        entity.setNombre(dto.getNombre());
        return entity;
    }

    public static DtoOcupacion mapEntitytoDto(Ocupacion entity){
        FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.OCUPACION);
        DtoOcupacion dto = (DtoOcupacion) fabrica.generarDto();
        dto.set_id(entity.get_id());
        dto.setCreado_el(entity.getCreado_el());
        dto.setModificado_el(entity.getModificado_el());
        dto.setActivo(entity.getActivo());
        dto.setNombre(entity.getNombre());
        return dto;
    }
}
