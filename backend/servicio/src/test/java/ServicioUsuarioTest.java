import mercadeoucab.dtos.DtoUsuario;
import mercadeoucab.entidades.Usuario;
import mercadeoucab.servicio.ServicioUsuario;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ServicioUsuarioTest {

    @Test
    public void registrarUsuarioTest(){
        ServicioUsuario servicio = new ServicioUsuario();
        DtoUsuario dtoUsuario = new DtoUsuario();
        dtoUsuario.setNombre( "Yeferson");
        dtoUsuario.setApellido( "Soteldo");
        dtoUsuario.setEstado( "activo");
        dtoUsuario.setRol( "encuestado");
        dtoUsuario.setCorreo( "soteldios@gmail.com");
        DtoUsuario resultado = servicio.registrarUsuario( dtoUsuario);
        Assert.assertNotEquals( 0, resultado.get_id());
    }

    @Test
    public void actualizarUsuarioTest(){
        ServicioUsuario servicio = new ServicioUsuario();
        DtoUsuario dtoUsuario = new DtoUsuario();
        dtoUsuario.setNombre( "Yangel");
        dtoUsuario.setApellido( "Herrera");
        dtoUsuario.setEstado( "activo");
        dtoUsuario.setRol( "encuestado");
        dtoUsuario.setCorreo( "sanYangel@gmail.com");
        DtoUsuario paraActualizar = servicio.registrarUsuario( dtoUsuario);
        paraActualizar.setNombre("Juan");
        paraActualizar.setApellido( "Ricardo");
        paraActualizar.setEstado( "activo");
        DtoUsuario resultado = servicio.actualizarUsuario(
                paraActualizar.get_id(),
                paraActualizar
        );
        Assert.assertNotEquals( 0, resultado.get_id());
    }

    @Test
    public void eliminarUsuarioTest(){
        ServicioUsuario servicio = new ServicioUsuario();
        DtoUsuario dtoUsuario = new DtoUsuario();
        dtoUsuario.setNombre( "Arturo");
        dtoUsuario.setApellido( "Vidal");
        dtoUsuario.setEstado( "activo");
        dtoUsuario.setRol( "encuestado");
        dtoUsuario.setCorreo( "elrey@gmail.com");
        DtoUsuario paraBorrar = servicio.registrarUsuario( dtoUsuario);
        DtoUsuario resultado = servicio.eliminarUsuario( paraBorrar.get_id());
        Assert.assertEquals( paraBorrar.get_id(), resultado.get_id());
    }

    @Test
    public void obtenerUsuarioTest(){
        ServicioUsuario servicio = new ServicioUsuario();
        DtoUsuario dtoUsuario = new DtoUsuario();
        dtoUsuario.setNombre( "Juan");
        dtoUsuario.setApellido( "Arango");
        dtoUsuario.setEstado( "activo");
        dtoUsuario.setRol( "encuestado");
        dtoUsuario.setCorreo( "arangol@gmail.com");
        DtoUsuario paraConsultar = servicio.registrarUsuario( dtoUsuario);
        DtoUsuario resultado = servicio.obtenerUsuario( paraConsultar.get_id());
        Assert.assertEquals( paraConsultar.get_id(), resultado.get_id());
    }

    @Test
    public void listarUsuarioTest(){
        ServicioUsuario servicio = new ServicioUsuario();
        List<Usuario> resultado = servicio.listarUsuarios();
        Assert.assertNotNull(resultado);
    }
}
