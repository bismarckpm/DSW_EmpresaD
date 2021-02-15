package mercadeoucab.fabricas;

import mercadeoucab.comandos.ComandoAbstracto;
import mercadeoucab.fabricas.Enums.Comandos;
import mercadeoucab.fabricas.fabricasComandoConcretos.*;

public abstract class FabricaComandosAbstractos {

    private static final FabricaComandosCategoria COMANDOS_CATEGORIA = new FabricaComandosCategoria();
    private static final FabricaComandosDatoEncuestado COMANDOS_DATO_ENCUESTADO = new FabricaComandosDatoEncuestado();
    private static final FabricaComandosEstado COMANDOS_ESTADO = new FabricaComandosEstado();
    private static final FabricaComandosEstudio COMANDOS_ESTUDIO = new FabricaComandosEstudio();
    private static final FabricaComandosLDAP COMANDOS_LDAP = new FabricaComandosLDAP();
    private static final FabricaComandosMunicipio COMANDOS_MUNICIPIO = new FabricaComandosMunicipio();
    private static final FabricaComandosOcupacion COMANDOS_OCUPACION = new FabricaComandosOcupacion();
    private static final FabricaComandosOpcion COMANDOS_OPCION = new FabricaComandosOpcion();
    private static final FabricaComandosPais COMANDOS_PAIS = new FabricaComandosPais();
    private static final FabricaComandoParroquia COMANDO_PARROQUIA = new FabricaComandoParroquia();
    private static final FabricaComandosPregunta COMANDOS_PREGUNTA = new FabricaComandosPregunta();
    private static final FabricaComandosPresentacion COMANDOS_PRESENTACION = new FabricaComandosPresentacion();
    private static final FabricaComandoRespuesta COMANDO_RESPUESTA = new FabricaComandoRespuesta();
    private static final FabricaComandosSolicitud COMANDOS_SOLICITUD = new FabricaComandosSolicitud();
    private static final FabricaComandosSubCategoria COMANDOS_SUB_CATEGORIA = new FabricaComandosSubCategoria();
    private static final FabricaComandoTipo COMANDO_TIPO = new FabricaComandoTipo();
    private static final FabricaComandosUsuario COMANDOS_USUARIO = new FabricaComandosUsuario();
    private static final FabricaComandosMuestraPoblacion COMANDOS_MUESTRA_POBLACION = new FabricaComandosMuestraPoblacion();

    public static FabricaComandosAbstractos getFactory(Comandos tipo){
        FabricaComandosAbstractos fabricaComandosAbstractos = null;
        switch (tipo){
            case USUARIO:
                fabricaComandosAbstractos = COMANDOS_USUARIO;
                break;
            case TIPO:
                fabricaComandosAbstractos = COMANDO_TIPO;
                break;
            case SUBCATEGORIA:
                fabricaComandosAbstractos = COMANDOS_SUB_CATEGORIA;
                break;
            case SOLICITUD:
                fabricaComandosAbstractos = COMANDOS_SOLICITUD;
                break;
            case RESPUESTA:
                fabricaComandosAbstractos = COMANDO_RESPUESTA;
                break;
            case PRESENTACION:
                fabricaComandosAbstractos = COMANDOS_PRESENTACION;
                break;
            case PREGUNTA:
                fabricaComandosAbstractos = COMANDOS_PREGUNTA;
                break;
            case PARROQUIA:
                fabricaComandosAbstractos = COMANDO_PARROQUIA;
                break;
            case PAIS:
                fabricaComandosAbstractos = COMANDOS_PAIS;
                break;
            case OPCION:
                fabricaComandosAbstractos = COMANDOS_OPCION;
                break;
            case OCUPACION:
                fabricaComandosAbstractos = COMANDOS_OCUPACION;
                break;
            case MUNICIPIO:
                fabricaComandosAbstractos = COMANDOS_MUNICIPIO;
                break;
            case ESTUDIO:
                fabricaComandosAbstractos = COMANDOS_ESTUDIO;
                break;
            case ESTADO:
                fabricaComandosAbstractos = COMANDOS_ESTADO;
                break;
            case DATOENCUESTADO:
                fabricaComandosAbstractos = COMANDOS_DATO_ENCUESTADO;
                break;
            case LDAP:
                fabricaComandosAbstractos = COMANDOS_LDAP;
                break;
            case CATEGORIA:
                fabricaComandosAbstractos = COMANDOS_CATEGORIA;
                break;
            case MUESTRAPOBLACION:
                fabricaComandosAbstractos = COMANDOS_MUESTRA_POBLACION;
                break;
        }
        return fabricaComandosAbstractos;
    }

    public abstract ComandoAbstracto comandoCrear();

    public abstract ComandoAbstracto comandoConsultar();

    public abstract ComandoAbstracto comandoListar();

    public abstract ComandoAbstracto comandoModificar();

    public abstract ComandoAbstracto comandoEliminar();

}
