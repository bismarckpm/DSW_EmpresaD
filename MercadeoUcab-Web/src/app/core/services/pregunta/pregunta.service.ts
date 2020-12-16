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

  //id en path
  getPregunta(id): Observable<any> {
    return this._http.get(this.url + '/preguntas/' + id);
  }

  /*las opciones pueden estar vacias tho 
  {
    "nombre_pregunta":string,
    "tipo":string",
    "rango":string,
    "fk_usuario":id_admin,
    "opciones":[
      {
        "nombre_opcion": string
      },
      .
      .
      .
      {
        "nombre_opcion": string
      }
    ]
  }
  */
  createPregunta(data) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.post(this.url + '/preguntas/', JSON.stringify(data), {
      headers: headers,
    });
  }

  /*id en el path
  {
    "nombre_pregunta":string,
    "tipo":string",
    "rango":string
  }
  */
  updatePregunta(id, data) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.put(this.url + '/preguntas/' + id, JSON.stringify(data), {
      headers: headers,
    });
  }

  //id en el path
  deletePregunta(id, data) {
    // Ignorar data por los momentos
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.put(this.url + '/preguntas/' + id + '/eliminar', data);
  }
}
