import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { GLOBAL } from '@env/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class PreguntaService {
  public url: string;

  constructor(public _http: HttpClient) {
    this.url = GLOBAL.urlOscar;
  }

  getPreguntas(): Observable<any> {
    return this._http.get(this.url + '/preguntas');
  }

  getPregunta(id): Observable<any> {
    return this._http.get(this.url + '/preguntas/' + id);
  }

  createPregunta(data) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.post(this.url + '/preguntas/', JSON.stringify(data), {
      headers: headers,
    });
  }

  updatePregunta(id, data) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.put(this.url + '/preguntas/' + id, JSON.stringify(data), {
      headers: headers,
    });
  }

  deletePregunta(id, data) {
    // Ignorar data por los momentos
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.put(this.url + '/preguntas/' + id + '/eliminar', data);
  }
}
