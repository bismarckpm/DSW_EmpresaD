package mercadeoucab.fabricas.fabricasComandoConcretos;

import mercadeoucab.comandos.ComandoAbstracto;
import mercadeoucab.comandos.Municipio.*;
import mercadeoucab.fabricas.FabricaComandosAbstractos;

public class FabricaComandosMunicipio extends FabricaComandosAbstractos {

    @Override
    public ComandoAbstracto comandoCrear() {
        return new ComandoRegistrarMunicipio();
    }

    @Override
    public ComandoAbstracto comandoConsultar() {
        return new ComandoObtenerMunicipio();
    }

    @Override
    public ComandoAbstracto comandoListar() {
        return new ComandoListarMunicipios();
    }

    @Override
    public ComandoAbstracto comandoModificar() {
        return new ComandoActualizarMunicipio();
    }

    @Override
    public ComandoAbstracto comandoEliminar() {
        return new ComandoEliminarMunicipio();
    }
}
