package mercadeoucab.accesodatos;

import mercadeoucab.dtos.DtoUsuario;
import mercadeoucab.entidades.Estudio;
import mercadeoucab.entidades.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class DaoEstudio extends Dao<Estudio>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoEstudio( )
    {
        super( _handler );
    }

    public List<Estudio> estudiosAnalista(Usuario usuario){
        _em = _handler.getSession();
        List<Estudio> resultado = null;
        try {
            _handler.beginTransaction();
            TypedQuery<Estudio> estudios = this._em.createNamedQuery("estudios_de_un_analista",Estudio.class);
            estudios.setParameter("fk_usuario", usuario);
            resultado = estudios.getResultList();
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
