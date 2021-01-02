import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { GLOBAL } from '@env/environment';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root',
})
export class OcupacionService {
  public url: string;

  constructor(public _http: HttpClient) {
    this.url = GLOBAL.urlOscar;
  }

  getOcupaciones(): Observable<any> {
    return this._http.get(this.url + '/ocupaciones');
  }

  getOcupacion(id): Observable<any> {
    return this._http.get(this.url + '/ocupaciones/' + id);
  }

  createOcupacion(data) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.post(this.url + '/ocupaciones/', JSON.stringify(data), {
      headers,
    });
  }

  updateOcupacion(id, data) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.put(
      this.url + '/ocupaciones/' + id,
      JSON.stringify(data),
      {
        headers: headers,
      }
    );
  }

  deleteOcupacion(id, data) {
    // Ignorar data por los momentos
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.put(
      this.url + '/ocupaciones/' + id + '/eliminar',
      JSON.stringify(data),
      {
        headers,
      }
    );
  }
}
