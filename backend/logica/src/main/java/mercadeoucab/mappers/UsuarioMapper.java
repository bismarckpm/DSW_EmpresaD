package mercadeoucab.mappers;

import mercadeoucab.dtos.DtoUsuario;
import mercadeoucab.entidades.Usuario;

public class UsuarioMapper {

    public static Usuario mapDtoToEntity(DtoUsuario dtoUsuario){
        Usuario usuario = new Usuario( dtoUsuario.get_id());

        usuario.setCorreo( dtoUsuario.getCorreo());
        usuario.setActivo( dtoUsuario.getActivo());
        usuario.setModificado_el( dtoUsuario.getModificado_el());
        usuario.setRol( dtoUsuario.getRol());
        usuario.setEstado( dtoUsuario.getEstado());
        usuario.setNombre( dtoUsuario.getNombre());
        usuario.setApellido( dtoUsuario.getApellido());
        return usuario;
    }

    public static DtoUsuario mapEntityToDto ( Usuario usuario) throws Exception {
        DtoUsuario dtoUsuario = new DtoUsuario( usuario.get_id());

        dtoUsuario.setCorreo( usuario.getCorreo());
        dtoUsuario.setNombre( usuario.getNombre());
        dtoUsuario.setRol( usuario.getRol());
        dtoUsuario.setApellido( usuario.getApellido());
        dtoUsuario.setActivo( usuario.getActivo());
        dtoUsuario.setModificado_el( usuario.getModificado_el());
        dtoUsuario.setCreado_el( usuario.getCreado_el());

        return dtoUsuario;
    }
}
