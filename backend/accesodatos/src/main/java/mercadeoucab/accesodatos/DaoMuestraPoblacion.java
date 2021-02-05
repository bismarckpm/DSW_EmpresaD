package mercadeoucab.accesodatos;

import mercadeoucab.entidades.MuestraPoblacion;

import javax.persistence.EntityManager;

/**
 * Name: DaoMuestraPoblacion
 */
public class DaoMuestraPoblacion extends Dao<MuestraPoblacion> {

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoMuestraPoblacion( )
    {
        super( _handler );
    }
}
