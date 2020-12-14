package mercadeoucab.accesodatos;

import mercadeoucab.entidades.Pregunta;
import mercadeoucab.entidades.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class DaoPregunta extends Dao<Pregunta>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoPregunta(){
        super( _handler);
    }

    public List<Pregunta> obtenerPreguntasAdministrador(Usuario usuario){
        List<Pregunta> resultado =null;
        _em = _handler.getSession();
        try{
            _handler.beginTransaction();
            TypedQuery<Pregunta> preguntas = this._em.createNamedQuery("preguntas_de_un_administrador",Pregunta.class);
            preguntas.setParameter("fk_usuario", usuario);
            resultado = preguntas.getResultList();
            _em.flush();
            _em.clear();
            _handler.finishTransaction();
        }
        catch ( Exception e){
            String problema = e.getMessage();
        }
        return resultado;
    }

}
