import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { GLOBAL} from '@env/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ParroquiaService {
  public url: string;

  constructor(
    public _http:HttpClient
  ) { 
    this.url = GLOBAL.urlOscar;
  }

  getParroquias(): Observable<any>{
    return this._http.get( this.url + '/parroquias');
  }

  getParroquia(id): Observable<any>{
    return this._http.get( this.url + '/parroquias' + id);
  }

  createParroquia( data){
    let json = JSON.stringify({
      "nombre": data.nombre,
      "fk_municipio": {
        "_id": data.fk_municipio._id
      },
      "valor_socio_economico": data.valor_socio_economico
    });
    let params =json;
    return this._http.post( 
      this.url + '/parroquias/', 
      { params: params}
    );
  }

  updateParroquia( id, data){
    let json = JSON.stringify({
      "_id": data._id,
      "nombre": data.nombre
    });
    let params =json;
    return this._http.put( 
      this.url + '/parroquias/' + id, 
      { params: params}
    );
  }

  deleteParroquia( id, data){
    // Ignorar data por los momentos
    return this._http.put( 
      this.url + '/parroquias/' + id + '/eliminar', 
      data
    );
  }
}
