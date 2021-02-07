package mercadeoucab.fabricas.fabricasComandoConcretos;

import mercadeoucab.comandos.ComandoAbstracto;
import mercadeoucab.comandos.Tipo.*;
import mercadeoucab.fabricas.FabricaComandosAbstractos;

public class FabricaComandoTipo extends FabricaComandosAbstractos {

    @Override
    public ComandoAbstracto comandoCrear() {
        return new ComandoRegistrarTipo();
    }

    @Override
    public ComandoAbstracto comandoConsultar() {
        return new ComandoObtenerTipo();
    }

    @Override
    public ComandoAbstracto comandoListar() {
        return new ComandoListarTipos();
    }

    @Override
    public ComandoAbstracto comandoModificar() {
        return new ComandoActualizarTipo();
    }

    @Override
    public ComandoAbstracto comandoEliminar() {
        return new ComandoEliminarTipo();
    }
}
