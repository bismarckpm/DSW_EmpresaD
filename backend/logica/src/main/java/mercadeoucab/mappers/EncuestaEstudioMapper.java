package mercadeoucab.mappers;

import mercadeoucab.dtos.DtoEncuestaEstudio;
import mercadeoucab.dtos.DtoRespuesta;
import mercadeoucab.entidades.EncuestaEstudio;
import mercadeoucab.entidades.Respuesta;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.fabricas.FabricasConcretas.FabricaEncuestaEstudio;

import java.util.ArrayList;
import java.util.List;

public class EncuestaEstudioMapper {

    public static EncuestaEstudio mapDtotoEntity(DtoEncuestaEstudio dto){
        FabricaEncuestaEstudio fabrica = (FabricaEncuestaEstudio) FabricaAbstracta.getFactory(Fabricas.ENCUESTAESTUDIO);
        EncuestaEstudio entity = (EncuestaEstudio) fabrica.generarEntidad2();
        entity.set_id(dto.get_id());
        entity.setFk_estudio(
                EstudioMapper.mapDtotoEntity(dto.getFk_estudio())
        );
        entity.setFk_pregunta(
                PreguntaMapper.mapDtoToEntity(dto.getFk_pregunta())
        );
        if(!(dto.getRespuestas().isEmpty())){
            List<Respuesta> respuestas = new ArrayList<>();
            for(DtoRespuesta dtoRespuesta: dto.getRespuestas())
                respuestas.add(RespuestaMapper.mapDtoToEntity(dtoRespuesta));
            entity.setRespuestas(respuestas);
        }
        return entity;
    }

    public static DtoEncuestaEstudio mapEntitytoDto(EncuestaEstudio entity) throws Exception {
        FabricaEncuestaEstudio fabrica = (FabricaEncuestaEstudio) FabricaAbstracta.getFactory(Fabricas.ENCUESTAESTUDIO);
        DtoEncuestaEstudio dto = (DtoEncuestaEstudio) fabrica.generarDto();
        dto.set_id(entity.get_id());
        dto.setFk_estudio(
                EstudioMapper.mapEntitytoDto(entity.getFk_estudio())
        );
        dto.setFk_pregunta(
                PreguntaMapper.mapEntityToDto(entity.getFk_pregunta())
        );
        if(!(entity.getRespuestas().isEmpty())){
            List<DtoRespuesta> respuestas = new ArrayList<>();
            for(Respuesta respuesta: entity.getRespuestas())
                respuestas.add(RespuestaMapper.mapEntityToDto(respuesta));
            dto.setRespuestas(respuestas);
        }
        return dto;
    }
}
