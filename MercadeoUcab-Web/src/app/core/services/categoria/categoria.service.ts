import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { GLOBAL} from '@env/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CategoriaService {

  public url: string;

  constructor(
    public _http:HttpClient
  ) { 
    this.url = GLOBAL.urlOscar;
  }

  getCategorias(): Observable<any>{
    return this._http.get( this.url + '/categorias');
  }

  getCategoria(id): Observable<any>{
    return this._http.get( this.url + '/categorias' + id);
  }

  createCategoria( data){
    let json = JSON.stringify({
      "nombre": data.nombre
    });
    let params =json;
    return this._http.post( 
      this.url + '/categorias/', 
      { params: params}
    );
  }

  updateCategoria( id, data){
    let json = JSON.stringify({
      "_id": data._id,
      "nombre": data.nombre
    });
    let params =json;
    return this._http.put( 
      this.url + '/categorias/' + id, 
      { params: params}
    );
  }

  deleteCategoria( id, data){
    // Ignorar data por los momentos
    return this._http.put( 
      this.url + '/categorias/' + id + '/eliminar', 
      data
    );
  }
}
