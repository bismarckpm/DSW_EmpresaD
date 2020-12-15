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

  getPresentacion(id): Observable<any> {
    return this._http.get(this.url + '/presentaciones/' + id);
  }

  createPresentacion(data) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.post(
      this.url + '/presentaciones/',
      JSON.stringify(data),
      { headers: headers }
    );
  }

  updatePresentacion(id, data) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.put(
      this.url + '/presentaciones/' + id,
      JSON.stringify(data),
      { headers: headers }
    );
  }

  deletePresentacion(id, data) {
    // Ignorar data por los momentos
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.put(
      this.url + '/presentaciones/' + id + '/eliminar',
      data
    );
  }
}
