package mercadeoucab.accesodatos;

import mercadeoucab.entidades.Ocupacion;

import javax.persistence.EntityManager;

/**
 * Name: DaoOcupacion
 */
public class DaoOcupacion extends Dao<Ocupacion>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoOcupacion(){super(_handler);}


}
