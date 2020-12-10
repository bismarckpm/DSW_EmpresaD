import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { GLOBAL} from '@env/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SubCategoriaSolicitudService {
  public url: string;

  constructor(
    public _http:HttpClient
  ) { 
    this.url = GLOBAL.urlOscar;
  }

  getSubCategoriaSolicitudes(): Observable<any>{
    return this._http.get( this.url + '/subCategoriaSolicitud');
  }

  getSubCategoriaSolicitud(id): Observable<any>{
    return this._http.get( this.url + '/subCategoriaSolicitud' + id);
  }

  createSubCategoriaSolicitud( data){
    let json = JSON.stringify({
      "solicitud": {
        "_id": data.solicitud._id
      },
      "subCategoria": {
        "_id": data.subCategoria._id
      },
    });
    let params =json;
    return this._http.post( 
      this.url + '/subCategoriaSolicitud/', 
      { params: params}
    );
  }

  deleteSubCategoriaSolicitud( id, data){
    // Ignorar data por los momentos
    return this._http.put( 
      this.url + '/subCategoriaSolicitud/' + id + '/eliminar', 
      data
    );
  }
}
