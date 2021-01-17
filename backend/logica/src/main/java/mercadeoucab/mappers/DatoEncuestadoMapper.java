package mercadeoucab.mappers;

import mercadeoucab.dtos.*;
import mercadeoucab.entidades.DatoEncuestado;
import mercadeoucab.entidades.Hijo;
import mercadeoucab.entidades.Telefono;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DatoEncuestadoMapper {

    public static DatoEncuestado mapDtotoEntity(DtoDatoEncuestado dto){
        DatoEncuestado entity = new DatoEncuestado();
        entity.set_id( dto.get_id() );
        entity.setCreado_el( dto.getCreado_el() );
        entity.setModificado_el( dto.getModificado_el() );
        entity.setSegundoNombre( dto.getSegundoNombre() );
        entity.setSegundoapellido( dto.getSegundoapellido() );
        entity.setCedula( dto.getCedula() );
        entity.setMedioConexion( dto.getMedioConexion() );
        entity.setEdad( dto.getEdad() );
        entity.setGenero( dto.getGenero() );
        entity.setNive_economico(dto.getNive_economico() );
        entity.setNivelAcademico(dto.getNivelAcademico() );
        entity.setPersonasHogar( dto.getPersonasHogar() );
        if(Objects.nonNull(dto.getFk_lugar()))
            entity.setFk_lugar( ParroquiaMapper.mapDtoToEntity( dto.getFk_lugar() ) );
        if(Objects.nonNull(dto.getUsuario()))
            entity.setUsuario(UsuarioMapper.mapDtoToEntity(dto.getUsuario()));
        if(Objects.nonNull( dto.getOcupacion()) )
            entity.setOcupacion(OcupacionMapper.mapDtotoEntity(dto.getOcupacion()));
        if(!(dto.getHijos().isEmpty())){
            List<Hijo> hijos = new ArrayList<>();
            for(DtoHijo dtoHijo: dto.getHijos()){
                hijos.add(HijoMapper.mapDtotoEntity(dtoHijo));
            }
            entity.setHijos(hijos);
        }
        if(!(dto.getTelefonos().isEmpty())){
            List<Telefono> telefonos = new ArrayList<>();
            for(DtoTelefono dtoTelefono: dto.getTelefonos()){
                telefonos.add(null);
            }
            entity.setTelefonos(telefonos);
        }
        return entity;
    }

    public static DtoDatoEncuestado mapEntitytoDto(DatoEncuestado entity) throws Exception {
        DtoDatoEncuestado dto = new DtoDatoEncuestado();
        dto.set_id( entity.get_id() );
        dto.setCreado_el( entity.getCreado_el() );
        dto.setModificado_el( entity.getModificado_el() );
        dto.setSegundoNombre( entity.getSegundoNombre() );
        dto.setSegundoapellido( entity.getSegundoapellido() );
        dto.setCedula( entity.getCedula() );
        dto.setMedioConexion( entity.getMedioConexion() );
        dto.setEdad( entity.getEdad() );
        dto.setGenero( entity.getGenero() );
        dto.setNive_economico( entity.getNive_economico() );
        dto.setNivelAcademico( entity.getNivelAcademico() );
        dto.setPersonasHogar( entity.getPersonasHogar() );
        if(Objects.nonNull( entity.getFk_lugar()) )
            dto.setFk_lugar( ParroquiaMapper.mapEntityToDto( entity.getFk_lugar() ) );
        if(Objects.nonNull( entity.getUsuario()) )
            dto.setUsuario(UsuarioMapper.mapEntityToDto(entity.getUsuario()));
        if(Objects.nonNull( entity.getOcupacion()) )
            dto.setOcupacion(OcupacionMapper.mapEntitytoDto(entity.getOcupacion()));
        if(!(entity.getHijos().isEmpty())){
            List<DtoHijo> hijos = new ArrayList<>();
            for(Hijo hijo: entity.getHijos()){
                hijos.add(HijoMapper.mapEntitytoDto(hijo));
            }
            dto.setHijos(hijos);
        }
        if(!(entity.getTelefonos().isEmpty())){
            List<DtoTelefono> telefonos = new ArrayList<>();
            for( Telefono telefono: entity.getTelefonos() ){
                telefonos.add(null);
            }
            dto.setTelefonos(telefonos);
        }
        return dto;
    }
}
