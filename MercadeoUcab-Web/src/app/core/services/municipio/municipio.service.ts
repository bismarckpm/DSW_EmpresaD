import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { GLOBAL } from '@env/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class MunicipioService {
  public url: string;

  constructor(public _http: HttpClient) {
    this.url = GLOBAL.urlOscar;
  }

  getMunicipios(): Observable<any> {
    return this._http.get(this.url + '/municipios');
  }

  getMunicipio(id): Observable<any> {
    return this._http.get(this.url + '/municipios/' + id);
  }

  createMunicipio(data) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.post(this.url + '/municipios/', JSON.stringify(data), {
      headers: headers,
    });
  }

  updateMunicipio(id, data) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.put(
      this.url + '/municipios/' + id,
      JSON.stringify(data),
      { headers: headers }
    );
  }

  deleteMunicipio(id, data) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    // Ignorar data por los momentos
    return this._http.put(
      this.url + '/municipios/' + id + '/eliminar',
      JSON.stringify(data),
      { headers: headers }
    );
  }
}
