import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { GLOBAL } from '@env/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CategoriaService {
  public url: string;

  constructor(public _http: HttpClient) {
    this.url = GLOBAL.urlOscar;
  }

  getCategorias(): Observable<any> {
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', token);
    return this._http.get(this.url + '/extra', { headers: headers });
  }

  //id en el path
  getCategoria(id): Observable<any> {
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', token);
    return this._http.get(this.url + '/categorias/' + id, { headers: headers });
  }

  /*{
      "nombre":"nombre"
    }
  */
  createCategoria(data) {
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', token);
    let json = JSON.stringify({
      nombre: data.nombre,
    });
    let params = json;
    return this._http.post(this.url + '/categorias/', JSON.stringify(data), {
      headers: headers,
    });
  }

  /*{
      "nombre":"nombre"
    }
  */
  updateCategoria(id, data) {
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', token);
    let json = JSON.stringify({
      _id: data._id,
      nombre: data.nombre,
    });
    let params = json;
    return this._http.put(
      this.url + '/categorias/' + id,
      JSON.stringify(data),
      { headers: headers }
    );
  }

  //id en el path
  deleteCategoria(id, data) {
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', token);
    // Ignorar data por los momentos
    return this._http.put(
      this.url + '/categorias/' + id + '/eliminar',
      JSON.stringify(data),
      { headers: headers }
    );
  }
}
