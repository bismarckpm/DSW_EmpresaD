package mercadeoucab.mappers;

import mercadeoucab.dtos.DtoTelefono;
import mercadeoucab.entidades.Telefono;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;

public class TelefonoMapper {

    public static Telefono mapDtotoEntiy(DtoTelefono dto){
        FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.TELEFONO);
        Telefono entity = (Telefono) fabrica.generarEntidad();
        entity.set_id(dto.get_id());
        entity.setModificado_el(dto.getModificado_el());
        entity.setCreado_el(dto.getCreado_el());
        entity.setActivo(dto.getActivo());
        entity.setTelefono(dto.getTelefono());
        return entity;
    }

    public static DtoTelefono mapEntitytoDto(Telefono entity){
        FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.TELEFONO);
        DtoTelefono dto = (DtoTelefono) fabrica.generarDto();
        dto.set_id(entity.get_id());
        dto.setModificado_el(entity.getModificado_el());
        dto.setCreado_el(entity.getCreado_el());
        dto.setActivo(entity.getActivo());
        dto.setTelefono(entity.getTelefono());
        return dto;
    }
}
