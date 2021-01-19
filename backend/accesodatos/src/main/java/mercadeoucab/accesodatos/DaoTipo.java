package mercadeoucab.accesodatos;

import mercadeoucab.entidades.Tipo;

import javax.persistence.EntityManager;

/**
 * Name: DaoTipo
 */
public class DaoTipo extends Dao<Tipo> {
    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoTipo(){
        super( _handler);
    }
}
