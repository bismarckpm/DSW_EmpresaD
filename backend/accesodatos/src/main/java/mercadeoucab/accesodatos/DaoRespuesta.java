package mercadeoucab.accesodatos;

import mercadeoucab.entidades.Estudio;
import mercadeoucab.entidades.Respuesta;
import mercadeoucab.entidades.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Name: DaoRespuesta
 */
public class DaoRespuesta extends Dao<Respuesta>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoRespuesta(){super(_handler);}

    /**
     * Name: usuariosRespondidoEncuesta
     * Description: lista los usurios que respondieron una encuesta
     * @param estudio
     * @return List<Long>
     */
    public List<Long> usuariosRespondidoEncuesta(Estudio estudio){
        _em = _handler.getSession();
        List<Long> resultado = null;
        try {
            _handler.beginTransaction();
            TypedQuery<Long> usuarios = this._em.createNamedQuery("usuarios_respondieron", Long.class);
            usuarios.setParameter("idEstudio", estudio.get_id());
            resultado = usuarios.getResultList();
            _em.flush();
            _em.clear();
            _handler.finishTransaction();
        }
        catch (Exception e){
            String problema = e.getMessage();
            System.out.println(problema);
        }
        return resultado;
    }


}
