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
    return this._http.get(this.url + '/tipos');
  }

  //id en el path
  getTipo(id): Observable<any> {
    return this._http.get(this.url + '/tipos/' + id);
  }

  /*{
    "nombre":"nombre"
    }*/ 
  createTipo(data) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
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
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.put(this.url + '/tipos/' + id, JSON.stringify(data), {
      headers: headers,
    });
  }

  //id en el path
  deleteTipo(id, data) {
    // Ignorar data por los momentos
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.put(this.url + '/tipos/' + id + '/eliminar', data);
  }
}
