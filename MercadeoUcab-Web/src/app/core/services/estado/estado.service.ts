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
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.post( 
      this.url + '/estados/', 
      JSON.stringify(data), 
      {headers: headers}
    );
  }

  updateEstado( id, data){
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.put( 
      this.url + '/estados/' + id, 
      JSON.stringify(data), 
      {headers: headers}
    );
  }

  deleteEstado( id, data){
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    // Ignorar data por los momentos
    return this._http.put( 
      this.url + '/estados/' + id + '/eliminar', 
      JSON.stringify(data), 
      {headers: headers}
    );
  }
}
