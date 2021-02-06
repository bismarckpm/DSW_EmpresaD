package mercadeoucab.mappers;

import mercadeoucab.dtos.DtoCategoria;
import mercadeoucab.dtos.DtoSubCategoria;
import mercadeoucab.entidades.Categoria;
import mercadeoucab.entidades.SubCategoria;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;

import java.util.Objects;


public class CategoriaMapper {

    public static Categoria mapDtotoEntity(DtoCategoria dto){
        FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.CATEGORIA);
        Categoria entity = (Categoria) fabrica.generarEntidad();
        entity.set_id( dto.get_id() );
        entity.setNombre( dto.getNombre() );
        entity.setActivo( dto.getActivo() );
        entity.setCreado_el( dto.getCreado_el() );
        entity.setModificado_el( dto.getModificado_el() );

        if (Objects.nonNull(dto.getSubCategorias()) && dto.getSubCategorias().size() > 0){
            for (DtoSubCategoria dtoSubCategoria : dto.getSubCategorias()){
                entity.addSubCategoria(
                        SubCategoriaMapper.mapDtoToEntity(dtoSubCategoria)
                );
            }
        }
        return entity;
    }

    public static DtoCategoria mapEntitytoDto(Categoria entity) throws Exception {
        FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.CATEGORIA);
        DtoCategoria dto = (DtoCategoria) fabrica.generarDto();
        dto.set_id( entity.get_id() );
        dto.setNombre( entity.getNombre() );
        dto.setActivo( entity.getActivo() );
        dto.setCreado_el( entity.getCreado_el() );
        dto.setModificado_el( entity.getModificado_el() );

        if (Objects.nonNull(entity.getSubCategorias()) && entity.getSubCategorias().size() > 0){
            for (SubCategoria subCategoria : entity.getSubCategorias()){
                dto.addSubCategoria(
                        SubCategoriaMapper.mapEntityToDto(subCategoria)
                );
            }
        }
        return dto;
    }

}
