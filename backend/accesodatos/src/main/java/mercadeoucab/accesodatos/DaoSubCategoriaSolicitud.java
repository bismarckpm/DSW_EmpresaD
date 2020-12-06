package mercadeoucab.accesodatos;

import mercadeoucab.entidades.SubCategoriaSolicitud;

import javax.persistence.EntityManager;

public class DaoSubCategoriaSolicitud extends Dao<SubCategoriaSolicitud> {
    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoSubCategoriaSolicitud(){
        super( _handler);
    }
}
