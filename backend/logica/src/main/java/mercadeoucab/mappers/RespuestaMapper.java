package mercadeoucab.mappers;

import mercadeoucab.dtos.DtoEncuestaEstudio;
import mercadeoucab.dtos.DtoOpcion;
import mercadeoucab.dtos.DtoRespuesta;
import mercadeoucab.dtos.DtoUsuario;
import mercadeoucab.entidades.EncuestaEstudio;
import mercadeoucab.entidades.Opcion;
import mercadeoucab.entidades.Respuesta;
import mercadeoucab.entidades.Usuario;

import java.util.Objects;

public class RespuestaMapper {

    public static Respuesta mapDtoToEntity(DtoRespuesta dtoRespuesta){
        Respuesta respuesta = new Respuesta( dtoRespuesta.get_id());

        respuesta.setRespuesta( dtoRespuesta.getRespuesta());
        respuesta.setActivo( dtoRespuesta.getActivo());
        respuesta.setCreado_el( dtoRespuesta.getCreado_el());
        respuesta.setModificado_el( dtoRespuesta.getModificado_el());

        if (Objects.nonNull( dtoRespuesta.getDtoEncuestaEstudio())){
            respuesta.setEncuesta_estudio(
                    new EncuestaEstudio( dtoRespuesta.getDtoEncuestaEstudio().get_id())
            );
        }

        if (Objects.nonNull( dtoRespuesta.getDtousuario())){
            respuesta.setFk_usuario(
                    new Usuario( dtoRespuesta.get_id())
            );
        }

        if ( Objects.nonNull( dtoRespuesta.getDtoopcion())){
            respuesta.setFk_opcion(
                    new Opcion( dtoRespuesta.getDtoopcion().get_id())
            );
        }

        return respuesta;
    }

    public static DtoRespuesta mapEntityToDto( Respuesta respuesta) throws Exception {
        DtoRespuesta dtoRespuesta = new DtoRespuesta( respuesta.get_id());

        dtoRespuesta.setRespuesta( respuesta.getRespuesta());
        dtoRespuesta.setActivo( respuesta.getActivo());
        dtoRespuesta.setCreado_el( respuesta.getCreado_el());
        dtoRespuesta.setModificado_el( respuesta.getModificado_el());
        dtoRespuesta.setDtousuario(
                new DtoUsuario( respuesta.getFk_usuario().get_id())
        );
        dtoRespuesta.setDtoEncuestaEstudio(
                new DtoEncuestaEstudio( respuesta.getEncuesta_estudio().get_id())
        );
        dtoRespuesta.setDtoopcion(
                new DtoOpcion( respuesta.getFk_opcion().get_id())
        );

        return dtoRespuesta;
    }
}
