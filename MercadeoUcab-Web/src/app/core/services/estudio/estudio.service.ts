import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { GLOBAL } from '@env/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class EstudioService {
  public url: string;

  constructor(public _http: HttpClient) {
    this.url = GLOBAL.urlOscar;
  }

  getEstudios(): Observable<any> {
    return this._http.get(this.url + '/estudios');
  }

  getEstudioss(): Observable<any> {
    return this._http.get(this.url + '/estudios/s');
  }
  //id en path
  getEstudio(id): Observable<any> {
    return this._http.get(this.url + '/estudios/' + id);
  }

  /*
  Las preguntas tienen que existir
  {
    "estado":String,
    "tipo":String
    "encuestasEsperadas":int,
    "solicitud"int,
    "fk_usuario":int,
    "fk_muestra_poblacion":int,
    "preguntas":[
      {
        "_id":int
      },
      .
      .
      .
      {
        "_id":int
      }
    ]
  }
  */
  createEstudio(data) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.post(this.url + '/estudios/', JSON.stringify(data), {
      headers: headers,
    });
  }

  /*
  id en el path
  {
    "estado":String,
    "tipo":String
    "encuestasEsperadas":int
  }
  */
  updateEstudio(id, data) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.put(this.url + '/estudios/' + id, JSON.stringify(data), {
      headers: headers,
    });
  }

  //id en el path
  deleteEstudio(id, data) {
    // Ignorar data por los momentos
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.put(this.url + '/estudios/' + id + '/eliminar', data);
  }
}
