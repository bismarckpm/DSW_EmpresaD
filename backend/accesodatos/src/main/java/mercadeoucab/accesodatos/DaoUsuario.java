package mercadeoucab.accesodatos;

import mercadeoucab.entidades.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class DaoUsuario extends Dao<Usuario> {

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoUsuario(){
        super( _handler);
    }

    public  Usuario obtenerUsuarioPorCorreo( String correo){
        Usuario resultado = new Usuario();
        _em = _handler.getSession();
        try{
            _handler.beginTransaction();
            TypedQuery<Usuario> usuario = this._em.createNamedQuery("obtenerUsuarioPorCorreo",Usuario.class);
            usuario.setParameter("correo", correo);
            resultado = usuario.getSingleResult();
            System.out.println(resultado);
            System.out.println(resultado.getCorreo());
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
