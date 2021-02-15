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
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', token);
    return this._http.get(this.url + '/ocupaciones', { headers: headers });
  }

  getOcupacion(id): Observable<any> {
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', token);
    return this._http.get(this.url + '/ocupaciones/' + id, {
      headers: headers,
    });
  }

  createOcupacion(data) {
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', token);
    return this._http.post(this.url + '/ocupaciones/', JSON.stringify(data), {
      headers,
    });
  }

  updateOcupacion(id, data) {
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', token);
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
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', token);
    return this._http.put(
      this.url + '/ocupaciones/' + id + '/eliminar',
      JSON.stringify(data),
      {
        headers,
      }
    );
  }
}
