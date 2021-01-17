package mercadeoucab.mappers;

import mercadeoucab.dtos.DtoCategoria;
import mercadeoucab.dtos.DtoSubCategoria;
import mercadeoucab.entidades.Categoria;
import mercadeoucab.entidades.SubCategoria;
import org.omg.PortableInterceptor.SUCCESSFUL;

import java.util.Objects;

public class SubCategoriaMapper {

    public static SubCategoria mapDtoToEntity(DtoSubCategoria dtoSubCategoria){
        SubCategoria subCategoria = new SubCategoria( dtoSubCategoria.get_id());

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
        DtoSubCategoria dtoSubCategoria = new DtoSubCategoria( subCategoria.get_id());

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
