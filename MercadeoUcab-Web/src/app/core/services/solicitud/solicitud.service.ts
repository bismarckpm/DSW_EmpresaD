import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { GLOBAL} from '@env/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SolicitudService {
  public url: string;

  constructor(
    public _http:HttpClient
  ) { 
    this.url = GLOBAL.urlOscar;
  }

  getSolicitudes(): Observable<any>{
    return this._http.get( this.url + '/solicitudes');
  }

  getSolicitud(id): Observable<any>{
    return this._http.get( this.url + '/solicitudes' + id);
  }

  createSolicitud( data){
    // Posible error verificar que la marca y usuario la cargue bien 
    let json = JSON.stringify({
      "estado": data.estado,
      "usuario": {
        "_id": data.usuario._id
      },
      "marca": {
        "_id": data.marca._id
      }
    });
    let params =json;
    return this._http.post( 
      this.url + '/solicitudes/', 
      { params: params}
    );
  }

  updateSolicitud( id, data){
    let json = JSON.stringify({
      "_id": data._id,
      "estado": data.estado,
      "activo": data.activo,
      "creado_el": data.creado_el,
      "modificado_el": data.modificado_el
    });
    let params =json;
    return this._http.put( 
      this.url + '/solicitudes/' + id, 
      { params: params}
    );
  }

  deleteSolicitud( id, data){
    // Ignorar data por los momentos
    return this._http.put( 
      this.url + '/solicitudes/' + id + '/eliminar', 
      data
    );
  }
}
