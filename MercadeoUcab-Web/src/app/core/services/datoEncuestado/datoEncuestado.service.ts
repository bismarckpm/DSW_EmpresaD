import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { GLOBAL } from '@env/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class DatoEncuestadoService {
  public url: string;

  constructor(public _http: HttpClient) {
    this.url = GLOBAL.urlOscar;
  }

  getDatosEncuestados(): Observable<any> {
    return this._http.get(this.url + '/datos_encuestados');
  }

  //id en el path
  getDatoEncuestado(id): Observable<any> {
    return this._http.get(this.url + '/datos_encuestados/' + id);
  }

  /*
  {
    "segundoNombre":String,
    "segundoApellido":String,
    "cedula":String,
    "medioConexion":String,
    "edad":"yyyy-mm-dd",
    "genero":"genero",
    "nive_economico": int,
    "nivelAcademico": String,
    "personasHogar":int,
    "fk_lugar":int,
    "usuario":int,
    "ocupacion":int,
    "telefonos":[
      {
        "telefono":String
      },
      .
      .
      .
      {
        "telefono":String
      }
    ],
    "hijos":[
      {
        "genero":String,
        "edad":"yyyy-mm-dd"
      }
    ]
  }
  */
  createDatoEncuestado(data) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.post(
      this.url + '/datos_encuestados/',
      JSON.stringify(data),
      { headers: headers }
    );
  }

  /*
  id en path
  {
    "segundoNombre":String,
    "segundoApellido":String,
    "cedula":String,
    "medioConexion":String,
    "edad":"yyyy-mm-dd",
    "genero":"genero",
    "nive_economico": int,
    "nivelAcademico": String,
    "personasHogar":int
  }
  */
  updateDatoEncuestado(id, data) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.put(
      this.url + '/datos_encuestados/' + id,
      JSON.stringify(data),
      { headers: headers }
    );
  }

  //id en path
  deleteDatoEncuestado(id, data) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    // Ignorar data por los momentos
    return this._http.put(
      this.url + '/datos_encuestados/' + id + '/eliminar',
      JSON.stringify(data),
      { headers: headers }
    );
  }
}
