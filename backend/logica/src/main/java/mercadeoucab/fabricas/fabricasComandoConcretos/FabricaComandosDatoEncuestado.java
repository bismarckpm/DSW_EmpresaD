package mercadeoucab.fabricas.fabricasComandoConcretos;

import mercadeoucab.comandos.ComandoAbstracto;
import mercadeoucab.comandos.DatoEncuestado.*;
import mercadeoucab.fabricas.FabricaComandosAbstractos;

public class FabricaComandosDatoEncuestado extends FabricaComandosAbstractos {
    @Override
    public ComandoAbstracto comandoCrear() { return new ComandoRegistrarDatoEncuestado(); }

    @Override
    public ComandoAbstracto comandoConsultar() { return new ComandoConsultarDatoEncuestado(); }

    @Override
    public ComandoAbstracto comandoListar() { return new ComandoListarDatoEncuestados(); }

    @Override
    public ComandoAbstracto comandoModificar() { return new ComandoActualizarDatoEncuestado(); }

    @Override
    public ComandoAbstracto comandoEliminar() { return new ComandoEliminarDatoEncuestado(); }
}
