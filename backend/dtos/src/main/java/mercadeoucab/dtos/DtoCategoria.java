package mercadeoucab.dtos;

import java.util.ArrayList;
import java.util.List;

/**
 * Name:DtoCategoria
 */
public class DtoCategoria  extends DtoBase{
    private String nombre;
    private List<DtoSubCategoria> subCategorias;

    /**
     * Name: DtoCategoria
     * Description: crea un nuevo DtoCategoria con un id
     * @param id
     * @throws Exception
     */
    public DtoCategoria(long id) throws Exception {
        super(id);
    }

    /**
     * Name: DtoCategoria
     * Description: constructor vacio
     */
    public DtoCategoria() {
    }

    /**
     * Name: getNombre
     * Description: obtiene el nombre de la categoria
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Name: setCategoria
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Name: getSubCategorias
     * @return subCategorias
     */
    public List<DtoSubCategoria> getSubCategorias() { return subCategorias; }

    /**
     * Name: setSubCategorias
     * @param subCategorias
     */
    public void setSubCategorias(List<DtoSubCategoria> subCategorias) { this.subCategorias = subCategorias; }

    /**
     * Name: addSubCategoria
     * @param dtoSubCategoria
     */
    public void addSubCategoria(DtoSubCategoria dtoSubCategoria){
        if (this.subCategorias == null)
            this.subCategorias = new ArrayList<>();
        this.subCategorias.add(dtoSubCategoria);
    }
}
