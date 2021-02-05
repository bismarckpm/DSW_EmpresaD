package mercadeoucab.accesodatos;

import mercadeoucab.entidades.Hijo;

import javax.persistence.EntityManager;

/**
 * Name: DaoHijo
 */
public class DaoHijo extends Dao<Hijo> {

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoHijo( )
    {
        super( _handler );
    }
}
