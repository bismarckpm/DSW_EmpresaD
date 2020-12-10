package mercadeoucab.accesodatos;

import mercadeoucab.entidades.PresentacionSolicitud;

import javax.persistence.EntityManager;

public class DaoPresentacionSolicitud extends Dao<PresentacionSolicitud>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoPresentacionSolicitud(){super(_handler);}
}
