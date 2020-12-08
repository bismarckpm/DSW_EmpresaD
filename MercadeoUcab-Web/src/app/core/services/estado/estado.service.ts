import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { GLOBAL} from '@env/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EstadoService {
  public url: string;

  constructor(
    public _http:HttpClient
  ) { 
    this.url = GLOBAL.urlOscar;
  }

  getEstados(): Observable<any>{
    return this._http.get( this.url + '/estados');
  }

  getEstado(id): Observable<any>{
    return this._http.get( this.url + '/estados' + id);
  }

  createEstado( data){
    let json = JSON.stringify({
      "nombre": data.nombre,
      "fk_pais": {
        "_id": data.fk_pais._id
      }
    });
    let params =json;
    return this._http.post( 
      this.url + '/estados/', 
      { params: params}
    );
  }

  updateEstado( id, data){
    let json = JSON.stringify({
      "_id": data._id,
      "nombre": data.nombre
    });
    let params =json;
    return this._http.put( 
      this.url + '/estados/' + id, 
      { params: params}
    );
  }

  deleteEstado( id, data){
    // Ignorar data por los momentos
    return this._http.put( 
      this.url + '/estados/' + id + '/eliminar', 
      data
    );
  }
}
