import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { GLOBAL } from '@env/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class SolicitudService {
  public url: string;

  constructor(public _http: HttpClient) {
    this.url = GLOBAL.urlOscar;
  }

  getSolicitudByEstado(estado) {
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', token);
    return this._http.get(this.url + '/solicitudes/estado/' + estado, {
      headers: headers,
    });
  }

  getSolicitudes(): Observable<any> {
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', token);
    return this._http.get(this.url + '/solicitudes', {
      headers: headers,
    });
  }

  //id en el path
  getSolicitud(id): Observable<any> {
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', token);
    return this._http.get(this.url + '/solicitudes/' + id, {
      headers: headers,
    });
  }

  /*
  {
    "estado":"solicitada",
    "usuario":3,
    "marca":1,
    "tipo":4,
    "subCategoria":1,
    "presentacion":2
  }
  */
  createSolicitud(data) {
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', token);
    return this._http.post(this.url + '/solicitudes/', JSON.stringify(data), {
      headers,
    });
  }

  /*
   id en el path
  {
    "estado":string
  }
  */
  updateSolicitud(id, data) {
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', token);
    return this._http.put(
      this.url + '/solicitudes/' + id,
      JSON.stringify(data),
      { headers: headers }
    );
  }

  //id en el path
  deleteSolicitud(id, data) {
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', token);
    // Ignorar data por los momentos
    return this._http.put(
      this.url + '/solicitudes/' + id + '/eliminar',
      JSON.stringify(data),
      { headers: headers }
    );
  }
}
