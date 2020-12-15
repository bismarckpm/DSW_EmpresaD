import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { GLOBAL } from '@env/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class RespuestaService {
  public url: string;

  constructor(public _http: HttpClient) {
    this.url = GLOBAL.urlOscar;
  }

  getRespuestas(): Observable<any> {
    return this._http.get(this.url + '/respuestas');
  }

  getRespuesta(id): Observable<any> {
    return this._http.get(this.url + '/respuestas' + id);
  }

  createRespuesta(data) 
  {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.post(this.url + '/respuestas/', JSON.stringify(data), {headers: headers});
  }

  updateRespuesta(id, data) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.put(this.url + '/respuestas/' + id, JSON.stringify(data), {headers: headers});
  }

  deleteRespuesta(id, data) {
    // Ignorar data por los momentos
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.put(this.url + '/respuestas/' + id + '/eliminar', data);
  }
}
