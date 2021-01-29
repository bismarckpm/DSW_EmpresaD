package mercadeoucab.servicio;

import mercadeoucab.JWT.JWT;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

@ApplicationPath( "/api/v1" )
public class AplicacionBase extends Application {
    /**
     * Name: verifyParams
     * Desccription: valida que el objeto recibido no sea nulo
     * @param object
     */
    void verifyParams( Object object )
    {
        if ( object == null )
            throwException( Response.Status.BAD_REQUEST );
    }

    /**
     * Name: validateToken
     * Decription: Validad si un token es valido
     * @param token
     */
    void validateToken (String token){
        JWT.parseJWT(token);
    }

    /**
     * Name: generateToke
     * @param id
     * @param subject Correo del usuario
     */
    String generateToke(long id, String subject){
        JWT.createJWT(id, subject);
        return null;
    }

    void throwException( Response.Status status, Exception e )
    {
        throw new WebApplicationException(Response.status( status ).entity( e ).build() );
    }

    void throwException( Response.Status status )
    {
        throw new WebApplicationException( Response.status( status ).build() );
    }
}
