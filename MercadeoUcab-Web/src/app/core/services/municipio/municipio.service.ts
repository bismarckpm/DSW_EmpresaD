import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { GLOBAL } from '@env/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class MunicipioService {
  public url: string;

  constructor(public _http: HttpClient) {
    this.url = GLOBAL.urlOscar;
  }

  getMunicipios(): Observable<any> {
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', token);
    return this._http.get(this.url + '/municipios', { headers: headers });
  }

  //id en path
  getMunicipio(id): Observable<any> {
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', token);
    return this._http.get(this.url + '/municipios/' + id, { headers: headers });
  }

  /*
  {
    "nombre":"nombre",
    "fk_estado": int
  }
  */
  createMunicipio(data) {
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', token);
    return this._http.post(this.url + '/municipios/', JSON.stringify(data), {
      headers: headers,
    });
  }

  /*
  iden el path
  {
    "nombre":"nombre"
  }
  */
  updateMunicipio(id, data) {
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', token);
    return this._http.put(
      this.url + '/municipios/' + id,
      JSON.stringify(data),
      { headers: headers }
    );
  }

  //id en el path
  deleteMunicipio(id, data) {
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', token);
    // Ignorar data por los momentos
    return this._http.put(
      this.url + '/municipios/' + id + '/eliminar',
      JSON.stringify(data),
      { headers: headers }
    );
  }
}
