package mercadeoucab.mappers;

import mercadeoucab.dtos.DtoMunicipio;
import mercadeoucab.entidades.Municipio;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;

import java.util.Objects;

public class MunicipioMapper {

    public static Municipio mapDtotoEntity(DtoMunicipio dto){
        FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.MUNICIPIO);
        Municipio entity = (Municipio) fabrica.generarEntidad();
        entity.set_id(dto.get_id());
        entity.setCreado_el(dto.getCreado_el());
        entity.setModificado_el(dto.getModificado_el());
        entity.setActivo(dto.getActivo());
        entity.setNombre(dto.getNombre());
        if(Objects.nonNull(dto.getFk_estado()))
            entity.setFk_estado(EstadoMapper.mapdtotoEntity(dto.getFk_estado()));
        return entity;
    }

    public static DtoMunicipio mapEntitytoDto(Municipio entity) throws Exception {
        FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.MUNICIPIO);
        DtoMunicipio dto = (DtoMunicipio) fabrica.generarDto();
        dto.set_id(entity.get_id());
        dto.setCreado_el(entity.getCreado_el());
        dto.setModificado_el(entity.getModificado_el());
        dto.setActivo(entity.getActivo());
        dto.setNombre(entity.getNombre());
        if(Objects.nonNull(entity.getFk_estado()))
            dto.setFk_estado(EstadoMapper.mapentitytoDto(entity.getFk_estado()));
        return dto;
    }
}
