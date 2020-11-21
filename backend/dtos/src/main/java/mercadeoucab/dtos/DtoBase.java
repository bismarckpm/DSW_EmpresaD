package mercadeoucab.dtos;

import mercadeoucab.excepciones.PruebaExcepcion;

public class DtoBase
{
    private long _id;


    public DtoBase( long id ) throws Exception
    {
        setId( id );
    }

    public DtoBase()
    {
    }

    public long getId()
    {
        return _id;
    }

    public void setId( long id ) throws PruebaExcepcion
    {
        if ( id >= 0 )
        {
            _id = id;
        }
        else
        {
            throw new PruebaExcepcion( );
        }
    }
}
