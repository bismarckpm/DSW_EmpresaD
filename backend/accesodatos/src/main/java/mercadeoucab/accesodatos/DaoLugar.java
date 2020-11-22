package mercadeoucab.accesodatos;

import mercadeoucab.entidades.Lugar;

import javax.persistence.EntityManager;

public class DaoLugar extends Dao<Lugar> {
    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoLugar( )
    {
        super( _handler );
    }
}
