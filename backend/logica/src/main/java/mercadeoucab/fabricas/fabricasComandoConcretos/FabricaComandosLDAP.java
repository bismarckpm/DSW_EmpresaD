package mercadeoucab.fabricas.fabricasComandoConcretos;

import mercadeoucab.comandos.ComandoAbstracto;
import mercadeoucab.comandos.Ldap.ComandoIniciarSesion;
import mercadeoucab.fabricas.FabricaComandosAbstractos;

public class FabricaComandosLDAP extends FabricaComandosAbstractos {
    @Override
    public ComandoAbstracto comandoCrear() {
        return null;
    }

    @Override
    public ComandoAbstracto comandoConsultar() {
        return null;
    }

    @Override
    public ComandoAbstracto comandoListar() {
        return null;
    }

    @Override
    public ComandoAbstracto comandoModificar() {
        return null;
    }

    @Override
    public ComandoAbstracto comandoEliminar() {
        return null;
    }

    public ComandoAbstracto comandoIniciarSesion(){
        return new ComandoIniciarSesion();
    }
}
