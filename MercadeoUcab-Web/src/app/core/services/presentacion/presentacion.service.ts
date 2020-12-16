import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { GLOBAL } from '@env/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class PresentacionService {
  public url: string;

  constructor(public _http: HttpClient) {
    this.url = GLOBAL.urlOscar;
  }

  getPresentaciones(): Observable<any> {
    return this._http.get(this.url + '/presentaciones');
  }

  //id en el path
  getPresentacion(id): Observable<any> {
    return this._http.get(this.url + '/presentaciones/' + id);
  }

  /*
  {
    "cantidad":"string",
    "tipo":string
  }
  */
  createPresentacion(data) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.post(
      this.url + '/presentaciones/',
      JSON.stringify(data),
      { headers: headers }
    );
  }

  /*
  id en path
  {
    "cantidad":"string",
    "tipo":string
  }
  */
  updatePresentacion(id, data) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.put(
      this.url + '/presentaciones/' + id,
      JSON.stringify(data),
      { headers: headers }
    );
  }

  //id en el path
  deletePresentacion(id, data) {
    // Ignorar data por los momentos
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.put(
      this.url + '/presentaciones/' + id + '/eliminar',
      data
    );
  }
}
