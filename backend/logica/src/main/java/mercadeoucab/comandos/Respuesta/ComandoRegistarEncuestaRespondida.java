package mercadeoucab.comandos.Respuesta;

import mercadeoucab.accesodatos.DaoEstudio;
import mercadeoucab.accesodatos.DaoRespuesta;
import mercadeoucab.comandos.ComandoAbstracto;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoEncuestaEstudio;
import mercadeoucab.dtos.DtoRespuesta;
import mercadeoucab.entidades.*;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.mappers.EncuestaEstudioMapper;
import mercadeoucab.mappers.OpcionMapper;
import mercadeoucab.mappers.UsuarioMapper;
import mercadeoucab.responses.ResponseGeneral;

import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class ComandoRegistarEncuestaRespondida extends ComandoAbstracto implements ComandoBase {

    private Response result;
    private DtoEncuestaEstudio dtoEncuestaEstudio;

    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try {

            FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.RESPUESTA);
            FabricaAbstracta fabricaEstudio = FabricaAbstracta.getFactory((Fabricas.ESTUDIO));

            DaoRespuesta dao = (DaoRespuesta) fabrica.generarDao();
            DaoEstudio daoEstudio = (DaoEstudio) fabricaEstudio.generarDao();
            
            Respuesta consulta = null;

            for(DtoRespuesta dtorespuesta: dtoEncuestaEstudio.getRespuestas()){

                Respuesta respuesta = (Respuesta) fabrica.generarEntidad();
                respuesta.setRespuesta(dtorespuesta.getRespuesta());

                EncuestaEstudio encuestaEstudio = EncuestaEstudioMapper.mapDtotoEntity(dtorespuesta.getDtoEncuestaEstudio());
                respuesta.setEncuesta_estudio(encuestaEstudio);

                Usuario usuario = UsuarioMapper.mapDtoToEntity(dtorespuesta.getDtousuario());
                respuesta.setFk_usuario(usuario);

                if(dtorespuesta.get_dtoopcion() != null){
                    Opcion opcion = OpcionMapper.mapDtotoEntity(dtorespuesta.get_dtoopcion());
                    respuesta.setFk_opcion(opcion);
                }
                respuesta.setActivo(1);
                respuesta.setCreado_el(new Date(Calendar.getInstance().getTime().getTime()));

                consulta = dao.insert(respuesta);
                
            }

            Respuesta respuesta = dao.find(consulta.get_id(), Respuesta.class);
            Estudio estudio = daoEstudio.find(respuesta.getEncuesta_estudio().getFk_estudio().get_id(), Estudio.class);

            int cantidadPreguntas = estudio.getEncuestaEstudio().size();

            ArrayList<Usuario> usuariosRespondieron = new ArrayList<>();


            for(EncuestaEstudio encuestaEstudio : estudio.getEncuestaEstudio()){

                ArrayList<Usuario> encontradosSinRepetir = new ArrayList<>();

                if(Objects.nonNull(encuestaEstudio.getRespuestas()) && encuestaEstudio.getRespuestas().size() > 0) {

                    for (Respuesta rest : encuestaEstudio.getRespuestas()) {

                        if (Objects.nonNull(encontradosSinRepetir) && encontradosSinRepetir.size() > 0)
                        {
                            boolean encontrado = false;
                            for (Usuario usuario : encontradosSinRepetir){

                                if (usuario.get_id() == rest.getFk_usuario().get_id())
                                {
                                    encontrado = true;
                                    break;
                                }

                            }
                            if (!encontrado)
                                encontradosSinRepetir.add(rest.getFk_usuario());
                        }
                        else
                            encontradosSinRepetir.add(rest.getFk_usuario());

                    }// Arreglo de respuestas en la encuesta

                } //Final If
                if (Objects.nonNull(encontradosSinRepetir))
                    usuariosRespondieron.addAll(encontradosSinRepetir);
            }// Arreglo de encuesta Estudio

            ArrayList<Usuario> total = new ArrayList<>();

            if (Objects.nonNull(usuariosRespondieron) && usuariosRespondieron.size() > 0){

                for (Usuario usuario : usuariosRespondieron){
                    int preguntasRespondidas = 0;
                    for (Usuario usuario1 : usuariosRespondieron)
                    {
                        if (usuario.get_id() == usuario1.get_id())
                            preguntasRespondidas ++;
                    }
                    if (preguntasRespondidas == cantidadPreguntas) {
                        if (!(total.contains(usuario)))
                            total.add(usuario);
                    }

                }// array de usuarios

            }// final del if

            System.out.println(total.size() + " USUARIOS QUE RESPONDIERON");
            if (total.size() >= estudio.getEncuestasEsperadas()){
                System.out.println("Encuesta Terminada");
                estudio.setEstado("Procesando");
                daoEstudio.update(estudio);
            }

            result = ResponseGeneral.SuccesMessage();
        }
        catch (Exception e){
            // CAMBIAR CUANDO SE MANEJEN LAS EXCEPCIONES PROPIAS
            String problema = e.getMessage();
            result = ResponseGeneral.Failure("Ha ocurrido un error");
        }
    }

    /**
     * Name: getResult
     *
     * @return Response
     */
    @Override
    public Response getResult() { return this.result; }

    public void setDtoEncuestaEstudio(DtoEncuestaEstudio dtoEncuestaEstudio) { this.dtoEncuestaEstudio = dtoEncuestaEstudio; }
}
