package mercadeoucab.fabricas.fabricasComandoConcretos;

import mercadeoucab.comandos.ComandoAbstracto;
import mercadeoucab.comandos.SubCategoria.*;
import mercadeoucab.fabricas.FabricaComandosAbstractos;

public class FabricaComandosSubCategoria extends FabricaComandosAbstractos {
    @Override
    public ComandoAbstracto comandoCrear() {
        return new ComandoRegistrarSubCategoria();
    }

    @Override
    public ComandoAbstracto comandoConsultar() {
        return new ComandoConsultarSubcategoria();
    }

    @Override
    public ComandoAbstracto comandoListar() {
        return new ComandoListarSubcategorias();
    }

    @Override
    public ComandoAbstracto comandoModificar() {
        return new ComandoActualizarSubCategoria();
    }

    @Override
    public ComandoAbstracto comandoEliminar() {
        return new ComandoEliminarSubCategoria();
    }
}
