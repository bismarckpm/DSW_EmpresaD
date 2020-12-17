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

  //id en el path
  getRespuesta(id): Observable<any> {
    return this._http.get(this.url + '/respuestas/' + id);
  }

  /*
  {
    "respuesta":String,
    "fk_opcion":int,
    "encuesta_estudio":int,
    "fk_usuario":int
  }
  */
  createRespuesta(data) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.post(this.url + '/respuestas/', JSON.stringify(data), {
      headers: headers,
    });
  }

  /*
    id en patj
  {
    "respuesta":String
  }
  */
  updateRespuesta(id, data) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.put(
      this.url + '/respuestas/' + id,
      JSON.stringify(data),
      { headers: headers }
    );
  }

  //id en el path
  deleteRespuesta(id, data) {
    // Ignorar data por los momentos
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.put(this.url + '/respuestas/' + id + '/eliminar', data);
  }

  saveSurvey(data) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.post(this.url + '/respuestas/encuesta', data);
  }
}
