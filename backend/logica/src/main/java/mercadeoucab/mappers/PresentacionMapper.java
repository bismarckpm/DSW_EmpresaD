package mercadeoucab.mappers;

import mercadeoucab.dtos.DtoPresentacion;
import mercadeoucab.entidades.Presentacion;
import mercadeoucab.fabricas.Enums.Fabricas;
import mercadeoucab.fabricas.FabricaAbstracta;

public class PresentacionMapper {

    public static Presentacion mapDtoToEntity (DtoPresentacion dtoPresentacion) throws Exception{

        FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.PRESENTACION);
        Presentacion presentacion = (Presentacion) fabrica.generarEntidad();
        presentacion.set_id(dtoPresentacion.get_id());
        presentacion.setCantidad( dtoPresentacion.getCantidad());
        presentacion.setTipo( dtoPresentacion.getTipo());
        presentacion.setActivo( dtoPresentacion.getActivo());
        presentacion.setModificado_el( dtoPresentacion.getModificado_el());
        presentacion.setCreado_el( dtoPresentacion.getCreado_el());
        if(dtoPresentacion.getFk_tipo() != null)
            presentacion.setFk_tipo(
                    TipoMapper.mapDtoToEntity(dtoPresentacion.getFk_tipo())
            );
       return presentacion;
    }

    public static DtoPresentacion mapEntityToDto ( Presentacion presentacion) throws Exception {
        FabricaAbstracta fabrica = FabricaAbstracta.getFactory(Fabricas.PRESENTACION);
        DtoPresentacion dtoPresentacion = (DtoPresentacion) fabrica.generarDto();
        dtoPresentacion.set_id(presentacion.get_id());
        dtoPresentacion.setCantidad( presentacion.getCantidad());
        dtoPresentacion.setTipo( presentacion.getTipo());
        dtoPresentacion.setActivo( presentacion.getActivo());
        dtoPresentacion.setCreado_el( presentacion.getCreado_el());
        dtoPresentacion.setModificado_el( presentacion.getModificado_el());
        if(presentacion.getFk_tipo() != null)
            dtoPresentacion.setFk_tipo(
                    TipoMapper.mapEntityToDto(presentacion.getFk_tipo())
            );

        return dtoPresentacion;
    }
}
