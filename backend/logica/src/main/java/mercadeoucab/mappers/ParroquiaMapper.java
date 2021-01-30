package mercadeoucab.mappers;

import mercadeoucab.dtos.DtoParroquia;
import mercadeoucab.entidades.Parroquia;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;

import java.util.Objects;

public class ParroquiaMapper {

    public static Parroquia mapDtoToEntity(DtoParroquia dtoParroquia){
        FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.PARROQUIA);
        Parroquia parroquia = (Parroquia) fabrica.generarEntidad();
        parroquia.set_id(dtoParroquia.get_id());

        parroquia.setNombre( dtoParroquia.getNombre());
        if (Objects.nonNull( dtoParroquia.getFk_municipio())) {
            parroquia.setFk_municipio(
                    MunicipioMapper.mapDtotoEntity(dtoParroquia.getFk_municipio())
            );
        }
        parroquia.setValor_socio_economico( dtoParroquia.getValor_socio_economico());
        parroquia.setActivo( dtoParroquia.getActivo());
        parroquia.setModificado_el( dtoParroquia.getModificado_el());
        parroquia.setCreado_el( dtoParroquia.getCreado_el());
        return parroquia;
    }

    public static DtoParroquia mapEntityToDto( Parroquia parroquia) throws Exception {
        FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.PARROQUIA);
        DtoParroquia dtoParroquia = (DtoParroquia) fabrica.generarDto();
        dtoParroquia.set_id(parroquia.get_id());

        dtoParroquia.setNombre( parroquia.getNombre());
        dtoParroquia.setFk_municipio(
                MunicipioMapper.mapEntitytoDto(parroquia.getFk_municipio())
        );
        dtoParroquia.setValor_socio_economico( parroquia.getValor_socio_economico());
        dtoParroquia.setActivo( parroquia.getActivo());
        dtoParroquia.setModificado_el( parroquia.getModificado_el());
        dtoParroquia.setCreado_el( parroquia.getCreado_el());
        return dtoParroquia;
    }
}
