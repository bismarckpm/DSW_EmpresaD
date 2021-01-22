package mercadeoucab.mappers;

import mercadeoucab.dtos.DtoCategoria;
import mercadeoucab.dtos.DtoSubCategoria;
import mercadeoucab.entidades.Categoria;
import mercadeoucab.entidades.SubCategoria;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import org.omg.PortableInterceptor.SUCCESSFUL;

import java.util.Objects;

public class SubCategoriaMapper {

    public static SubCategoria mapDtoToEntity(DtoSubCategoria dtoSubCategoria){
        FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.SUBCATEGORIA);
        SubCategoria subCategoria = (SubCategoria) fabrica.generarEntidad() ;
        subCategoria.set_id(dtoSubCategoria.get_id());
        subCategoria.setNombre( dtoSubCategoria.getNombre());
        subCategoria.setActivo( dtoSubCategoria.getActivo());
        subCategoria.setModificado_el( dtoSubCategoria.getModificado_el());
        subCategoria.setCreado_el( dtoSubCategoria.getCreado_el());

        if (Objects.nonNull( dtoSubCategoria.getCategoria())){
            subCategoria.setCategoria(
                    CategoriaMapper.mapDtotoEntity( dtoSubCategoria.getCategoria())
            );
        }

        return subCategoria;
    }

    public static DtoSubCategoria mapEntityToDto( SubCategoria subCategoria) throws Exception {
        FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.SUBCATEGORIA);
        DtoSubCategoria dtoSubCategoria = (DtoSubCategoria) fabrica.generarDto();
        dtoSubCategoria.set_id(subCategoria.get_id());
        dtoSubCategoria.setNombre( subCategoria.getNombre());
        dtoSubCategoria.setActivo( subCategoria.getActivo());
        dtoSubCategoria.setModificado_el( subCategoria.getModificado_el());
        dtoSubCategoria.setCategoria(
                CategoriaMapper.mapEntitytoDto( subCategoria.getCategoria())
        );
        dtoSubCategoria.setCreado_el( subCategoria.getCreado_el());

        return dtoSubCategoria;
    }
}
