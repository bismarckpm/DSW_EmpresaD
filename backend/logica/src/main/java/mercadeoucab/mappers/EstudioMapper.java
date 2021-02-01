package mercadeoucab.mappers;

import mercadeoucab.dtos.*;
import mercadeoucab.entidades.EncuestaEstudio;
import mercadeoucab.entidades.Estudio;
import mercadeoucab.entidades.Pregunta;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EstudioMapper {

    public static Estudio mapDtotoEntity(DtoEstudio dto) throws Exception {
        FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.ESTUDIO);
        Estudio entity = (Estudio) fabrica.generarEntidad();
        entity.set_id(dto.get_id());
        entity.setCreado_el(dto.getCreado_el());
        entity.setModificado_el(dto.getModificado_el());
        entity.setEstado(dto.getEstado());
        entity.setTipo(dto.getTipo());
        entity.setEncuestasEsperadas(dto.getEncuestasEsperadas());

        if(Objects.nonNull(dto.getFk_usuario()))
            entity.setFk_usuario(UsuarioMapper.mapDtoToEntity(dto.getFk_usuario()));

        if(Objects.nonNull(dto.getSolicitud()))
            entity.setSolicitud(SolicitudMapper.mapDtoToEntity(dto.getSolicitud()));

        if(!(dto.getPreguntas().isEmpty())){
            List<Pregunta> preguntas = new ArrayList<>();
            for(DtoPregunta pregunta: dto.getPreguntas())
                preguntas.add(PreguntaMapper.mapDtoToEntity(pregunta));
            entity.setPreguntas(preguntas);
        }
        return entity;
    }

    public static DtoEstudio mapEntitytoDto(Estudio entity) throws Exception {
        FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.ESTUDIO);
        DtoEstudio dto = (DtoEstudio) fabrica.generarDto();
        dto.set_id(entity.get_id());
        dto.setCreado_el(entity.getCreado_el());
        dto.setModificado_el(entity.getModificado_el());
        dto.setEstado(entity.getEstado());
        dto.setTipo(entity.getTipo());
        dto.setEncuestasEsperadas(entity.getEncuestasEsperadas());
        if(Objects.nonNull(entity.getFk_usuario()))
            dto.setFk_usuario(UsuarioMapper.mapEntityToDto(entity.getFk_usuario()));
        if(Objects.nonNull(entity.getSolicitud()))
            dto.setSolicitud(SolicitudMapper.mapEntityToDto(entity.getSolicitud()));
        if(!(entity.getPreguntas().isEmpty())){
            List<DtoPregunta> preguntas = new ArrayList<>();
            for(Pregunta pregunta: entity.getPreguntas())
                preguntas.add(PreguntaMapper.mapEntityToDto(pregunta));
            dto.setPreguntas(preguntas);
        }
        if ( Objects.nonNull( entity.getEncuestaEstudio())){
            for (EncuestaEstudio encuestaEstudio: entity.getEncuestaEstudio()){
                dto.addEncuestaEstudio(
                        EncuestaEstudioMapper.mapEntitytoDto( encuestaEstudio)
                );
            }
        }
        return dto;
    }

    public static DtoEstudio mapEntitytoDto2(Estudio entity) throws Exception {
        FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.ESTUDIO);
        DtoEstudio dto = (DtoEstudio) fabrica.generarDto();
        dto.set_id(entity.get_id());
        dto.setCreado_el(entity.getCreado_el());
        dto.setModificado_el(entity.getModificado_el());
        dto.setEstado(entity.getEstado());
        dto.setTipo(entity.getTipo());
        dto.setEncuestasEsperadas(entity.getEncuestasEsperadas());
        if(Objects.nonNull(entity.getFk_usuario()))
            dto.setFk_usuario(UsuarioMapper.mapEntityToDto(entity.getFk_usuario()));
        if(Objects.nonNull(entity.getSolicitud()))
            dto.setSolicitud(SolicitudMapper.mapEntityToDto(entity.getSolicitud()));
        if(!(entity.getPreguntas().isEmpty())){
            List<DtoPregunta> preguntas = new ArrayList<>();
            for(Pregunta pregunta: entity.getPreguntas())
                preguntas.add(PreguntaMapper.mapEntityToDto(pregunta));
            dto.setPreguntas(preguntas);
        }
        if ( Objects.nonNull( entity.getEncuestaEstudio())){
            for (EncuestaEstudio encuestaEstudio: entity.getEncuestaEstudio()){
                dto.addEncuestaEstudio(
                        new DtoEncuestaEstudio( encuestaEstudio.get_id())
                );
            }
        }
        return dto;
    }
}
