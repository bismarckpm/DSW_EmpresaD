package mercadeoucab.accesodatos;

import mercadeoucab.entidades.Pregunta;

import javax.persistence.EntityManager;

public class DaoPregunta extends Dao<Pregunta>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoPregunta(){
        super( _handler);
    }

}
