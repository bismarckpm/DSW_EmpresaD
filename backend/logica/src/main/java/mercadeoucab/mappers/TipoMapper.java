package mercadeoucab.mappers;

import mercadeoucab.dtos.DtoTipo;
import mercadeoucab.entidades.Tipo;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;

public class TipoMapper {

    public static Tipo mapDtoToEntity(DtoTipo dtoTipo){
        FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.TIPO);
        Tipo tipo = (Tipo) fabrica.generarEntidad();
        tipo.set_id(dtoTipo.get_id());
        tipo.setNombre( dtoTipo.getNombre());
        tipo.setActivo( dtoTipo.getActivo());
        tipo.setModificado_el( dtoTipo.getModificado_el());
        tipo.setCreado_el( dtoTipo.getCreado_el());
        return tipo;
    }

    public static DtoTipo mapEntityToDto( Tipo tipo) throws Exception {
        FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.TIPO);
        DtoTipo dtoTipo = (DtoTipo) fabrica.generarDto();
        dtoTipo.set_id(tipo.get_id());
        dtoTipo.setNombre( tipo.getNombre());
        dtoTipo.setActivo( tipo.getActivo());
        dtoTipo.setCreado_el( tipo.getCreado_el());
        dtoTipo.setModificado_el( tipo.getModificado_el());
        return dtoTipo;
    }
}
