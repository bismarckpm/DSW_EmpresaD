package mercadeoucab.accesodatos;

import mercadeoucab.entidades.Solicitud;
import mercadeoucab.entidades.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Name: DoSolicitud
 */
public class DaoSolicitud extends Dao<Solicitud> {

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoSolicitud(){
        super( _handler);
    }

    /**
     * Name: solicitudesCliente
     * Description: lista las solicitudes de un cliente
     * @param usuario
     * @return List<Solicitud>
     */
    public List<Solicitud> solicitudesCliente(Usuario usuario){
        _em = _handler.getSession();
        List<Solicitud> resultado = null;
        try {
            _handler.beginTransaction();
            TypedQuery<Solicitud> solicitudes = this._em.createNamedQuery("solicitudes_de_un_cliente",Solicitud.class);
            solicitudes.setParameter("usuario", usuario);
            resultado = solicitudes.getResultList();
            _em.flush();
            _em.clear();
            _handler.finishTransaction();
        }
        catch (Exception e){
            String problema = e.getMessage();
        }
        return resultado;
    }

    /**
     * Name: solicitudesSegunEstado
     * Description: Lista las solicitudessegun el estado solicitado
     * @param estado
     * @return List<Solicitud>
     **/
    public List<Solicitud> solicitudesSegunEstado(String estado){
        _em = _handler.getSession();
        List<Solicitud> resultado = null;
        try {
            _handler.beginTransaction();
            TypedQuery<Solicitud> solicitudes = this._em.createNamedQuery("solicitudes_segun_estado",Solicitud.class);
            solicitudes.setParameter("estado", estado);
            resultado = solicitudes.getResultList();
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
