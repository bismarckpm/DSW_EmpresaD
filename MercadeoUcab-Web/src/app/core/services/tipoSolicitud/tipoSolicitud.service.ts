import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { GLOBAL} from '@env/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TipoSolicitudService {
  public url: string;

  constructor(
    public _http:HttpClient
  ) { 
    this.url = GLOBAL.urlOscar;
  }

  getTipoSolicitudes(): Observable<any>{
    return this._http.get( this.url + '/tipoSolicitud');
  }

  getTipoSolicitud(id): Observable<any>{
    return this._http.get( this.url + '/tipoSolicitud' + id);
  }

  createTipoSolicitud( data){
    let json = JSON.stringify({
      "solicitud": {
        "_id": data.solicitud._id
      },
      "tipo": {
        "_id": data.tipo._id
      },
    });
    let params =json;
    return this._http.post( 
      this.url + '/tipoSolicitud/', 
      { params: params}
    );
  }

  deleteTipoSolicitud( id, data){
    // Ignorar data por los momentos
    return this._http.put( 
      this.url + '/tipoSolicitud/' + id + '/eliminar', 
      data
    );
  }
}
