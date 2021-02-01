package mercadeoucab.mappers;

import mercadeoucab.dtos.DtoEncuestaEstudio;
import mercadeoucab.dtos.DtoOpcion;
import mercadeoucab.dtos.DtoRespuesta;
import mercadeoucab.dtos.DtoUsuario;
import mercadeoucab.entidades.EncuestaEstudio;
import mercadeoucab.entidades.Opcion;
import mercadeoucab.entidades.Respuesta;
import mercadeoucab.entidades.Usuario;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;

import java.util.Objects;

public class RespuestaMapper {

    public static Respuesta mapDtoToEntity(DtoRespuesta dtoRespuesta) throws Exception {
        FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.RESPUESTA);
        Respuesta respuesta = (Respuesta) fabrica.generarEntidad();
        respuesta.set_id(dtoRespuesta.get_id());
        respuesta.setRespuesta( dtoRespuesta.getRespuesta());
        respuesta.setActivo( dtoRespuesta.getActivo());
        respuesta.setCreado_el( dtoRespuesta.getCreado_el());
        respuesta.setModificado_el( dtoRespuesta.getModificado_el());

        if (Objects.nonNull( dtoRespuesta.getDtoEncuestaEstudio())){
            respuesta.setEncuesta_estudio(
                    EncuestaEstudioMapper.mapDtotoEntity(dtoRespuesta.getDtoEncuestaEstudio())
            );
        }

        if (Objects.nonNull( dtoRespuesta.getDtousuario())){
            respuesta.setFk_usuario(
                    UsuarioMapper.mapDtoToEntity(dtoRespuesta.getDtousuario())
            );
        }

        if ( Objects.nonNull( dtoRespuesta.getDtoopcion())){
            respuesta.setFk_opcion(
                    OpcionMapper.mapDtotoEntity(dtoRespuesta.get_dtoopcion())
            );
        }

        return respuesta;
    }

    public static DtoRespuesta mapEntityToDto( Respuesta respuesta) throws Exception {
        FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.RESPUESTA);
        DtoRespuesta dtoRespuesta = (DtoRespuesta) fabrica.generarDto();
        dtoRespuesta.set_id(respuesta.get_id());
        dtoRespuesta.setRespuesta( respuesta.getRespuesta());
        dtoRespuesta.setActivo( respuesta.getActivo());
        dtoRespuesta.setCreado_el( respuesta.getCreado_el());
        dtoRespuesta.setModificado_el( respuesta.getModificado_el());

        if (Objects.nonNull( respuesta.getEncuesta_estudio())){
            dtoRespuesta.setDtoEncuestaEstudio(
                    new DtoEncuestaEstudio( respuesta.getEncuesta_estudio().get_id())
            );
        }

        if (Objects.nonNull( respuesta.getFk_usuario())){
            dtoRespuesta.setDtousuario(
                    UsuarioMapper.mapEntityToDto(respuesta.getFk_usuario())
            );
        }

        if ( Objects.nonNull( respuesta.getFk_opcion())){
            dtoRespuesta.set_dtoopcion(
                    OpcionMapper.mapEntitytoDto(respuesta.getFk_opcion())
            );
        }

        return dtoRespuesta;
    }
}
