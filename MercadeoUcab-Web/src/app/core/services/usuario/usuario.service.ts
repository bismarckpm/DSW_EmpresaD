import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { GLOBAL } from '@env/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UsuarioService {
  public url: string;

  constructor(public _http: HttpClient) {
    this.url = GLOBAL.urlOscar;
  }

  getUsers(): Observable<any> {
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', token);
    return this._http.get(this.url + '/usuarios', { headers: headers });
  }

  getUser(id): Observable<any> {
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', token);
    return this._http.get(this.url + '/usuarios/' + id, { headers: headers });
  }

  createUser(data) {
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', token);
    return this._http.post(this.url + '/usuarios/', JSON.stringify(data), {
      headers,
    });
  }

  updateUser(id, data) {
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', token);
    return this._http.put(this.url + '/usuarios/' + id, JSON.stringify(data), {
      headers: headers,
    });
  }

  deleteUser(id, data) {
    // Ignorar data por los momentos
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', token);
    return this._http.put(
      this.url + '/usuarios/' + id + '/eliminar',
      JSON.stringify(data),
      {
        headers,
      }
    );
  }

  forgotPasswordRequest(data) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.post(
      this.url + '/usuarios/peticionClaveOlvidada',
      JSON.stringify(data),
      { headers }
    );
  }

  forgotPassword(data) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.post(
      this.url + '/usuarios/cambioClaveOlvidada',
      JSON.stringify(data),
      { headers }
    );
  }

  signup(data) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.post(
      'http://45.76.60.252:8080/mercadeoucab-1.0-SNAPSHOT/api/v1/LDAP/login',
      JSON.stringify(data),
      {
        headers,
      }
    );
  }
}
