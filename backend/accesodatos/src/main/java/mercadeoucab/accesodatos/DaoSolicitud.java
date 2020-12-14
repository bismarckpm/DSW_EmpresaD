package mercadeoucab.accesodatos;

import mercadeoucab.entidades.Estudio;
import mercadeoucab.entidades.Solicitud;
import mercadeoucab.entidades.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class DaoSolicitud extends Dao<Solicitud> {

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoSolicitud(){
        super( _handler);
    }

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
}
