package mercadeoucab.mappers;

import mercadeoucab.dtos.DtoSolicitud;
import mercadeoucab.dtos.DtoSubCategoria;
import mercadeoucab.dtos.DtoUsuario;
import mercadeoucab.entidades.*;

import java.util.List;
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
        if ( Objects.nonNull( dtoSolicitud.getUsuario())){
            dtoSolicitud.setUsuario(
                    UsuarioMapper.mapEntityToDto( solicitud.getUsuario())
            );
        }
        if ( Objects.nonNull( dtoSolicitud.getSubCategorias())){
            for (SubCategoria subCategoria: solicitud.getSubCategorias()) {
                dtoSolicitud.addSubcategoria(
                        SubCategoriaMapper.mapEntityToDto( subCategoria)
                );
            }
        }
        if ( Objects.nonNull( dtoSolicitud.getPresentaciones())){
            for (Presentacion presentacion: solicitud.getPresentaciones()){
                dtoSolicitud.addPresentacion(
                        PresentacionMapper.mapEntityToDto( presentacion)
                );
            }
        }

        if ( Objects.nonNull( dtoSolicitud.getTipos())){
            for (Tipo tipo: solicitud.getTipos()){
                dtoSolicitud.addTipo(
                        TipoMapper.mapEntityToDto( tipo)
                );
            }
        }
        return dtoSolicitud;
    }
}
