package mercadeoucab.accesodatos;

import mercadeoucab.entidades.Opcion;

import javax.persistence.EntityManager;

/**
 * Name: DaoOpcion
 */
public class DaoOpcion extends Dao<Opcion>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoOpcion(){super(_handler);}
}
