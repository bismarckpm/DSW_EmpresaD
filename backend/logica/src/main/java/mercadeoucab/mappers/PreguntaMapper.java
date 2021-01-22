package mercadeoucab.mappers;

import mercadeoucab.dtos.DtoOpcion;
import mercadeoucab.dtos.DtoPregunta;
import mercadeoucab.dtos.DtoUsuario;
import mercadeoucab.entidades.Opcion;
import mercadeoucab.entidades.Pregunta;
import mercadeoucab.entidades.Usuario;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;

import java.util.List;
import java.util.Objects;

public class PreguntaMapper {

    public static Pregunta mapDtoToEntity(DtoPregunta dtoPregunta){
        FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.PREGUNTA);
        Pregunta pregunta = (Pregunta) fabrica.generarEntidad();
        pregunta.set_id(dtoPregunta.get_id());
        pregunta.setNombrePregunta( dtoPregunta.getNombre_pregunta());
        pregunta.setRango( dtoPregunta.getRango());
        pregunta.setTipo( dtoPregunta.getTipo());
        pregunta.setActivo( dtoPregunta.getActivo());
        pregunta.setCreado_el( dtoPregunta.getCreado_el());
        pregunta.setModificado_el( dtoPregunta.getModificado_el());

        if (Objects.nonNull( dtoPregunta.getUsuarioDto())){
            pregunta.setUsuario(
                    UsuarioMapper.mapDtoToEntity( dtoPregunta.getUsuarioDto())
            );
        }

        if (Objects.nonNull( dtoPregunta.getOpciones())){
            for (DtoOpcion opcion:dtoPregunta.getOpciones()) {
                   pregunta.addOpcion(
                           OpcionMapper.mapDtotoEntity( opcion)
                   );
            }
        }

        return pregunta;
    }

    public static DtoPregunta mapEntityToDto ( Pregunta pregunta) throws Exception {
        FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.PREGUNTA);
        DtoPregunta dtoPregunta = (DtoPregunta) fabrica.generarDto();
        dtoPregunta.set_id(pregunta.get_id());
        dtoPregunta.setNombre_pregunta( pregunta.getNombrePregunta());
        dtoPregunta.setRango( pregunta.getRango());
        dtoPregunta.setTipo( pregunta.getTipo());
        dtoPregunta.setActivo( pregunta.getActivo());
        dtoPregunta.setCreado_el( pregunta.getCreado_el());
        dtoPregunta.setModificado_el( pregunta.getModificado_el());
        dtoPregunta.setUsuarioDto(
                UsuarioMapper.mapEntityToDto( pregunta.getUsuario())
        );

        for (Opcion opcion: pregunta.getOpciones()){
            dtoPregunta.addOpcion(
                    OpcionMapper.mapEntitytoDto( opcion)
            );
        }

        return dtoPregunta;
    }
}
