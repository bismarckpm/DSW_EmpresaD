package mercadeoucab.fabricas;

import mercadeoucab.comandos.ComandoAbstracto;
import mercadeoucab.fabricas.Enums.Comandos;
import mercadeoucab.fabricas.fabricasComandoConcretos.FabricaComandosCategoria;

public abstract class FabricaComandosAbstractos {

    private static final FabricaComandosCategoria COMANDOS_CATEGORIA = new FabricaComandosCategoria();

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
                break;
            case MUNICIPIO:
                break;
            case ESTUDIO:
                break;
            case ESTADO:
                break;
            case DATOENCUESTADO:
                break;
            case LDAP:
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
