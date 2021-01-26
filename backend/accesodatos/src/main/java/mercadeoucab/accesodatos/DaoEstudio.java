package mercadeoucab.accesodatos;

import mercadeoucab.dtos.DtoUsuario;
import mercadeoucab.entidades.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

/**
 * Name: DaoEstudio
 */
public class DaoEstudio extends Dao<Estudio>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoEstudio( )
    {
        super( _handler );
    }

    /**
     * Name: estudiosAnalista
     * Description: retorna los estudios asignados a un analista
     * @param usuario
     * @return List<Estudio>
     */
    public List<Estudio> estudiosAnalista(Usuario usuario){
        _em = _handler.getSession();
        List<Estudio> resultado = null;
        try {
            _handler.beginTransaction();
            TypedQuery<Estudio> estudios = this._em.createNamedQuery("estudios_de_un_analista",Estudio.class);
            estudios.setParameter("fk_usuario", usuario);
            resultado = estudios.getResultList();
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
     * Name: estudiosAplicanUsuario
     * Description: Retorna todos los estudios a los que aplica un usuario
     * @param datoEncuestado
     * @return List<Estudio>
     */
    public List<Estudio> estudiosAplicanUsuario(DatoEncuestado datoEncuestado){
        _em = _handler.getSession();
        List<Estudio> resultado = null;
        try {
            _handler.beginTransaction();
            TypedQuery<Estudio> estudios = this._em.createNamedQuery("estudios_aplican_encuestado",Estudio.class);
            estudios.setParameter("cantidadHijos", datoEncuestado.getHijos().size());
            estudios.setParameter("genero", datoEncuestado.getGenero());
            estudios.setParameter("nivelAcademico", datoEncuestado.getNivelAcademico());
            estudios.setParameter("nivelEconomico", datoEncuestado.getNive_economico());
            estudios.setParameter("lugar", datoEncuestado.getFk_lugar());
            estudios.setParameter("ocupacion", datoEncuestado.getOcupacion());
            LocalDate ahora = LocalDate.now();
            Period periodo = Period.between( datoEncuestado.getEdad().toLocalDate(),ahora);
            estudios.setParameter("edad", periodo.getYears());
            resultado = estudios.getResultList();
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
     * Name: personasAplicanEstudio
     * Description: Retorna las personas que aplican a un estudio
     * @param estudio
     * @return List<Usuario>
     */
    public List<Usuario> personasAplicanEstudio(Estudio estudio){
        _em = _handler.getSession();
        List<Usuario> resultado = null;
        try {
            _handler.beginTransaction();
            TypedQuery<Usuario> usuarios = this._em.createNamedQuery("personas_aplican", Usuario.class);
            usuarios.setParameter("nivelEcon", estudio.getSolicitud().getFk_muestra_poblacion().getNivelEconomico());
            usuarios.setParameter("genero", estudio.getSolicitud().getFk_muestra_poblacion().getGenero());
            //usuarios.setParameter("cantidadHijos", estudio.getFk_muestra_poblacion().getCantidadHijos());
            usuarios.setParameter("nivelAcademico", estudio.getSolicitud().getFk_muestra_poblacion().getNivelAcademico());
            usuarios.setParameter("lugar", estudio.getSolicitud().getFk_muestra_poblacion().getFk_lugar());
            usuarios.setParameter("ocupacion", estudio.getSolicitud().getFk_muestra_poblacion().getFk_ocupacion());
            //usuarios.setParameter("edadInicial", estudio.getFk_muestra_poblacion().getRangoEdadInicio());
            //usuarios.setParameter("edadFinal", estudio.getFk_muestra_poblacion().getRangoEdadFin());
            resultado = usuarios.getResultList();
            _em.flush();
            _em.clear();
            _handler.finishTransaction();
        }
        catch (Exception e){
            String problema = e.getMessage();
            System.out.println(problema);
        }
        return resultado;
    }

    /**
     * Name: preguntasSimilares
     * Description: Lista las preguntas similares que se puedan recomendar a un estudio
     * @param solicitud
     * @return List<Estudio>
     */
    public  List<Estudio> preguntasSimilares(Solicitud solicitud){
        _em = _handler.getSession();
        List<Estudio> resultado = null;
        try {
            _handler.beginTransaction();
            TypedQuery<Estudio> estudios = this._em.createNamedQuery("preguntas_similares",Estudio.class);
            estudios.setParameter("presentaciones", solicitud.getPresentaciones());
            resultado = estudios.getResultList();
            _em.flush();
            _em.clear();
            _handler.finishTransaction();
        }
        catch (Exception e){
            String problema = e.getMessage();
            System.out.println(problema);
        }
        return  resultado;
    }

    /**
     * Name: poblacionesSimilares
     * Description: lista las poblaciones que fueron objeto de estudio y son similares al estudio actual
     * @param solicitud
     * @return
     */
    public  List<MuestraPoblacion> poblacionesSimilares(Solicitud solicitud){
        _em = _handler.getSession();
        List<MuestraPoblacion> resultado = null;
        try {
            _handler.beginTransaction();
            TypedQuery<MuestraPoblacion> muestras = this._em.createNamedQuery("poblaciones_similares",MuestraPoblacion.class);
            muestras.setParameter("presentaciones", solicitud.getPresentaciones());
            resultado = muestras.getResultList();
            _em.flush();
            _em.clear();
            _handler.finishTransaction();
        }
        catch (Exception e){
            String problema = e.getMessage();
            System.out.println(problema);
        }
        return  resultado;
    }
}
