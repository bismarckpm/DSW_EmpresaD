package mercadeoucab.mappers;

import mercadeoucab.dtos.DtoMuestraPoblacion;
import mercadeoucab.entidades.MuestraPoblacion;

import java.util.Objects;

public class MuestraPoblacionMapper {

    public static MuestraPoblacion mapDtotoEntity(DtoMuestraPoblacion dto){
        MuestraPoblacion entity = new MuestraPoblacion();
        entity.set_id(dto.get_id());
        entity.setCreado_el(dto.getCreado_el());
        entity.setModificado_el(dto.getModificado_el());
        entity.setGenero(dto.getGenero());
        entity.setNivelEconomico(dto.getNivelEconomico());
        entity.setNivelAcademico(dto.getNivelAcademico());
        entity.setRangoEdadInicio(dto.getRangoEdadInicio());
        entity.setRangoEdadFin(dto.getRangoEdadFin());
        entity.setCantidadHijos(dto.getCantidadHijos());
        if(Objects.nonNull(dto.getFk_lugar()))
            entity.setFk_lugar(ParroquiaMapper.mapDtoToEntity(dto.getFk_lugar()));
        if(Objects.nonNull(dto.getDtoOcupacion()))
            entity.setFk_ocupacion(OcupacionMapper.mapDtotoEntity(dto.getDtoOcupacion()));
        return entity;
    }

    public static DtoMuestraPoblacion mapEntitytoDto(MuestraPoblacion entity) throws Exception {
        DtoMuestraPoblacion dto = new DtoMuestraPoblacion();
        dto.set_id(entity.get_id());
        dto.setCreado_el(entity.getCreado_el());
        dto.setModificado_el(entity.getModificado_el());
        dto.setGenero(entity.getGenero());
        dto.setNivelEconomico(entity.getNivelEconomico());
        dto.setNivelAcademico(entity.getNivelAcademico());
        dto.setRangoEdadInicio(entity.getRangoEdadInicio());
        dto.setRangoEdadFin(entity.getRangoEdadFin());
        dto.setCantidadHijos(entity.getCantidadHijos());
        if(Objects.nonNull(entity.getFk_lugar()))
            dto.setFk_lugar(ParroquiaMapper.mapEntityToDto(entity.getFk_lugar()));
        if(Objects.nonNull(entity.getFk_ocupacion()))
            dto.setDtoOcupacion(OcupacionMapper.mapEntitytoDto(entity.getFk_ocupacion()));
        return dto;
    }
}
