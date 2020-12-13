package mercadeoucab.entidades;

import javax.persistence.*;

@Entity
@Table( name = "telefono" )
public class Telefono extends EntidadBase{

    @Column( name = "telefono")
    private String telefono;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "fk_dato_encuestado")
    private DatoEncuestado datoEncuestado;

    public Telefono(long id) {
        super(id);
    }

    public Telefono() {
    }

    public DatoEncuestado getDatoEncuestado() {
        return datoEncuestado;
    }

    public void setDatoEncuestado(DatoEncuestado datoEncuestado) {
        this.datoEncuestado = datoEncuestado;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
