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
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', token);
    return this._http.get(this.url + '/respuestas', { headers: headers });
  }

  //id en el path
  getRespuesta(id): Observable<any> {
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', token);
    return this._http.get(this.url + '/respuestas/' + id, { headers: headers });
  }
  /*
  En ejecucion = se estan haciendo las encuestas 
Procesando = Se completaron las encuestas esperadas, se esta esperando que el analista lo revise y ponga el resultado 
Culminado = Ya el analista le puso el resultado
  */
  /*
  {
    "respuesta":String,
    "fk_opcion":int,
    "encuesta_estudio":int,
    "fk_usuario":int
  }
  */
  createRespuesta(data) {
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', token);
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
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', token);
    return this._http.put(
      this.url + '/respuestas/' + id,
      JSON.stringify(data),
      { headers: headers }
    );
  }

  //id en el path
  deleteRespuesta(id, data) {
    // Ignorar data por los momentos
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', token);
    return this._http.put(this.url + '/respuestas/' + id + '/eliminar', data, {
      headers: headers,
    });
  }

  saveSurvey(data) {
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', token);
    return this._http.post(
      this.url + '/respuestas/encuesta',
      JSON.stringify(data),
      { headers: headers }
    );
  }
}
