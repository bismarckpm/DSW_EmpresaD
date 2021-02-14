package mercadeoucab.fabricas.fabricasComandoConcretos;

import mercadeoucab.comandos.ComandoAbstracto;
import mercadeoucab.comandos.Parroquia.*;
import mercadeoucab.fabricas.FabricaComandosAbstractos;

public class FabricaComandoParroquia extends FabricaComandosAbstractos {

    @Override
    public ComandoAbstracto comandoCrear() {
        return new ComandoRegistrarParroquia();
    }

    @Override
    public ComandoAbstracto comandoConsultar() {
        return new ComandoConsultarParroquia();
    }

    @Override
    public ComandoAbstracto comandoListar() {
        return new ComandoListarParroquias();
    }

    @Override
    public ComandoAbstracto comandoModificar() {
        return new ComandoActualizarParroquia();
    }

    @Override
    public ComandoAbstracto comandoEliminar() {
        return new ComandoEliminarParroquia();
    }
}
