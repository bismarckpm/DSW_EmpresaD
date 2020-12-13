import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { GLOBAL} from '@env/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EstudioService {

  public url: string;

  constructor(
    public _http:HttpClient
  ) {
    this.url = GLOBAL.urlOscar;
  }

  getEstudios(): Observable<any>{
    return this._http.get( this.url + '/estudios');
  }

  getEstudio(id): Observable<any>{
    return this._http.get( this.url + '/estudios' + id);
  }

  createEstudio( data){
    // Posible error verificar que la marca y usuario la cargue bien
    let json = JSON.stringify({
      estado:data.estado,
      tipo:data.tipo,
      encuestas_esperadas:data.encuestas_esperadas,
      activo:data.activo,
    });
    let params =json;
    return this._http.post(
      this.url + '/estudios/',
      { params: params}
    );
  }

}
