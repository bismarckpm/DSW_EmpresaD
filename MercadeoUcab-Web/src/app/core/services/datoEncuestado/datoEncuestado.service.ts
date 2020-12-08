import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { GLOBAL} from '@env/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DatoEncuestadoService {
  public url: string;

  constructor(
    public _http:HttpClient
  ) { 
    this.url = GLOBAL.urlOscar;
  }

  getDatosEncuestados(): Observable<any>{
    return this._http.get( this.url + '/datos_encuestados');
  }

  getDatoEncuestado(id): Observable<any>{
    return this._http.get( this.url + '/datos_encuestados' + id);
  }

  createDatoEncuestado( data){
    let json = JSON.stringify({
      "segundoNombre": data.segundoNombre,
      "segundoApellido": data.segundoApellido,
      "cedula": data.cedula,
      "medioConexion": data.medioConexion,
      "edad": data.edad,
      "genero": data.genero,
      "nivel_economico": data.nivel_economico,
      "nivelAcademico": data.nivelAcademico,
      "personasHogar": data.personasHogar,
      "usuario": {
        "_id": data.usuario._id
      },
      "fk_lugar": {
        "_id": data.fk_lugar._id
      }
    });
    let params =json;
    return this._http.post( 
      this.url + '/datos_encuestados/', 
      { params: params}
    );
  }

  updateDatoEncuestado( id, data){
    let json = JSON.stringify({
      "_id": data._id,
      "segundoNombre": data.segundoNombre,
      "segundoApellido": data.segundoApellido,
      "cedula": data.cedula,
      "medioConexion": data.medioConexion,
      "edad": data.edad,
      "genero": data.genero,
      "nivel_economico": data.nivel_economico,
      "nivelAcademico": data.nivelAcademico,
      "personasHogar": data.personasHogar,
      "activo": data.activo,
      "creado_el": data.creado_el,
      "modificado_el": data.modificado_el
    });
    let params =json;
    return this._http.put( 
      this.url + '/datos_encuestados/' + id, 
      { params: params}
    );
  }

  deleteDatoEncuestado( id, data){
    // Ignorar data por los momentos
    return this._http.put( 
      this.url + '/datos_encuestados/' + id + '/eliminar', 
      data
    );
  }
}
