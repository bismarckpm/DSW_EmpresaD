package mercadeoucab.mappers;

import mercadeoucab.dtos.DtoUsuario;
import mercadeoucab.entidades.Usuario;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;

public class UsuarioMapper {

    public static Usuario mapDtoToEntity(DtoUsuario dtoUsuario){
        FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.USUARIO);
        Usuario usuario = (Usuario) fabrica.generarEntidad();
        usuario.set_id(dtoUsuario.get_id());
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
        FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.USUARIO);
        DtoUsuario dtoUsuario = (DtoUsuario) fabrica.generarDto();
        dtoUsuario.set_id(usuario.get_id());
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
