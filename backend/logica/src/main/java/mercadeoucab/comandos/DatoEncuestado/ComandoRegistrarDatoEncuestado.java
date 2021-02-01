package mercadeoucab.comandos.DatoEncuestado;

import mercadeoucab.accesodatos.DaoDatoEncuestado;
import mercadeoucab.comandos.ComandoBase;
import mercadeoucab.dtos.DtoDatoEncuestado;
import mercadeoucab.dtos.DtoHijo;
import mercadeoucab.dtos.DtoTelefono;
import mercadeoucab.entidades.*;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.responses.ResponseGeneral;

import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.Calendar;

/**
 *
 * @author Oscar Marquez
 * @version 1.0
 * @since 2021-01-29
 */
public class ComandoRegistrarDatoEncuestado implements ComandoBase {

    private Response result;
    private DtoDatoEncuestado dtoDatoEncuestado;

    /**
     * Metodo para ejecutar los comandos
     */
    @Override
    public void execute() {
        try {
            FabricaAbstracta fabricaDatoEncuestado = FabricaAbstracta.getFactory( Fabricas.DATOENCUESTADO);
            FabricaAbstracta fabricaTelefono = FabricaAbstracta.getFactory( Fabricas.TELEFONO);
            FabricaAbstracta fabricaHijo = FabricaAbstracta.getFactory( Fabricas.HIJO);
            FabricaAbstracta fabricaUsuario = FabricaAbstracta.getFactory( Fabricas.USUARIO);
            FabricaAbstracta fabricaOcupacion = FabricaAbstracta.getFactory( Fabricas.OCUPACION);
            FabricaAbstracta fabricaParroquia = FabricaAbstracta.getFactory( Fabricas.PARROQUIA);
            DaoDatoEncuestado dao = (DaoDatoEncuestado) fabricaDatoEncuestado.generarDao();
            DatoEncuestado datoEncuestado = (DatoEncuestado) fabricaDatoEncuestado.generarEntidad();
            datoEncuestado.setSegundoNombre( dtoDatoEncuestado.getSegundoNombre());
            datoEncuestado.setSegundoapellido( dtoDatoEncuestado.getSegundoapellido());
            datoEncuestado.setEdad( dtoDatoEncuestado.getEdad());
            datoEncuestado.setGenero( dtoDatoEncuestado.getGenero());
            datoEncuestado.setMedioConexion( dtoDatoEncuestado.getMedioConexion());
            datoEncuestado.setNive_economico( dtoDatoEncuestado.getNive_economico());
            datoEncuestado.setNivelAcademico( dtoDatoEncuestado.getNivelAcademico());
            datoEncuestado.setPersonasHogar( dtoDatoEncuestado.getPersonasHogar());
            datoEncuestado.setCedula( dtoDatoEncuestado.getCedula());
            datoEncuestado.setActivo( 1);
            datoEncuestado.setCreado_el(
                    new Date(Calendar.getInstance().getTime().getTime())
            );
            Parroquia parroquia = (Parroquia) fabricaParroquia.generarEntidad();
            parroquia.set_id(
                    dtoDatoEncuestado.getFk_lugar().get_id()
            );
            datoEncuestado.setFk_lugar(parroquia);

            Usuario usuario = (Usuario) fabricaUsuario.generarEntidad();
            usuario.set_id(
                    dtoDatoEncuestado.getUsuario().get_id()
            );
            datoEncuestado.setUsuario( usuario );
            Ocupacion ocupacion = (Ocupacion) fabricaOcupacion.generarEntidad();
            ocupacion.set_id(
                    dtoDatoEncuestado.getOcupacion().get_id()
            );
            datoEncuestado.setOcupacion( ocupacion );
            for ( DtoTelefono telefono: dtoDatoEncuestado.getTelefonos()){

                Telefono paraInsertar = (Telefono) fabricaTelefono.generarEntidad();
                paraInsertar.setTelefono( telefono.getTelefono());
                paraInsertar.setActivo( 1);
                paraInsertar.setCreado_el(
                        new Date(Calendar.getInstance().getTime().getTime())
                );
                datoEncuestado.addTelefono( paraInsertar);
            }
            for (DtoHijo hijo: dtoDatoEncuestado.getHijos()){
                Hijo paraInsertar = (Hijo) fabricaHijo.generarEntidad();
                paraInsertar.setEdad( hijo.getEdad());
                paraInsertar.setGenero( hijo.getGenero());
                paraInsertar.setActivo( 1);
                paraInsertar.setCreado_el(
                        new Date(Calendar.getInstance().getTime().getTime())
                );
                datoEncuestado.addHijo( paraInsertar);
            }
            DatoEncuestado resul = dao.insert(datoEncuestado);
            this.result = ResponseGeneral.SuccesCreate( resul.get_id());
        }catch (Exception e){
            this.result = ResponseGeneral.Failure("ERROR");
        }
    }

    @Override
    public Response getResult() {
        return this.result;
    }

    public void setDtoDatoEncuestado(DtoDatoEncuestado dtoDatoEncuestado) {
        this.dtoDatoEncuestado = dtoDatoEncuestado;
    }
}
