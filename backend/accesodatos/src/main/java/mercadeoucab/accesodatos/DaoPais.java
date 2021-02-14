package mercadeoucab.accesodatos;

import mercadeoucab.entidades.Pais;

import javax.persistence.EntityManager;

/**
 * Name: DaoPais
 */
public class DaoPais extends Dao<Pais> {
    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoPais( )
    {
        super( _handler );
    }
}
