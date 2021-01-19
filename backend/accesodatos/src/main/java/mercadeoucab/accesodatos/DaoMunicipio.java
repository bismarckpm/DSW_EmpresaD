package mercadeoucab.accesodatos;

import mercadeoucab.entidades.Municipio;

import javax.persistence.EntityManager;

/**
 * Name: DaoMunicipio
 */
public class DaoMunicipio extends Dao<Municipio> {
    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoMunicipio( )
    {
        super( _handler );
    }
}
