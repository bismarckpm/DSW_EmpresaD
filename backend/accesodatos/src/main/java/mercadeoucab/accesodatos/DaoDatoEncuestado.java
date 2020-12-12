package mercadeoucab.accesodatos;

import mercadeoucab.entidades.DatoEncuestado;

import javax.persistence.EntityManager;

public class DaoDatoEncuestado extends Dao<DatoEncuestado>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoDatoEncuestado( )
    {
        super( _handler );
    }
}
