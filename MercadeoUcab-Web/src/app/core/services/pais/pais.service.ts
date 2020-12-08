import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { GLOBAL} from '@env/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PaisService {
  public url: string;

  constructor(
    public _http:HttpClient
  ) { 
    this.url = GLOBAL.urlOscar;
  }

  getPaises(): Observable<any>{
    return this._http.get( this.url + '/paises');
  }

  getPais(id): Observable<any>{
    return this._http.get( this.url + '/paises' + id);
  }

  createPais( data){
    let json = JSON.stringify({
      "nombre": data.nombre
    });
    let params =json;
    return this._http.post( 
      this.url + '/paises/', 
      { params: params}
    );
  }

  updatePais( id, data){
    let json = JSON.stringify({
      "_id": data._id,
      "nombre": data.nombre
    });
    let params =json;
    return this._http.put( 
      this.url + '/paises/' + id, 
      { params: params}
    );
  }

  deletePais( id, data){
    // Ignorar data por los momentos
    return this._http.put( 
      this.url + '/paises/' + id + '/eliminar', 
      data
    );
  }
}
