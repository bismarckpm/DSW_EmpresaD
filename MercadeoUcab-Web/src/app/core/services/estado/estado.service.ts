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
    return this._http.get(this.url + '/estados');
  }

  //id en el path
  getEstado(id): Observable<any> {
    return this._http.get(this.url + '/estados/' + id);
  }

  /*
  {
    "nombre":"nombre",
    "fk_pais":int
  }
  */
  createEstado(data) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
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
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.put(this.url + '/estados/' + id, JSON.stringify(data), {
      headers: headers,
    });
  }

  //id en el path
  deleteEstado(id, data) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    // Ignorar data por los momentos
    return this._http.put(
      this.url + '/estados/' + id + '/eliminar',
      JSON.stringify(data),
      { headers: headers }
    );
  }
}
