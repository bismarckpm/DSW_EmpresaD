package mercadeoucab.mappers;

import mercadeoucab.dtos.DtoSolicitud;
import mercadeoucab.dtos.DtoUsuario;
import mercadeoucab.entidades.Marca;
import mercadeoucab.entidades.Solicitud;
import mercadeoucab.entidades.Usuario;

import java.util.Objects;

public class SolicitudMapper {

    //FALTA ARREGLAR A LOS NUEVOS CAMBIOS
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
        return solicitud;
    }
    //FALTA ARREGLAR A LOS NUEVOS CAMBIOS
    public static DtoSolicitud mapEntityToDto( Solicitud solicitud) throws Exception {
        DtoSolicitud dtoSolicitud = new DtoSolicitud( solicitud.get_id());

        dtoSolicitud.setEstado( solicitud.getEstado());
        dtoSolicitud.setActivo( solicitud.getActivo());
        dtoSolicitud.setCreado_el( solicitud.getCreado_el());
        dtoSolicitud.setModificado_el( solicitud.getModificado_el());
        dtoSolicitud.setUsuario(
                new DtoUsuario( solicitud.getUsuario().get_id())
        );

        return dtoSolicitud;
    }
}
