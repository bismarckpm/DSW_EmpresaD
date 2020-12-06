package mercadeoucab.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name ="tipo")
public class Tipo extends EntidadBase{

    public Tipo(long id) {
        super(id);
    }

    public Tipo() {
    }
}
