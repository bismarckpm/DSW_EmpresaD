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

  getEstudio(id): Observable<any> {
    return this._http.get(this.url + '/estudios' + id);
  }

  createEstudio(data) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.post(this.url + '/estudios/', JSON.stringify(data), {
      headers: headers,
    });
  }

  updateEstudio(id, data) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.put(this.url + '/estudios/' + id, JSON.stringify(data), {
      headers: headers,
    });
  }

  deleteEstudio(id, data) {
    // Ignorar data por los momentos
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.put(this.url + '/estudios/' + id + '/eliminar', data);
  }
}
