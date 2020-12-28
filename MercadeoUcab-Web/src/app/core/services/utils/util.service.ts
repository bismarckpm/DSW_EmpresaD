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

  getPreguntasOfAnAdministrador(id) {
    return this._http.get(this.url + '/administrador/' + id + '/preguntas');
  }

  getEstudiosOfEncuestado(id) {
    return this._http.get(this.url + '/encuestados/estudios/' + id);
  }

  getUsuariosAnalistas() {
    return this._http.get(this.url + '/analista');
  }

  getEstudiosOfAnalista(id) {
    return this._http.get(this.url + '/analista/' + id + '/estudios');
  }

  getSolicitudesOfCliente(id) {
    return this._http.get(this.url + '/cliente/' + id + '/solicitudes');
  }

  getPreguntasRecomendadasOfSolicitud(id) {
    return this._http.get(
      this.url + '/solicitudes/' + id + '/preguntas_recomendadas'
    );
  }

  getUsuariosOfEncuesta(idEstudio) {
    return this._http.get(
      this.url + '/estudios/' + idEstudio + '/usuarios_respondieron'
    );
  }
}
