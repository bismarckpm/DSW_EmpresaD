import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { GLOBAL } from '@env/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class SubcategoriaService {
  public url: string;

  constructor(public _http: HttpClient) {
    this.url = GLOBAL.urlOscar;
  }

  getSubCategorias(): Observable<any> {
    return this._http.get(this.url + '/subcategorias');
  }

  //id en el path
  getSubCategoria(id): Observable<any> {
    return this._http.get(this.url + '/subcategorias/' + id);
  }

  /*
  {
    "nombre":"nombre",
    "fk_categoria":int
  }
  */
  createSubCategoria(data) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    // Posible error verificar que la categoria la cargue bien
    return this._http.post(this.url + '/subcategorias/', JSON.stringify(data), {
      headers: headers,
    });
  }

  /*
  id en el path
  {
    "nombre":"nombre"
  }
  */
  updateSubCategoria(id, data) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    // Posible error verificar que la categoria la cargue bien
    return this._http.put(
      this.url + '/subcategorias/' + id,
      JSON.stringify(data),
      { headers: headers }
    );
  }

  //id en el path
  deleteSubCategoria(id, data) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    // Ignorar data por los momentos
    return this._http.put(
      this.url + '/subcategorias/' + id + '/eliminar',
      JSON.stringify(data),
      { headers: headers }
    );
  }
}
