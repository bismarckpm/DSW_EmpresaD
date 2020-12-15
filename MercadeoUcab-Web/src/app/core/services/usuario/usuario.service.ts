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
    return this._http.get(this.url + '/usuarios');
  }

  getUser(id): Observable<any> {
    return this._http.get(this.url + '/usuarios/' + id);
  }

  createUser(data) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.post(this.url + '/usuarios/', JSON.stringify(data), {
      headers: headers,
    });
  }

  updateUser(id, data) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.put(this.url + '/usuarios/' + id, JSON.stringify(data), {
      headers: headers,
    });
  }

  deleteUser(id, data) {
    // Ignorar data por los momentos
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.put(
      this.url + '/usuarios/' + id + '/eliminar',
      JSON.stringify(data),
      {
        headers: headers,
      }
    );
  }

  forgotPasswordRequest(data) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.post(
      this.url + '/usuarios/peticionClaveOlvidada',
      JSON.stringify(data),
      { headers: headers }
    );
  }

  forgotPassword(data) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.post(
      this.url + '/usuarios/cambioClaveOlvidada',
      JSON.stringify(data),
      { headers: headers }
    );
  }

  signup(data) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    console.log('Usuario Services Signup');
    return data;
  }
}
