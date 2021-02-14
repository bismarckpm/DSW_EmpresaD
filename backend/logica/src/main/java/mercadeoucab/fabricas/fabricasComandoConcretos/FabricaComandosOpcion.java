package mercadeoucab.fabricas.fabricasComandoConcretos;

import mercadeoucab.comandos.ComandoAbstracto;
import mercadeoucab.comandos.Ocupacion.ComandoEliminarOcupacion;
import mercadeoucab.comandos.Opcion.ComandoActualizarOpcion;
import mercadeoucab.comandos.Opcion.ComandoListarOpcion;
import mercadeoucab.comandos.Opcion.ComandoObtenerOpcion;
import mercadeoucab.comandos.Opcion.ComandoRegistrarOpcion;
import mercadeoucab.fabricas.FabricaComandosAbstractos;

public class FabricaComandosOpcion extends FabricaComandosAbstractos {

    @Override
    public ComandoAbstracto comandoCrear() {
        return new ComandoRegistrarOpcion();
    }

    @Override
    public ComandoAbstracto comandoConsultar() {
        return new ComandoObtenerOpcion();
    }

    @Override
    public ComandoAbstracto comandoListar() {
        return new ComandoListarOpcion();
    }

    @Override
    public ComandoAbstracto comandoModificar() {
        return new ComandoActualizarOpcion();
    }

    @Override
    public ComandoAbstracto comandoEliminar() {
        return new ComandoEliminarOcupacion();
    }
}
