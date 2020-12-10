package mercadeoucab.accesodatos;

import mercadeoucab.entidades.OcupacionMuestra;

import javax.persistence.EntityManager;


public class DaoOcupacionMuestra extends Dao<OcupacionMuestra>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoOcupacionMuestra(){super(_handler);}
}
