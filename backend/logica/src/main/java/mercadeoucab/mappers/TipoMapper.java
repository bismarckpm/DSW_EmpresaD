package mercadeoucab.mappers;

import mercadeoucab.dtos.DtoPresentacion;
import mercadeoucab.dtos.DtoTipo;
import mercadeoucab.entidades.Presentacion;
import mercadeoucab.entidades.Tipo;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;

import java.util.Objects;

public class TipoMapper {

    public static Tipo mapDtoToEntity(DtoTipo dtoTipo) throws Exception {
        FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.TIPO);
        Tipo tipo = (Tipo) fabrica.generarEntidad();
        tipo.set_id(dtoTipo.get_id());
        tipo.setNombre( dtoTipo.getNombre());
        tipo.setActivo( dtoTipo.getActivo());
        tipo.setModificado_el( dtoTipo.getModificado_el());
        tipo.setCreado_el( dtoTipo.getCreado_el());

        if(Objects.nonNull(dtoTipo.getSubCategoria())) {
            tipo.setSubCategoria(
                    SubCategoriaMapper.mapDtoToEntity(dtoTipo.getSubCategoria())
            );
        }

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

        if(Objects.nonNull(tipo.getSubCategoria())) {
            dtoTipo.setSubCategoria(
                    SubCategoriaMapper.mapEntityToDto(tipo.getSubCategoria())
            );
        }

        return dtoTipo;
    }

    public static Tipo mapDtoToEntity2(DtoTipo dtoTipo) throws Exception {
        FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.TIPO);
        Tipo tipo = (Tipo) fabrica.generarEntidad();
        tipo.set_id(dtoTipo.get_id());
        tipo.setNombre( dtoTipo.getNombre());
        tipo.setActivo( dtoTipo.getActivo());
        tipo.setModificado_el( dtoTipo.getModificado_el());
        tipo.setCreado_el( dtoTipo.getCreado_el());

        if (Objects.nonNull(dtoTipo.getPresentacionList()) && dtoTipo.getPresentacionList().size() > 0){
            for (DtoPresentacion dtoPresentacion : dtoTipo.getPresentacionList()){
                tipo.addPresentacion(
                        PresentacionMapper.mapDtoToEntity2(dtoPresentacion)
                );
            }
        }

        return tipo;
    }

    public static DtoTipo mapEntityToDto2( Tipo tipo) throws Exception {
        FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.TIPO);
        DtoTipo dtoTipo = (DtoTipo) fabrica.generarDto();
        dtoTipo.set_id(tipo.get_id());
        dtoTipo.setNombre( tipo.getNombre());
        dtoTipo.setActivo( tipo.getActivo());
        dtoTipo.setCreado_el( tipo.getCreado_el());
        dtoTipo.setModificado_el( tipo.getModificado_el());

        if (Objects.nonNull(tipo.getPresentacionList()) && tipo.getPresentacionList().size() > 0){
            for (Presentacion presentacion : tipo.getPresentacionList()){
                dtoTipo.addPresentacion(
                        PresentacionMapper.mapEntityToDto2(presentacion)
                );
            }
        }
        return dtoTipo;
    }
}
