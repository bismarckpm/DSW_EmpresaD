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

  getDatoEncuestado(id): Observable<any> {
    return this._http.get(this.url + '/datos_encuestados/' + id);
  }

  createDatoEncuestado(data) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.post(
      this.url + '/datos_encuestados/',
      JSON.stringify(data),
      { headers: headers }
    );
  }

  updateDatoEncuestado(id, data) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.put(
      this.url + '/datos_encuestados/' + id,
      JSON.stringify(data),
      { headers: headers }
    );
  }

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
