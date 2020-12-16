import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { GLOBAL} from '@env/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OpcionService {

  public url: string;

  constructor(public _http: HttpClient) {
    this.url = GLOBAL.urlOscar;
  }

  /*
  {
    "nombre_opcion":String,
    "fk_pregunta": int
  }
  */
  createOpcion(data) 
  {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.post(this.url + '/opcion/', JSON.stringify(data), {headers: headers});
  }

  /*id en path
  {
    "nombre_opcion":String
  }
  */
  updateOpcion(id, data) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.put(this.url + '/opcion/' + id, JSON.stringify(data), {headers: headers});
  }

  //id en el path
  deleteOpcion(id, data) {
    // Ignorar data por los momentos
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.put(this.url + '/opcion/' + id + '/eliminar', data);
  }

}
