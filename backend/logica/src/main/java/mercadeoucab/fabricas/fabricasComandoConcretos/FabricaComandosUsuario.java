package mercadeoucab.fabricas.fabricasComandoConcretos;

import mercadeoucab.comandos.ComandoAbstracto;
import mercadeoucab.comandos.Usuario.*;
import mercadeoucab.fabricas.FabricaComandosAbstractos;

public class FabricaComandosUsuario extends FabricaComandosAbstractos {
    @Override
    public ComandoAbstracto comandoCrear() {
        return new ComandoRegistrarUsuario();
    }

    @Override
    public ComandoAbstracto comandoConsultar() {
        return new ComandoObtenerUsuario();
    }

    @Override
    public ComandoAbstracto comandoListar() {
        return new ComandoListarUsuarios();
    }

    @Override
    public ComandoAbstracto comandoModificar() {
        return new ComandoActualizarUsuario();
    }

    @Override
    public ComandoAbstracto comandoEliminar() {
        return new ComandoEliminarUsuario();
    }

    public ComandoAbstracto cambioClaveOlvidada(){
        return new ComandoCambioClaveOlvidada();
    }

    public ComandoAbstracto comandoEstudiosAnalista(){
        return new ComandoEstudiosAnalista();
    }

    public ComandoAbstracto comandoEstudiosAplicablesEncuestado(){
        return new ComandoEstudiosAplicablesEncuestado();
    }

    public ComandoAbstracto comandoListarAnalistas(){
        return new ComandoListarAnalistas();
    }

    public ComandoAbstracto comandoPeticionClaveOlvidada(){
        return new ComandoPeticionClaveOlvidada();
    }

    public ComandoAbstracto comandoPreguntasAdmin(){
        return new ComandoPreguntasAdministrador();
    }

    public ComandoAbstracto comandoSolicitudesCliente(){
        return new ComandoSolicitudesCliente();
    }
}
