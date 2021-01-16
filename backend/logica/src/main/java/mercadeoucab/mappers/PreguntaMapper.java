package mercadeoucab.mappers;

import mercadeoucab.dtos.DtoOpcion;
import mercadeoucab.dtos.DtoPregunta;
import mercadeoucab.dtos.DtoUsuario;
import mercadeoucab.entidades.Opcion;
import mercadeoucab.entidades.Pregunta;
import mercadeoucab.entidades.Usuario;

import java.util.List;
import java.util.Objects;

public class PreguntaMapper {

    public static Pregunta mapDtoToEntity(DtoPregunta dtoPregunta){
        Pregunta pregunta = new Pregunta ( dtoPregunta.get_id());

        pregunta.setNombrePregunta( dtoPregunta.getNombre_pregunta());
        pregunta.setRango( dtoPregunta.getRango());
        pregunta.setTipo( dtoPregunta.getTipo());
        pregunta.setActivo( dtoPregunta.getActivo());
        pregunta.setCreado_el( dtoPregunta.getCreado_el());
        pregunta.setModificado_el( dtoPregunta.getModificado_el());

        if (Objects.nonNull( dtoPregunta.getUsuarioDto())){
            pregunta.setUsuario(
                    new Usuario( dtoPregunta.getUsuarioDto().get_id())
            );
        }

        if (Objects.nonNull( dtoPregunta.getOpciones())){
            for (DtoOpcion opcion:dtoPregunta.getOpciones()) {
                   pregunta.addOpcion( new Opcion(opcion.get_id()));
            }
        }

        return pregunta;
    }

    public static DtoPregunta mapEntityToDto ( Pregunta pregunta) throws Exception {
        DtoPregunta dtoPregunta = new DtoPregunta( pregunta.get_id());
        dtoPregunta.setNombre_pregunta( pregunta.getNombrePregunta());
        dtoPregunta.setRango( pregunta.getRango());
        dtoPregunta.setTipo( pregunta.getTipo());
        dtoPregunta.setActivo( pregunta.getActivo());
        dtoPregunta.setCreado_el( pregunta.getCreado_el());
        dtoPregunta.setModificado_el( pregunta.getModificado_el());
        dtoPregunta.setUsuarioDto(
                new DtoUsuario( pregunta.getUsuario().get_id())
        );

        for (Opcion opcion: pregunta.getOpciones()){
            dtoPregunta.addOpcion(
                    new DtoOpcion(opcion.get_id())
            );
        }

        return dtoPregunta;
    }
}