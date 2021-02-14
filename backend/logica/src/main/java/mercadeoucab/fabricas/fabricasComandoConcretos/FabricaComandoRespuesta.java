package mercadeoucab.fabricas.fabricasComandoConcretos;

import mercadeoucab.comandos.ComandoAbstracto;
import mercadeoucab.comandos.Respuesta.*;
import mercadeoucab.fabricas.FabricaComandosAbstractos;

public class FabricaComandoRespuesta extends FabricaComandosAbstractos {

    @Override
    public ComandoAbstracto comandoCrear() {
        return new ComandoRegistrarRespuesta();
    }

    @Override
    public ComandoAbstracto comandoConsultar() {
        return new ComandoObtenerRespuesta();
    }

    @Override
    public ComandoAbstracto comandoListar() {
        return new ComandoListarRespuesta();
    }

    @Override
    public ComandoAbstracto comandoModificar() {
        return new ComandoActualizarRespuesta();
    }

    @Override
    public ComandoAbstracto comandoEliminar() {
        return new ComandoEliminarRespuesta();
    }
     public ComandoAbstracto comandoResponderEncuesta(){
        return new ComandoRegistarEncuestaRespondida();
     }
}
