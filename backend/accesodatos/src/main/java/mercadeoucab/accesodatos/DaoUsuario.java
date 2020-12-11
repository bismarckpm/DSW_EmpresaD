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
        try{
            TypedQuery<Usuario> usuario = this._em.createNamedQuery(
                    "obtenerUsuarioPorCorreo"
                    ,Usuario.class
            );

            resultado = usuario.setParameter("correo", correo)
                                .getSingleResult();
        }catch ( Exception e){
            String problema = e.getMessage();
        }
        return resultado;
    }
}
