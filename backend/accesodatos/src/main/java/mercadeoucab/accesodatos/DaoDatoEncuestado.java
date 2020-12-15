package mercadeoucab.accesodatos;

import mercadeoucab.entidades.DatoEncuestado;
import mercadeoucab.entidades.Estudio;
import mercadeoucab.entidades.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class DaoDatoEncuestado extends Dao<DatoEncuestado>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoDatoEncuestado( )
    {
        super( _handler );
    }

    public DatoEncuestado datoEncuestado(Usuario usuario){
        _em = _handler.getSession();
        DatoEncuestado resultado = new DatoEncuestado();
        try {
            _handler.beginTransaction();
            TypedQuery<DatoEncuestado> datoEncuestado = this._em.createNamedQuery("obtener_datoEncuestado_encuestado",DatoEncuestado.class);
            datoEncuestado.setParameter("usuario", usuario);
            resultado = datoEncuestado.getResultList().get(0);
            _em.flush();
            _em.clear();
            _handler.finishTransaction();
        }
        catch (Exception e){
            String problema = e.getMessage();
        }
        return resultado;
    }
}
