package mercadeoucab.fabricas;

import mercadeoucab.accesodatos.Dao;
import mercadeoucab.dtos.DtoBase;
import mercadeoucab.entidades.EntidadBase;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricasConcretas.*;
import mercadeoucab.responses.ResponseBase;

/**
 * Name: FabricaAbstracta
 */
public abstract class FabricaAbstracta {
    private static final FabricaCategoria FABRICA_CATEGORIA = new FabricaCategoria();
    private static final FabricaDatoEncuestado FABRICA_DATO_ENCUESTADO = new FabricaDatoEncuestado();
    private static final FabricaDirectorioAUser FABRICA_DIRECTORIO_A_USER = new FabricaDirectorioAUser();
    private static final FabricaEncuestaEstudio FABRICA_ENCUESTA_ESTUDIO = new FabricaEncuestaEstudio();
    private static final FabricaEstado FABRICA_ESTADO = new FabricaEstado();
    private static final FabricaEstudio FABRICA_ESTUDIO = new FabricaEstudio();
    private static final FabricaHijos FABRICA_HIJOS = new FabricaHijos();
    private static final FabricaMail FABRICA_MAIL = new FabricaMail();
    private static final FabricaMuestraPoblacion FABRICA_MUESTRA_POBLACION = new FabricaMuestraPoblacion();
    private static final FabricaMunicipio FABRICA_MUNICIPIO = new FabricaMunicipio();
    private static final FabricaOcupacion FABRICA_OCUPACION = new FabricaOcupacion();
    private static final FabricaOpcion FABRICA_OPCION = new FabricaOpcion();
    private static final FabricaPais FABRICA_PAIS = new FabricaPais();
    private static final FabricaParroquia FABRICA_PARROQUIA = new FabricaParroquia();
    private static final FabricaPreguntas FABRICA_PREGUNTAS = new FabricaPreguntas();
    private static final FabricaPresentacion FABRICA_PRESENTACION = new FabricaPresentacion();
    private static final FabricaRespuesta FABRICA_RESPUESTA = new FabricaRespuesta();
    private static final FabricaSolicitud FABRICA_SOLICITUD = new FabricaSolicitud();
    private static final FabricaSubCategoria FABRICA_SUB_CATEGORIA = new FabricaSubCategoria();
    private static final FabricaTelefono FABRICA_TELEFONO = new FabricaTelefono();
    private static final FabricaTipo FABRICA_TIPO = new FabricaTipo();
    private static final FabricaUsuario FABRICA_USUARIO = new FabricaUsuario();

    /**
     * Name: getFactory
     * Description: retorna una fabrica concreta
     * @param tipo
     * @return fabrica
     */
    public static FabricaAbstracta getFactory(Fabricas tipo){
        FabricaAbstracta fabrica = null;
        switch (tipo){
            case CATEGORIA:
                fabrica = FABRICA_CATEGORIA;
                break;
            case DATOENCUESTADO:
                fabrica = FABRICA_DATO_ENCUESTADO;
                break;
            case DIRECTORIOACTIVO:
                fabrica = FABRICA_DIRECTORIO_A_USER;
                break;
            case ENCUESTAESTUDIO:
                fabrica = FABRICA_ENCUESTA_ESTUDIO;
                break;
            case ESTADO:
                fabrica = FABRICA_ESTADO;
                break;
            case ESTUDIO:
                fabrica = FABRICA_ESTUDIO;
                break;
            case HIJO:
                fabrica = FABRICA_HIJOS;
                break;
            case MAIL:
                fabrica = FABRICA_MAIL;
                break;
            case MUESTRAPOBLACION:
                fabrica = FABRICA_MUESTRA_POBLACION;
                break;
            case MUNICIPIO:
                fabrica = FABRICA_MUNICIPIO;
                break;
            case OCUPACION:
                fabrica = FABRICA_OCUPACION;
                break;
            case OPCION:
                fabrica = FABRICA_OPCION;
                break;
            case PAIS:
                fabrica = FABRICA_PAIS;
                break;
            case PARROQUIA:
                fabrica = FABRICA_PARROQUIA;
                break;
            case PREGUNTA:
                fabrica = FABRICA_PREGUNTAS;
                break;
            case PRESENTACION:
                fabrica = FABRICA_PRESENTACION;
                break;
            case RESPUESTA:
                fabrica = FABRICA_RESPUESTA;
                break;
            case SOLICITUD:
                fabrica = FABRICA_SOLICITUD;
                break;
            case SUBCATEGORIA:
                fabrica = FABRICA_SUB_CATEGORIA;
                break;
            case TELEFONO:
                fabrica = FABRICA_TELEFONO;
                break;
            case TIPO:
                fabrica = FABRICA_TIPO;
                break;
            case USUARIO:
                fabrica = FABRICA_USUARIO;
                break;
        }
        return fabrica;
    }

    /**
     * Name: generarEntidad
     * Description: Crea una entidad
     * @return EntidadBase
     */
    public abstract EntidadBase generarEntidad();

    /**
     * Name: generarDto
     * Description: Genera un Dto
     * @return DtoBase
     */
    public abstract DtoBase generarDto();

    /**
     * Name: generarDao
     * Description: retorna la clase Dao
     * @return Dao
     */
    public abstract Dao generarDao();

    /**
     * Name: generarResponse
     * Description: genera un Response base
     * @return ResponseBase
     */
    public abstract ResponseBase generarResponse();

}
