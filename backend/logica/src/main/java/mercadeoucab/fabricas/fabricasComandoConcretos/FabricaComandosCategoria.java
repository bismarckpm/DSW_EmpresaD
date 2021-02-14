package mercadeoucab.fabricas.fabricasComandoConcretos;

import mercadeoucab.comandos.Categoria.*;
import mercadeoucab.comandos.ComandoAbstracto;
import mercadeoucab.fabricas.FabricaComandosAbstractos;

public class FabricaComandosCategoria extends FabricaComandosAbstractos {

    @Override
    public ComandoAbstracto comandoCrear() { return new ComandoAgregarCategoria(); }

    @Override
    public ComandoAbstracto comandoConsultar() { return new ComandoConsultarCategoria(); }

    @Override
    public ComandoAbstracto comandoListar() { return new ComandoListarCategorias(); }

    @Override
    public ComandoAbstracto comandoModificar() { return new ComandoActualizarCategoria(); }

    @Override
    public ComandoAbstracto comandoEliminar() { return new ComandoEliminarCategoria(); }

    public ComandoAbstracto comandoListaReversa() { return new ComandoListaReversa(); }
}
