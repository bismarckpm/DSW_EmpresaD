package mercadeoucab.mappers;

import mercadeoucab.dtos.DtoSubCategoria;
import mercadeoucab.dtos.DtoTipo;
import mercadeoucab.entidades.SubCategoria;
import mercadeoucab.entidades.Tipo;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;

import java.util.Objects;

public class SubCategoriaMapper {

    public static SubCategoria mapDtoToEntity(DtoSubCategoria dtoSubCategoria) throws Exception {
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
        dtoSubCategoria.setCreado_el( subCategoria.getCreado_el());

        if (Objects.nonNull(subCategoria.getCategoria())) {
            dtoSubCategoria.setCategoria(
                    CategoriaMapper.mapEntitytoDto(subCategoria.getCategoria())
            );
        }


        return dtoSubCategoria;
    }

    public static SubCategoria mapDtoToEntity2(DtoSubCategoria dtoSubCategoria) throws Exception {
        FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.SUBCATEGORIA);
        SubCategoria subCategoria = (SubCategoria) fabrica.generarEntidad() ;
        subCategoria.set_id(dtoSubCategoria.get_id());
        subCategoria.setNombre( dtoSubCategoria.getNombre());
        subCategoria.setActivo( dtoSubCategoria.getActivo());
        subCategoria.setModificado_el( dtoSubCategoria.getModificado_el());
        subCategoria.setCreado_el( dtoSubCategoria.getCreado_el());


        if (Objects.nonNull(dtoSubCategoria.getTipoList()) && dtoSubCategoria.getTipoList().size() > 0){
            for (DtoTipo dtoTipo : dtoSubCategoria.getTipoList()){
                subCategoria.addTipo(
                        TipoMapper.mapDtoToEntity2(dtoTipo)
                );
            }
        }

        return subCategoria;
    }

    public static DtoSubCategoria mapEntityToDto2( SubCategoria subCategoria) throws Exception {
        FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.SUBCATEGORIA);
        DtoSubCategoria dtoSubCategoria = (DtoSubCategoria) fabrica.generarDto();
        dtoSubCategoria.set_id(subCategoria.get_id());
        dtoSubCategoria.setNombre( subCategoria.getNombre());
        dtoSubCategoria.setActivo( subCategoria.getActivo());
        dtoSubCategoria.setModificado_el( subCategoria.getModificado_el());
        dtoSubCategoria.setCreado_el( subCategoria.getCreado_el());

        if (Objects.nonNull(subCategoria.getTipoList()) && subCategoria.getTipoList().size() > 0){
            for (Tipo tipo : subCategoria.getTipoList()){
                dtoSubCategoria.addTipo(
                        TipoMapper.mapEntityToDto2(tipo)
                );
            }
        }


        return dtoSubCategoria;
    }
}
