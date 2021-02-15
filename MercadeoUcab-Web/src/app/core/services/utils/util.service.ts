import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { GLOBAL } from '@env/environment';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root',
})
export class UtilService {
  public url: string;

  constructor(public _http: HttpClient) {
    this.url = GLOBAL.urlOscar;
  }

  getPreguntasOfAnAdministrador(id): Observable<any> {
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', token);
    return this._http.get(this.url + '/administrador/' + id + '/preguntas', {
      headers: headers,
    });
  }

  getEstudiosOfEncuestado(id): Observable<any> {
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', token);
    return this._http.get(this.url + '/encuestados/estudios/' + id, {
      headers: headers,
    });
  }

  getUsuariosAnalistas(): Observable<any> {
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', token);
    return this._http.get(this.url + '/analista', {
      headers: headers,
    });
  }

  getEstudiosOfAnalista(id): Observable<any> {
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', token);
    return this._http.get(this.url + '/analista/' + id + '/estudios', {
      headers: headers,
    });
  }

  getSolicitudesOfCliente(id): Observable<any> {
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', token);
    return this._http.get(this.url + '/cliente/' + id + '/solicitudes', {
      headers: headers,
    });
  }

  getPreguntasRecomendadasOfSolicitud(id): Observable<any> {
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', token);
    return this._http.get(
      this.url + '/solicitudes/' + id + '/preguntas_recomendadas',
      {
        headers: headers,
      }
    );
  }

  getPoblacionRecomendadasOfSolicitud(id): Observable<any> {
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', token);
    return this._http.get(
      this.url + '/solicitudes/' + id + '/poblaciones_recomendadas',
      {
        headers: headers,
      }
    );
  }

  getUsuariosOfEncuesta(idEstudio): Observable<any> {
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', token);
    return this._http.get(
      this.url + '/estudios/' + idEstudio + '/usuarios_respondieron',
      {
        headers: headers,
      }
    );
  }

  getUsuariosCanApplyToEstudio(idEstudio): Observable<any> {
    let token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', token);
    return this._http.get(
      this.url + '/estudios/' + idEstudio + '/usuarios_aplican',
      {
        headers: headers,
      }
    );
  }
}
