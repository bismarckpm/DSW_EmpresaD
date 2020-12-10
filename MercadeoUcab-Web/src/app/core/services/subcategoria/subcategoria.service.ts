import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { GLOBAL} from '@env/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SubcategoriaService {
  public url: string;

  constructor(
    public _http:HttpClient
  ) { 
    this.url = GLOBAL.urlOscar;
  }

  getSubCategorias(): Observable<any>{
    return this._http.get( this.url + '/subcategorias');
  }

  getSubCategoria(id): Observable<any>{
    return this._http.get( this.url + '/subcategorias' + id);
  }

  createSubCategoria( data){
    // Posible error verificar que la categoria la cargue bien 
    let json = JSON.stringify({
      "nombre": data.nombre,
      "categoria": {
        "_id": data.categoria._id
      }
    });
    let params =json;
    return this._http.post( 
      this.url + '/subcategorias/', 
      { params: params}
    );
  }
  updateSubCategoria( data){
    // Posible error verificar que la categoria la cargue bien 
    let json = JSON.stringify({
      "_id": data._id,
      "nombre": data.nombre
    });
    let params =json;
    return this._http.put( 
      this.url + '/subcategorias/', 
      { params: params}
    );
  }

  deleteUser( id, data){
    // Ignorar data por los momentos
    return this._http.put( 
      this.url + '/subcategorias/' + id + '/eliminar', 
      data
    );
  }

}
