package mercadeoucab.accesodatos;

import mercadeoucab.entidades.Categoria;

import javax.persistence.EntityManager;

/**
 * Name: DaoCategoria
 */
public class DaoCategoria extends Dao<Categoria>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    /**
     *
     */
    public DaoCategoria( )
    {
        super( _handler );
    }
}
