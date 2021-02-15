package mercadeoucab.fabricas.fabricasComandoConcretos;

import mercadeoucab.comandos.ComandoAbstracto;
import mercadeoucab.comandos.MuestraPoblacion.*;
import mercadeoucab.fabricas.FabricaComandosAbstractos;

public class FabricaComandosMuestraPoblacion extends FabricaComandosAbstractos {
    @Override
    public ComandoAbstracto comandoCrear() { return new ComandoRegistrarMuestraPoblacion(); }

    @Override
    public ComandoAbstracto comandoConsultar() {
        return new ComandoConsultarMuestraPoblacion();
    }

    @Override
    public ComandoAbstracto comandoListar() {
        return new ComandoListarMuestraPoblacion();
    }

    @Override
    public ComandoAbstracto comandoModificar() {
        return new ComandoActualizarMuestraPoblacion();
    }

    @Override
    public ComandoAbstracto comandoEliminar() { return new ComandoEliminarMuestraPoblacion(); }
}
