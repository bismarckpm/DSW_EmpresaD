package mercadeoucab.accesodatos;

import mercadeoucab.entidades.Marca;

import javax.persistence.EntityManager;

/**
 * Name: DaoMarca
 */
public class DaoMarca extends Dao<Marca>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoMarca( )
    {
        super( _handler );
    }
}
