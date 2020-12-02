package mercadeoucab.accesodatos;

import mercadeoucab.entidades.Estado;

import javax.persistence.EntityManager;

public class DaoEstado extends Dao<Estado> {
    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoEstado( )
    {
        super( _handler );
    }
}
