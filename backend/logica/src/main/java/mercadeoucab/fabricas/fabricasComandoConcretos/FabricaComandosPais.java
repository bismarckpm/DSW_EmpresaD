package mercadeoucab.fabricas.fabricasComandoConcretos;

import mercadeoucab.comandos.ComandoAbstracto;
import mercadeoucab.comandos.Pais.*;
import mercadeoucab.fabricas.FabricaComandosAbstractos;

public class FabricaComandosPais extends FabricaComandosAbstractos {

    @Override
    public ComandoAbstracto comandoCrear() {
        return new ComandoAgregarPais();
    }

    @Override
    public ComandoAbstracto comandoConsultar() {
        return new ComandoObtenerPais();
    }

    @Override
    public ComandoAbstracto comandoListar() {
        return new ComandoListarPaises();
    }

    @Override
    public ComandoAbstracto comandoModificar() {
        return new ComandoActualizarPais();
    }

    @Override
    public ComandoAbstracto comandoEliminar() {
        return new ComandoEliminarPais();
    }
}
