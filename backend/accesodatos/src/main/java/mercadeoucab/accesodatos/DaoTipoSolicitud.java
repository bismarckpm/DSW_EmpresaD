package mercadeoucab.accesodatos;

import mercadeoucab.entidades.TipoSolicitud;

import javax.persistence.EntityManager;

public class DaoTipoSolicitud extends Dao<TipoSolicitud> {

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoTipoSolicitud(){
        super( _handler);
    }
}
