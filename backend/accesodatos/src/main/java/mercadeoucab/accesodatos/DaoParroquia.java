package mercadeoucab.accesodatos;

import mercadeoucab.entidades.Parroquia;

import javax.persistence.EntityManager;

/**
 * Name: DaoParroquia
 */
public class DaoParroquia extends Dao<Parroquia> {
    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoParroquia( )
    {
        super( _handler );
    }
}
