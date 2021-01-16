package mercadeoucab.mappers;

import mercadeoucab.dtos.DtoSolicitud;
import mercadeoucab.entidades.Marca;
import mercadeoucab.entidades.Solicitud;
import mercadeoucab.entidades.Usuario;

import java.util.Objects;

public class SolicitudMapper {

    public static Solicitud mapDtoToEntity(DtoSolicitud dtoSolicitud){
        Solicitud solicitud = new Solicitud( dtoSolicitud.get_id());

        solicitud.setActivo( dtoSolicitud.getActivo());
        solicitud.setModificado_el( dtoSolicitud.getModificado_el());
        solicitud.setEstado( dtoSolicitud.getEstado());
        solicitud.setCreado_el( dtoSolicitud.getCreado_el());
        
        if (Objects.nonNull( dtoSolicitud.getUsuario())){
            solicitud.setUsuario(
                    new Usuario( dtoSolicitud.getUsuario().get_id())
            );
        }
        if (Objects.nonNull( dtoSolicitud.getPresentaciones())){
            solicitud.setMarca(
                    new Marca( dtoSolicitud.getMarca().get_id())
            );
        }

    }
}
