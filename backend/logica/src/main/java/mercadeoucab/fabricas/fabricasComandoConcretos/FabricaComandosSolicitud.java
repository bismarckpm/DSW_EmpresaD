package mercadeoucab.fabricas.fabricasComandoConcretos;

import mercadeoucab.comandos.ComandoAbstracto;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.comandos.Solicitud.*;
import mercadeoucab.fabricas.FabricaComandosAbstractos;

public class FabricaComandosSolicitud extends FabricaComandosAbstractos {
    @Override
    public ComandoAbstracto comandoCrear() {
        return new ComandoRegistrarSolicitud();
    }

    @Override
    public ComandoAbstracto comandoConsultar() {
        return new ComandoObtenerSolicitud();
    }

    @Override
    public ComandoAbstracto comandoListar() {
        return new ComandoListarSolicitud();
    }

    @Override
    public ComandoAbstracto comandoModificar() {
        return new ComandoActualizarSolicitud();
    }

    @Override
    public ComandoAbstracto comandoEliminar() {
        return new ComandoEliminarSolicitud();
    }

    public ComandoAbstracto comandoListarPorEstado(){
        return new ComandoListarSolicitudEstado();
    }

    public ComandoBase comandoPoblacionesRecomendadas(){
        return new ComandoPoblacionesRecomendadas();
    }

    public ComandoBase comandoPreguntasRecomendadas(){
        return new ComandoPreguntasRecomendadas();
    }

}
