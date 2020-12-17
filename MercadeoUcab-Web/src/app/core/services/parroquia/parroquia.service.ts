import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { GLOBAL } from '@env/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ParroquiaService {
  public url: string;

  constructor(public _http: HttpClient) {
    this.url = GLOBAL.urlOscar;
  }

  getParroquias(): Observable<any> {
    return this._http.get(this.url + '/parroquias');
  }

  //id en path
  getParroquia(id): Observable<any> {
    return this._http.get(this.url + '/parroquias/' + id);
  }

  /*
  {
    "nombre":"nombre",
    "valor_socio_economico":int,
    "fk_municipio":int
  }
  */
  createParroquia(data) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.post(this.url + '/parroquias/', JSON.stringify(data), {
      headers: headers,
    });
  }

  /*
  id en el path
  {
    "nombre":"nombre",
    "valor_socio_economico":int
  }
  */
  updateParroquia(id, data) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.put(
      this.url + '/parroquias/' + id,
      JSON.stringify(data),
      { headers: headers }
    );
  }

  //id en path
  deleteParroquia(id, data) {
    // Ignorar data por los momentos
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.put(
      this.url + '/parroquias/' + id + '/eliminar',
      JSON.stringify(data),
      { headers: headers }
    );
  }
}
