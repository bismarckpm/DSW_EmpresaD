package mercadeoucab.fabricas.fabricasComandoConcretos;

import mercadeoucab.comandos.ComandoAbstracto;
import mercadeoucab.comandos.Pregunta.*;
import mercadeoucab.fabricas.FabricaComandosAbstractos;

public class FabricaComandosPregunta extends FabricaComandosAbstractos {

    @Override
    public ComandoAbstracto comandoCrear() {
        return new ComandoRegistrarPregunta();
    }

    @Override
    public ComandoAbstracto comandoConsultar() {
        return new ComandoConsultarPregunta();
    }

    @Override
    public ComandoAbstracto comandoListar() {
        return new ComandoListarPreguntas();
    }

    @Override
    public ComandoAbstracto comandoModificar() {
        return new ComandoActualizarPregunta();
    }

    @Override
    public ComandoAbstracto comandoEliminar() {
        return new ComandoEliminarPregunta();
    }
}
