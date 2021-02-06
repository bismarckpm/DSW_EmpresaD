package mercadeoucab.fabricas.fabricasComandoConcretos;

import mercadeoucab.comandos.ComandoAbstracto;
import mercadeoucab.comandos.Presentacion.*;
import mercadeoucab.fabricas.FabricaComandosAbstractos;

public class FabricaComandosPresentacion extends FabricaComandosAbstractos {

    @Override
    public ComandoAbstracto comandoCrear() {
        return new ComandoRegistrarPresentacion();
    }

    @Override
    public ComandoAbstracto comandoConsultar() {
        return new ComandoObtenerPresentacion();
    }

    @Override
    public ComandoAbstracto comandoListar() {
        return new ComandoListarPresentacion();
    }

    @Override
    public ComandoAbstracto comandoModificar() {
        return new ComandoActualizarPresentacion();
    }

    @Override
    public ComandoAbstracto comandoEliminar() {
        return new ComandoEliminarPresentacion();
    }
}
