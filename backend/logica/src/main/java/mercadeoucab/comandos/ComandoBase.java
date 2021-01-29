package mercadeoucab.comandos;

import javax.ws.rs.core.Response;

/**
 *
 */
public interface ComandoBase {

    /**
     * Metodo para ejecutar los comandos
     */
    void execute();
    Response getResult();
}
