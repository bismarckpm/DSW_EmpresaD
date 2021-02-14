package mercadeoucab.dtos;

import java.util.ArrayList;
import java.util.List;

/**
 * Name: DtoSubCategoria
 */
public class DtoSubCategoria extends DtoBase{

    private String nombre;
    private DtoCategoria categoria;
    private List<DtoTipo> tipoList;

    /**
     * Name: DtoSubCategoria
     * Description: Constructor a partir de id
     * @param id
     * @throws Exception
     */
    public DtoSubCategoria(long id) throws Exception {
        super(id);
    }

    /**
     * Name: DtoSubCategoria
     * Description: COnstructor vacio
     */
    public DtoSubCategoria() {
    }

    /**
     * Name: getNombre
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Name: setNombre
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Name: getCategoria
     * @return categoria
     */
    public DtoCategoria getCategoria() {
        return categoria;
    }

    /**
     * Name: setCategoria
     * @param categoria
     */
    public void setCategoria(DtoCategoria categoria) {
        this.categoria = categoria;
    }

    /**
     * Name: getTipoList
     * @return
     */
    public List<DtoTipo> getTipoList() { return tipoList; }

    /**
     * Name: setTipoList
     * @param tipoList
     */
    public void setTipoList(List<DtoTipo> tipoList) { this.tipoList = tipoList; }

    /**
     * Name: addTipo
     * @param dtoTipo
     */
    public void addTipo(DtoTipo dtoTipo){
        if (this.tipoList == null)
            this.tipoList = new ArrayList<>();
        this.tipoList.add(dtoTipo);
    }
}
