import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { GLOBAL } from '@env/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class PaisService {
  public url: string;

  constructor(public _http: HttpClient) {
    this.url = GLOBAL.urlOscar;
  }

  getPaises(): Observable<any> {
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', token);
    return this._http.get(this.url + '/paises', { headers: headers });
  }

  //id en path
  getPais(id): Observable<any> {
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', token);
    return this._http.get(this.url + '/paises/' + id, { headers: headers });
  }

  /*
  {
    "nombre":"nombre"
  }
  */
  createPais(data) {
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', token);
    return this._http.post(this.url + '/paises/', JSON.stringify(data), {
      headers: headers,
    });
  }

  /*
  id en el path
  {
    "nombre":"nombre"
  }
  */
  updatePais(id, data) {
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', token);
    return this._http.put(this.url + '/paises/' + id, JSON.stringify(data), {
      headers: headers,
    });
  }

  //id en path
  deletePais(id, data) {
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', token);
    // Ignorar data por los momentos
    return this._http.put(
      this.url + '/paises/' + id + '/eliminar',
      JSON.stringify(data),
      { headers: headers }
    );
  }
}
