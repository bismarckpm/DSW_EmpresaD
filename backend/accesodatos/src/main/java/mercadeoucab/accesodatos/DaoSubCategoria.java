package mercadeoucab.accesodatos;

import mercadeoucab.entidades.SubCategoria;

import javax.persistence.EntityManager;

public class DaoSubCategoria extends Dao<SubCategoria> {

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoSubCategoria(){
        super( _handler);
    }
}
