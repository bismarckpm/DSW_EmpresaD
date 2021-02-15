package mercadeoucab.directorioactivo;

import mercadeoucab.dtos.DtoDirectorioAUser;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;
import java.util.Hashtable;

/**
 * Name: DirectorioActivo
 */
public class DirectorioActivo {

    private DirContext _ldapContext;
    private final String _url = "ldap://127.0.0.1:10389";
    private final String _connType =  "simple";
    private String _directory ;//Indica la ruta de conexion
    private final String _userDirectory =  "cn=%s";
    private final String _user =  "admin";
    private final String _password =  "secret";

    /**
     *
     * @param rol
     */
    public DirectorioActivo(String rol){
        if(rol.equals("administrador")){
            this._directory="ou=Administradores,o=empresad";
        }else if(rol.equals("cliente")){
            this._directory="ou=Clientes,o=empresad";
        }else if(rol.equals("encuestado")){
            this._directory="ou=Encuestados,o=empresad";
        }else if(rol.equals("analista")){
            this._directory="ou=Analistas,o=empresad";
        }
    }

    /**
     * Name: connectLDAP
     * Description: Conecta a un usuario al directorio activo
     * @param user
     * @param password
     */
    private void connectLDAP(String user, String password)//user y password del administrador
    {
        try
        {
            Hashtable<String, String> environment = new Hashtable<String, String>();
            environment.put( Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory" );
            environment.put( Context.PROVIDER_URL, _url );
            environment.put( Context.SECURITY_AUTHENTICATION, _connType );
            environment.put( Context.SECURITY_PRINCIPAL, String.format( "uid=%s,ou=system", user ) );//Uid del usuario administrador
            environment.put( Context.SECURITY_CREDENTIALS, password );
            _ldapContext = new InitialDirContext( environment );
        }
        catch ( Exception e )
        {

        }
    }

    /**
     * name: disconnectLDAP
     * Description: Desconecta del directorio activo
     */
    private void disconnectLDAP()
    {
        try
        {
            _ldapContext.close();
        }
        catch ( Exception e )
        {

        }
    }

    /**
     * Name: addEntryToLdap
     * Descriptiom: agrega un nuevo usuario al arbol del directorio activo
     * @param user
     *
     */
    public void addEntryToLdap(DtoDirectorioAUser user)
    {

        try
        {
            connectLDAP( _user, _password );
            Attribute oc = new BasicAttribute( "objectClass" );
            oc.add( "top" );
            oc.add( "person" );
            BasicAttributes entry = new BasicAttributes();
            entry.put( oc );
            entry.put( new BasicAttribute( "cn", user.getCorreo() ) );
            entry.put( new BasicAttribute( "surname", user.getEstado() ) );
            entry.put( new BasicAttribute( "userPassword",user.getPassword()));
            _ldapContext.createSubcontext( String.format( _userDirectory + "," + _directory, user.getCorreo()), entry );

        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        finally {
            disconnectLDAP();
        }
    }


    /**
     * Name: deleteEntry
     * Description: actualiza el estqado del valor surname a bloqueado
     *              ya que este es el equivalente al atributo de usuario estado.
     * @param user
     */
    public void deleteEntry(DtoDirectorioAUser user)
    {
        try
        {
            connectLDAP( _user, _password );
            Attributes atbs = new BasicAttributes();
            Attribute atb = new BasicAttribute("surname","bloqueado");
            atbs.put(atb);

            _ldapContext.modifyAttributes( String.format(_userDirectory + "," + _directory, user.getCorreo())
                    , DirContext.REPLACE_ATTRIBUTE,atbs );
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            disconnectLDAP();
        }
    }

    /**
     * Name: updateEntry
     * Description: actuaiza un registro dentro del directorio activo
     * @param user
     * @param COR
     * @param CON
     * @param EST
     */
    public void updateEntry(DtoDirectorioAUser user,String COR,String CON,String EST){
        try {
            String correoB=user.getCorreo();
            if(COR!=null) {
                cambiarRDN(user, COR);
                connectLDAP(_user, _password);
                Attributes attributes = this._ldapContext.getAttributes(String.format(_userDirectory + "," + _directory, COR));
                Attribute attribute = new BasicAttribute("cn", user.getCorreo());
                ModificationItem[] item = new ModificationItem[1];
                item[0] = new ModificationItem(DirContext.REMOVE_ATTRIBUTE,
                        attribute);
                attributes.put(attribute);
                this._ldapContext.modifyAttributes(String.format(_userDirectory + "," + _directory, COR), item);
                correoB=COR;
            }
            if (CON !=null){
                connectLDAP(_user, _password);
                ModificationItem[] modificationItems = new ModificationItem[ 1 ];
                modificationItems[ 0 ] = new ModificationItem( DirContext.REPLACE_ATTRIBUTE,
                        new BasicAttribute( "userPassword", CON
                        ) );
                _ldapContext.modifyAttributes(String.format(_userDirectory + "," + _directory, correoB
                ), modificationItems );
            }
            if (EST !=null){
                connectLDAP( _user, _password );
                Attributes atbs = new BasicAttributes();
                Attribute atb = new BasicAttribute("surname",EST);
                atbs.put(atb);

                _ldapContext.modifyAttributes( String.format(_userDirectory + "," + _directory, correoB)
                        , DirContext.REPLACE_ATTRIBUTE,atbs );
            }


        }catch (NamingException e){
            e.printStackTrace();
        }finally {
            disconnectLDAP();
        }

    }

    /**
     * Name: displayAttributes
     * @param attributes
     */
    public void displayAttributes(Attributes attributes) {
        if (attributes != null) {
            try {
                for (NamingEnumeration e = attributes.getAll(); e.hasMore();) {
                    Attribute attr = (Attribute) e.next();
                    System.out.println("Attribute name: " + attr.getID());

                    for (NamingEnumeration n = attr.getAll(); n.hasMore(); System.out
                            .println("value: " + n.next()))
                        ;
                }
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Name: cambiarRDN
     * @param user
     * @param COR
     */
    public void cambiarRDN(DtoDirectorioAUser user,String COR){
        try {
            // Configurar el entorno para crear el contexto inicial
            Hashtable <String, Object> env = new Hashtable <String, Object> ();
            env.put (Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory" );
            env.put (Context.PROVIDER_URL, this._url);
            // Establece la propiedad para mantener RDN
            env.put ( "java.naming.ldap.deleteRDN" , "false" );

            // Crear contexto inicial
            DirContext ctx = new InitialDirContext (env);
            // Realizar el cambio de nombre
            String BcnRDN=String.format(_userDirectory + ","+_directory,user.getCorreo());
            String CcnRDN=String.format(_userDirectory + ","+_directory,COR);
            ctx.rename(BcnRDN , CcnRDN);
            ctx.close ();

        }catch (NamingException e){
            e.printStackTrace();
        }
    }

    /**
     * Name: getEntry
     * Description:obtiene un registro del DA
     * @param user
     */
    public void getEntry(DtoDirectorioAUser user)
    {
        try
        {
            connectLDAP( _user, _password );
            SearchControls searcCon = new SearchControls();
            searcCon.setSearchScope( SearchControls.SUBTREE_SCOPE );
            NamingEnumeration results =
                    _ldapContext.search( _directory, String.format(_userDirectory, user.getCorreo()), searcCon );
           /* if ( results != null )
            {
                while ( results.hasMore() )
                {
                    SearchResult res = ( SearchResult ) results.next();
                    Attributes atbs = res.getAttributes();
                    Attribute atb = atbs.get( "cn" );
                    String name = ( String ) atb.get();
                    String pwd=new String((byte[])atbs.get("userpassword").get()) ;
                    System.out.println("Correo=" +atb);
                }
            }
            else
            {
                System.out.println( "fail" );
            }*/
        }
        catch ( Exception exception )
        {
            exception.printStackTrace();
        }
        finally
        {
            disconnectLDAP();
        }
    }

    /**
     * Name: userAuthentication
     * Description: autentica las credenciales de un usuario contra el DA
     * @param user
     * @return
     */
    public Boolean userAuthentication(DtoDirectorioAUser user)
    {
        Boolean resultado = false;
        connectLDAP( _user, _password );
        try {
            Hashtable<String, String> environment = new Hashtable<String, String>();
            environment.put( Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory" );
            environment.put(Context.PROVIDER_URL, _url);
            environment.put(Context.SECURITY_AUTHENTICATION, _connType);
            environment.put(Context.SECURITY_PRINCIPAL, String.format( _userDirectory + "," + _directory, user.getCorreo()));
            environment.put(Context.SECURITY_CREDENTIALS, user.getPassword());
            _ldapContext = new InitialDirContext( environment );
            if ( _ldapContext != null) {
                resultado = true;
            }
        }
        catch ( Exception e )
        {
            String problema = e.getMessage();
        }finally
        {
            disconnectLDAP();
        }
        return resultado;
    }



}
