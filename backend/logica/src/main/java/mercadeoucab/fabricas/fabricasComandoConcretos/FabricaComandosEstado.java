package mercadeoucab.fabricas.fabricasComandoConcretos;

import mercadeoucab.comandos.ComandoAbstracto;
import mercadeoucab.comandos.Estado.*;
import mercadeoucab.fabricas.FabricaComandosAbstractos;

public class FabricaComandosEstado extends FabricaComandosAbstractos {

    @Override
    public ComandoAbstracto comandoCrear() { return new ComandoAgregarEstado(); }

    @Override
    public ComandoAbstracto comandoConsultar() { return new ComandoConsultarEstado(); }

    @Override
    public ComandoAbstracto comandoListar() { return new ComandoListarEstados(); }

    @Override
    public ComandoAbstracto comandoModificar() { return new ComandoActualizarEstado(); }

    @Override
    public ComandoAbstracto comandoEliminar() { return new ComandoEliminarEstado(); }
}
