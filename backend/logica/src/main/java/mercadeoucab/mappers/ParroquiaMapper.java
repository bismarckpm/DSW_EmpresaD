package mercadeoucab.mappers;

import mercadeoucab.dtos.DtoMunicipio;
import mercadeoucab.dtos.DtoParroquia;
import mercadeoucab.entidades.Municipio;
import mercadeoucab.entidades.Parroquia;

import java.util.Objects;

public class ParroquiaMapper {

    public static Parroquia mapDtoToEntity(DtoParroquia dtoParroquia){
        Parroquia parroquia = new Parroquia(dtoParroquia.get_id());

        parroquia.setNombre( dtoParroquia.getNombre());
        if (Objects.nonNull( dtoParroquia.getFk_municipio())) {
            parroquia.setFk_municipio(
                    new Municipio(dtoParroquia.getFk_municipio().get_id())
            );
        }
        parroquia.setValor_socio_economico( dtoParroquia.getValor_socio_economico());
        parroquia.setActivo( dtoParroquia.getActivo());
        parroquia.setModificado_el( dtoParroquia.getModificado_el());
        parroquia.setCreado_el( dtoParroquia.getCreado_el());
        return parroquia;
    }

    public static DtoParroquia mapEntityToDto( Parroquia parroquia) throws Exception {
        DtoParroquia dtoParroquia = new DtoParroquia( parroquia.get_id());

        dtoParroquia.setNombre( parroquia.getNombre());
        dtoParroquia.setFk_municipio(
                new DtoMunicipio( parroquia.getFk_municipio().get_id())
        );
        dtoParroquia.setValor_socio_economico( parroquia.getValor_socio_economico());
        dtoParroquia.setActivo( parroquia.getActivo());
        dtoParroquia.setModificado_el( parroquia.getModificado_el());
        dtoParroquia.setCreado_el( parroquia.getCreado_el());
        return dtoParroquia;
    }
}
