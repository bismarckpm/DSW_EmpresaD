import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { GLOBAL} from '@env/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MarcaService {
  public url: string;

  constructor(
    public _http:HttpClient
  ) { 
    this.url = GLOBAL.urlOscar;
  }

  getMarcas(): Observable<any>{
    return this._http.get( this.url + '/marcas');
  }

  getMarca(id): Observable<any>{
    return this._http.get( this.url + '/marcas' + id);
  }

  createMarca( data){
    let json = JSON.stringify({
      "nombre": data.nombre
    });
    let params =json;
    return this._http.post( 
      this.url + '/marcas/', 
      { params: params}
    );
  }

  updateMarca( id, data){
    let json = JSON.stringify({
      "_id": data._id,
      "nombre": data.nombre
    });
    let params =json;
    return this._http.put( 
      this.url + '/marcas/' + id, 
      { params: params}
    );
  }

  deleteMarca( id, data){
    // Ignorar data por los momentos
    return this._http.put( 
      this.url + '/marcas/' + id + '/eliminar', 
      data
    );
  }
}
