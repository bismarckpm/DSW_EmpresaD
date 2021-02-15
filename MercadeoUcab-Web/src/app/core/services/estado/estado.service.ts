import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { GLOBAL } from '@env/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class EstadoService {
  public url: string;

  constructor(public _http: HttpClient) {
    this.url = GLOBAL.urlOscar;
  }

  getEstados(): Observable<any> {
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', token);
    return this._http.get(this.url + '/estados', { headers: headers });
  }

  //id en el path
  getEstado(id): Observable<any> {
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', token);
    return this._http.get(this.url + '/estados/' + id, { headers: headers });
  }

  /*
  {
    "nombre":"nombre",
    "fk_pais":int
  }
  */
  createEstado(data) {
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', token);
    return this._http.post(this.url + '/estados/', JSON.stringify(data), {
      headers: headers,
    });
  }

  /*id en path
  {
    "nombre":"nombre"
  }
  */
  updateEstado(id, data) {
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', token);
    return this._http.put(this.url + '/estados/' + id, JSON.stringify(data), {
      headers: headers,
    });
  }

  //id en el path
  deleteEstado(id, data) {
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', token);
    // Ignorar data por los momentos
    return this._http.put(
      this.url + '/estados/' + id + '/eliminar',
      JSON.stringify(data),
      { headers: headers }
    );
  }
}
