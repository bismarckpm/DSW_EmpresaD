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
    return this._http.get( this.url + '/solicitudes/' + id);
  }

  createSolicitud( data){
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.post( 
      this.url + '/solicitudes/', 
      JSON.stringify(data), 
      {headers: headers}
    );
  }

  updateSolicitud( id, data){
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.put( 
      this.url + '/solicitudes/' + id, 
      JSON.stringify(data), 
      {headers: headers}
    );
  }

  deleteSolicitud( id, data){
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    // Ignorar data por los momentos
    return this._http.put( 
      this.url + '/solicitudes/' + id + '/eliminar', 
      JSON.stringify(data), 
      {headers: headers}
    );
  }
}
