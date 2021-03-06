package mercadeoucab.fabricas.FabricasConcretas;

import mercadeoucab.accesodatos.Dao;
import mercadeoucab.dtos.DtoBase;
import mercadeoucab.dtos.DtoMail;
import mercadeoucab.entidades.EntidadBase;
import mercadeoucab.fabricas.FabricaAbstracta;
import mercadeoucab.mail.Mail;
import mercadeoucab.responses.ResponseBase;

public class FabricaMail extends FabricaAbstracta {
    /**
     * Name: generarEntidad
     * Description: Crea una entidad
     *
     * @return EntidadBase
     */
    @Override
    public EntidadBase generarEntidad() {
        return null;
    }

    /**
     * Name: generarDto
     * Description: Genera un Dto
     * @return DtoBase
     */
    @Override
    public DtoBase generarDto() {
        return null;
    }

    /**
     * Name: generarDtoMail
     * Description: Genera un DtoMail
     * @return DtoBase
     */
    public DtoMail generarDtoMail(){
        return new DtoMail();
    }

    /**
     * Name: generarMail
     * Description: Genera un Mail
     * @return DtoBase
     */
    public Mail generarMail(){
        return new Mail();
    }

    /**
     * Name: generarDao
     * Description: retorna la clase Dao
     *
     * @return Dao
     */
    @Override
    public Dao generarDao() {
        return null;
    }

    /**
     * Name: generarResponse
     * Description: genera un Response base
     *
     * @return ResponseBase
     */
    @Override
    public ResponseBase generarResponse() {
        return null;
    }
}
