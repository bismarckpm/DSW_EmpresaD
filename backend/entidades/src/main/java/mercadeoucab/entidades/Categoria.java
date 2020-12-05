package mercadeoucab.entidades;

import javax.persistence.Column;

public class Categoria extends EntidadBase{

    public Categoria(long id) {
        super(id);
    }

    public Categoria() {
    }

    @Column(name = "nombre")
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
