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
    return this._http.get(this.url + '/usuarios' + id);
  }

  createUser(data) {
    let json = JSON.stringify({
      nombre: data.nombre,
      apellido: data.apellido,
      estado: data.estado,
      rol: data.rol,
      correo: data.correo,
    });
    let params = json;
    return this._http.post(this.url + '/usuarios/', { params: params });
  }

  updateUser(id, data) {
    let json = JSON.stringify({
      _id: data._id,
      nombre: data.nombre,
      apellido: data.apellido,
      estado: data.estado,
      activo: data.activo,
      creado_el: data.creado_el,
      modificado_el: data.modificado_el,
    });
    let params = json;
    return this._http.put(this.url + '/usuarios/' + id, { params: params });
  }

  deleteUser(id, data) {
    // Ignorar data por los momentos
    return this._http.put(this.url + '/usuarios/' + id + '/eliminar', data);
  }

  signup(data) {
    console.log('Usuario Services Signup');
    return data;
  }
}
