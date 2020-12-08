import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { GLOBAL} from '@env/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MunicipioService {
  public url: string;

  constructor(
    public _http:HttpClient
  ) { 
    this.url = GLOBAL.urlOscar;
  }

  getMunicipios(): Observable<any>{
    return this._http.get( this.url + '/municipios');
  }

  getMunicipio(id): Observable<any>{
    return this._http.get( this.url + '/municipios' + id);
  }

  createMunicipio( data){
    let json = JSON.stringify({
      "nombre": data.nombre,
      "fk_estado": {
        "_id": data.fk_estado._id
      }
    });
    let params =json;
    return this._http.post( 
      this.url + '/municipios/', 
      { params: params}
    );
  }

  updateMunicipio( id, data){
    let json = JSON.stringify({
      "_id": data._id,
      "nombre": data.nombre
    });
    let params =json;
    return this._http.put( 
      this.url + '/municipios/' + id, 
      { params: params}
    );
  }

  deleteMunicipio( id, data){
    // Ignorar data por los momentos
    return this._http.put( 
      this.url + '/municipios/' + id + '/eliminar', 
      data
    );
  }
}
