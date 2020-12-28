package mercadeoucab.accesodatos;

import mercadeoucab.dtos.DtoUsuario;
import mercadeoucab.entidades.DatoEncuestado;
import mercadeoucab.entidades.Estudio;
import mercadeoucab.entidades.Solicitud;
import mercadeoucab.entidades.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class DaoEstudio extends Dao<Estudio>{

    private EntityManager _em;
    static DaoHandler _handler = new DaoHandler();

    public DaoEstudio( )
    {
        super( _handler );
    }

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

    public List<Usuario> personasAplicanEstudio(Estudio estudio){
        _em = _handler.getSession();
        List<Usuario> resultado = null;
        try {
            _handler.beginTransaction();
            TypedQuery<Usuario> usuarios = this._em.createNamedQuery("personas_aplican", Usuario.class);
            usuarios.setParameter("nivelEcon", estudio.getFk_muestra_poblacion().getNivelEconomico());
            usuarios.setParameter("genero", estudio.getFk_muestra_poblacion().getGenero());
            //usuarios.setParameter("cantidadHijos", estudio.getFk_muestra_poblacion().getCantidadHijos());
            usuarios.setParameter("nivelAcademico", estudio.getFk_muestra_poblacion().getNivelAcademico());
            usuarios.setParameter("lugar", estudio.getFk_muestra_poblacion().getFk_lugar());
            usuarios.setParameter("ocupacion", estudio.getFk_muestra_poblacion().getFk_ocupacion());
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

    public  List<Estudio> preguntasSimilares(Solicitud solicitud){
        _em = _handler.getSession();
        List<Estudio> resultado = null;
        try {
            _handler.beginTransaction();
            TypedQuery<Estudio> estudios = this._em.createNamedQuery("preguntas_similares",Estudio.class);
            estudios.setParameter("presentaciones", solicitud.getPresentaciones());
            estudios.setParameter("tipos", solicitud.getTipos());
            estudios.setParameter("subcategorias",solicitud.getSubCategorias());
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
}
