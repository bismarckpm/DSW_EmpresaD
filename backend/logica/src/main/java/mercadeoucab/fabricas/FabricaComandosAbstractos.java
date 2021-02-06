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

    public static FabricaComandosAbstractos getFactory(Comandos tipo){
        FabricaComandosAbstractos fabricaComandosAbstractos = null;
        switch (tipo){
            case USUARIO:
                break;
            case TIPO:
                break;
            case SUBCATEGORIA:
                break;
            case SOLICITUD:
                break;
            case RESPUESTA:
                break;
            case PRESENTACION:
                break;
            case PREGUNTA:
                break;
            case PARROQUIA:
                break;
            case PAIS:
                break;
            case OPCION:
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
        }
        return fabricaComandosAbstractos;
    }

    public abstract ComandoAbstracto comandoCrear();

    public abstract ComandoAbstracto comandoConsultar();

    public abstract ComandoAbstracto comandoListar();

    public abstract ComandoAbstracto comandoModificar();

    public abstract ComandoAbstracto comandoEliminar();

}
