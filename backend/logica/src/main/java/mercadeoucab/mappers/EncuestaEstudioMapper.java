package mercadeoucab.mappers;

import mercadeoucab.dtos.DtoEncuestaEstudio;
import mercadeoucab.dtos.DtoRespuesta;
import mercadeoucab.entidades.EncuestaEstudio;
import mercadeoucab.entidades.Respuesta;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.fabricas.FabricasConcretas.FabricaEncuestaEstudio;


public class EncuestaEstudioMapper {

    public static EncuestaEstudio mapDtotoEntity(DtoEncuestaEstudio dto) throws Exception {
        FabricaEncuestaEstudio fabrica = (FabricaEncuestaEstudio) FabricaAbstracta.getFactory(Fabricas.ENCUESTAESTUDIO);
        EncuestaEstudio entity = fabrica.generarEntidad2();
        entity.set_id(dto.get_id());

        if(dto.getFk_pregunta() != null) {
            entity.setFk_pregunta(
                    PreguntaMapper.mapDtoToEntity(dto.getFk_pregunta())
            );
        }

        if(dto.getRespuestas().size() > 0){
            for (DtoRespuesta dtoRespuesta : dto.getRespuestas())
                entity.addRespuesta(
                        RespuestaMapper.mapDtoToEntity(dtoRespuesta)
                );
        }

        return entity;
    }

    public static DtoEncuestaEstudio mapEntitytoDto(EncuestaEstudio entity) throws Exception {
        FabricaEncuestaEstudio fabrica = (FabricaEncuestaEstudio) FabricaAbstracta.getFactory(Fabricas.ENCUESTAESTUDIO);

        DtoEncuestaEstudio dto = (DtoEncuestaEstudio) fabrica.generarDto();
        dto.set_id(entity.get_id());

        if(entity.getFk_pregunta() != null) {
            dto.setFk_pregunta(
                    PreguntaMapper.mapEntityToDto(entity.getFk_pregunta())
            );
        }

        if(entity.getRespuestas().size() > 0){
            for(Respuesta respuesta: entity.getRespuestas())
                dto.addRespuesta(
                        RespuestaMapper.mapEntityToDto(respuesta)
                );
        }

        return dto;
    }

}
