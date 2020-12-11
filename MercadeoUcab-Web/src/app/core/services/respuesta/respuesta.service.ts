import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { GLOBAL } from '@env/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class RespuestaService {
  public url: string;

  constructor(public _http: HttpClient) {
    this.url = GLOBAL.urlOscar;
  }

  getRespuestas(): Observable<any> {
    return this._http.get(this.url + '/respuestas');
  }

  getRespuesta(id): Observable<any> {
    return this._http.get(this.url + '/respuestas' + id);
  }

  createRespuesta(data) {
    let json = JSON.stringify({
      respuesta: data.respuesta,
      Dtousuario: {
        _id: data.Dtousuario._id,
      },
      dtoopcion: {
        _id: data.dtoopcion._id,
      },
    });
    let params = json;
    return this._http.post(this.url + '/respuestas/', { params: params });
  }

  updateRespuesta(id, data) {
    let json = JSON.stringify({
      _id: data._id,
      respuesta: data.respuesta,
    });
    let params = json;
    return this._http.put(this.url + '/respuestas/' + id, { params: params });
  }

  deleteRespuesta(id, data) {
    // Ignorar data por los momentos
    return this._http.put(this.url + '/respuestas/' + id + '/eliminar', data);
  }

  signup(data) {
    console.log('Usuario Services Signup');
    return data;
  }
}
