package mercadeoucab.fabricas.fabricasComandoConcretos;

import mercadeoucab.comandos.ComandoAbstracto;
import mercadeoucab.comandos.Ocupacion.*;
import mercadeoucab.fabricas.FabricaComandosAbstractos;

public class FabricaComandosOcupacion extends FabricaComandosAbstractos {

    @Override
    public ComandoAbstracto comandoCrear() {
        return new ComandoRegistrarOcupacion();
    }

    @Override
    public ComandoAbstracto comandoConsultar() {
        return new ComandoObtenerOcupacion();
    }

    @Override
    public ComandoAbstracto comandoListar() {
        return new ComandoListarOcupaciones();
    }

    @Override
    public ComandoAbstracto comandoModificar() {
        return new ComandoActualizarOcupacion();
    }

    @Override
    public ComandoAbstracto comandoEliminar() {
        return new ComandoEliminarOcupacion();
    }
}
