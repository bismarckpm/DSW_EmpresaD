package mercadeoucab.accesodatos;

import mercadeoucab.entidades.Presentacion;

import javax.persistence.EntityManager;

/**
 * Name: DaoPresentacion
 */
public class DaoPresentacion extends Dao<Presentacion>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoPresentacion(){super(_handler);}
}
