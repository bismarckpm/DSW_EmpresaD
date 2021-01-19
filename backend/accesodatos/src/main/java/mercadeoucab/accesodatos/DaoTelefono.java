package mercadeoucab.accesodatos;

import mercadeoucab.entidades.Telefono;

import javax.persistence.EntityManager;

/**
 * Name DaoTelefono
 */
public class DaoTelefono extends Dao<Telefono> {

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoTelefono(){
        super( _handler);
    }
}
