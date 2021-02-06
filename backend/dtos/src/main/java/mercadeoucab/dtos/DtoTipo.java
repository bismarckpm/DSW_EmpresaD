package mercadeoucab.dtos;

import java.util.ArrayList;
import java.util.List;

/**
 * Name: DtoTipo
 */
public class DtoTipo extends DtoBase{

    private String nombre;
    private DtoSubCategoria subCategoria;
    private List<DtoPresentacion> presentacionList;

    /**
     * Name: DtoTipo
     * Description: Constructor a partir de id
     * @param id
     * @throws Exception
     */
    public DtoTipo(long id) throws Exception {
        super(id);
    }

    /**
     * Name: DtoTipo
     * Description: Constructor vacio
     */
    public DtoTipo() {
    }

    /**
     * Name: getNombre
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Name: DtoSubCategoria
     * @return
     */
    public DtoSubCategoria getSubCategoria() { return subCategoria; }

    /**
     * Name: setSubCategoria
     * @param subCategoria
     */
    public void setSubCategoria(DtoSubCategoria subCategoria) { this.subCategoria = subCategoria; }

    /**
     * Name:getPresentacionList
     * @return
     */
    public List<DtoPresentacion> getPresentacionList() { return presentacionList; }

    /**
     * Name: setPresentacionList
     * @param presentacionList
     */
    public void setPresentacionList(List<DtoPresentacion> presentacionList) { this.presentacionList = presentacionList; }

    /**
     * Name: addPresentacion
     * @param dtoPresentacion
     */
    public void addPresentacion(DtoPresentacion dtoPresentacion){
        if (this.presentacionList == null)
            this.presentacionList = new ArrayList<>();
        this.presentacionList.add(dtoPresentacion);
    }
}
