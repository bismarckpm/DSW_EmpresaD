package mercadeoucab.fabricas.fabricasComandoConcretos;

import mercadeoucab.comandos.ComandoAbstracto;
import mercadeoucab.comandos.Estudio.*;
import mercadeoucab.fabricas.FabricaComandosAbstractos;

public class FabricaComandosEstudio extends FabricaComandosAbstractos {


    @Override
    public ComandoAbstracto comandoCrear() {
        return new ComandoAgregarEstudio();
    }

    @Override
    public ComandoAbstracto comandoConsultar() {
        return new ComandoConsultarEstudio();
    }

    @Override
    public ComandoAbstracto comandoListar() {
        return new ComandoListarEstudios();
    }

    @Override
    public ComandoAbstracto comandoModificar() {
        return new ComandoActualizarEstudio();
    }

    @Override
    public ComandoAbstracto comandoEliminar() {
        return new ComandoEliminarEstudio();
    }

    public ComandoAbstracto comandoUsuariosAplicanEncuesta(){
        return new ComandoUsuariosAplicanEncuesta();
    }

    public ComandoAbstracto ComandoUsuariosRespondieron(){
        return new ComandoUsuariosRespondieronEncuesta();
    }
}
