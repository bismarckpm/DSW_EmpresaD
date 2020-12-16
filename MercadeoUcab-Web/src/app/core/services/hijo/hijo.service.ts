import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { GLOBAL} from '@env/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HijoService {
  public url: string;

  constructor(
    public _http:HttpClient
  ) { 
    this.url = GLOBAL.urlOscar;
  }

  getHijos(): Observable<any>{
    return this._http.get( this.url + '/hijos');
  }

  //id en path
  getHijo(id): Observable<any>{
    return this._http.get( this.url + '/hijos' + id);
  }

  /*
  {
    "genero":"genero",
    "edad":"yyyy-mm-dd",
    "datoEncuestado":int
  }
  */
  createHijo( data){
    let json = JSON.stringify({
      "genero": data.genero,
      "edad": data.edad,
      "fk_dato_encuestado": {
        "_id": data.fk_dato_encuestado._id
      },
      "correo": data.correo
    });
    let params =json;
    return this._http.post( 
      this.url + '/hijos/', 
      { params: params}
    );
  }

    /*
    id en path
  {
    "genero":"genero",
    "edad":"yyyy-mm-dd"
  }
  */
  updateHijo( id, data){
    let json = JSON.stringify({
      "_id": data._id,
      "genero": data.genero,
      "edad": data.edad,
    });
    let params =json;
    return this._http.put( 
      this.url + '/hijos/' + id, 
      { params: params}
    );
  }

  //id en el path
  deleteHijo( id, data){
    // Ignorar data por los momentos
    return this._http.put( 
      this.url + '/hijos/' + id + '/eliminar', 
      data
    );
  }
}
