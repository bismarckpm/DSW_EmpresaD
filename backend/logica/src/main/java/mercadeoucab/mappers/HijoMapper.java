package mercadeoucab.mappers;

import mercadeoucab.dtos.DtoDatoEncuestado;
import mercadeoucab.dtos.DtoHijo;
import mercadeoucab.entidades.Hijo;

import java.sql.Date;

public class HijoMapper {

    private String genero;
    private Date edad;
    private DtoDatoEncuestado fk_dato_encuestado;

    public static Hijo mapDtotoEntity(DtoHijo dto){
        Hijo entity = new Hijo();
        entity.set_id(dto.get_id());
        entity.setCreado_el(dto.getCreado_el());
        entity.setModificado_el(dto.getModificado_el());
        entity.setGenero(dto.getGenero());
        entity.setEdad(dto.getEdad());
        return entity;
    }

    public static DtoHijo mapEntitytoDto(Hijo entity){
        DtoHijo dto = new DtoHijo();
        dto.set_id(entity.get_id());
        dto.setCreado_el(entity.getCreado_el());
        dto.setModificado_el(entity.getModificado_el());
        dto.setGenero(entity.getGenero());
        dto.setEdad(entity.getEdad());
        return dto;
    }
}
