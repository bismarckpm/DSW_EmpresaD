package mercadeoucab.mappers;

import mercadeoucab.dtos.DtoPresentacion;
import mercadeoucab.dtos.DtoSolicitud;
import mercadeoucab.entidades.Presentacion;
import mercadeoucab.entidades.Solicitud;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;

import java.util.Objects;

public class SolicitudMapper {

    public static Solicitud mapDtoToEntity(DtoSolicitud dtoSolicitud) throws Exception{
        FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.SOLICITUD);
        Solicitud solicitud = (Solicitud) fabrica.generarEntidad();
        solicitud.set_id(dtoSolicitud.get_id());
        solicitud.setActivo( dtoSolicitud.getActivo());
        solicitud.setModificado_el( dtoSolicitud.getModificado_el());
        solicitud.setEstado( dtoSolicitud.getEstado());
        solicitud.setCreado_el( dtoSolicitud.getCreado_el());
        solicitud.setMarca(dtoSolicitud.getMarca());
        solicitud.setComentarios(dtoSolicitud.getComentarios());

        if (Objects.nonNull( dtoSolicitud.getUsuario())){
            solicitud.setUsuario(
                    UsuarioMapper.mapDtoToEntity(dtoSolicitud.getUsuario())
            );
        }

        if (Objects.nonNull( dtoSolicitud.getMuestraPoblacion() )){
            solicitud.setFk_muestra_poblacion(
                    MuestraPoblacionMapper.mapDtotoEntity(dtoSolicitud.getMuestraPoblacion())
            );
        }

        if (dtoSolicitud.getPresentaciones().size() > 0){
            for(DtoPresentacion presentacion: dtoSolicitud.getPresentaciones())
            {
                solicitud.addPresentacion(
                        PresentacionMapper.mapDtoToEntity(presentacion)
                );
            }
        }
        return solicitud;
    }

    public static DtoSolicitud mapEntityToDto( Solicitud solicitud) throws Exception {
        FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.SOLICITUD);
        DtoSolicitud dtoSolicitud = (DtoSolicitud) fabrica.generarDto();
        dtoSolicitud.set_id(solicitud.get_id());
        dtoSolicitud.setEstado( solicitud.getEstado());
        dtoSolicitud.setActivo( solicitud.getActivo());
        dtoSolicitud.setCreado_el( solicitud.getCreado_el());
        dtoSolicitud.setModificado_el( solicitud.getModificado_el());
        dtoSolicitud.setMarca(solicitud.getMarca());
        dtoSolicitud.setComentarios(solicitud.getComentarios());

        if ( Objects.nonNull( solicitud.getUsuario())){
            dtoSolicitud.setUsuario(
                    UsuarioMapper.mapEntityToDto( solicitud.getUsuario())
            );
        }

        if(Objects.nonNull(solicitud.getFk_muestra_poblacion())){
            dtoSolicitud.setMuestraPoblacion(
                    MuestraPoblacionMapper.mapEntitytoDto(solicitud.getFk_muestra_poblacion())
            );
        }

        if ( solicitud.getPresentaciones().size() > 0){
            for (Presentacion presentacion: solicitud.getPresentaciones()){
                dtoSolicitud.addPresentacion(
                        PresentacionMapper.mapEntityToDto( presentacion)
                );
            }
        }

        return dtoSolicitud;
    }
}
