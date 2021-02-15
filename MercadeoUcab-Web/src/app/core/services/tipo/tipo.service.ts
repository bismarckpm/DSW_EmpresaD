import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { GLOBAL } from '@env/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class TipoService {
  public url: string;

  constructor(public _http: HttpClient) {
    this.url = GLOBAL.urlOscar;
  }

  getTipos(): Observable<any> {
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', token);
    return this._http.get(this.url + '/tipos', { headers: headers });
  }

  //id en el path
  getTipo(id): Observable<any> {
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', token);
    return this._http.get(this.url + '/tipos/' + id, { headers: headers });
  }

  /*{
    "nombre":"nombre"
    }*/
  createTipo(data) {
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', token);
    return this._http.post(this.url + '/tipos/', JSON.stringify(data), {
      headers: headers,
    });
  }

  /*
    id en el path
    {
    "nombre":"nombre"
    }*/
  updateTipo(id, data) {
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', token);
    return this._http.put(this.url + '/tipos/' + id, JSON.stringify(data), {
      headers: headers,
    });
  }

  //id en el path
  deleteTipo(id, data) {
    // Ignorar data por los momentos
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', token);
    return this._http.put(this.url + '/tipos/' + id + '/eliminar', data);
  }
}
