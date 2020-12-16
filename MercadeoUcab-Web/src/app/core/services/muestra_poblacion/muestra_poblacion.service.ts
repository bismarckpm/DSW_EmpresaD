import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { GLOBAL} from '@env/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class Muestra_poblacionService {

  public url: string;

  constructor(public _http: HttpClient) {
    this.url = GLOBAL.urlOscar;
  }

  /*
  {
    "genero":"genero",
    "nivelEconomico":int,
    "nivelAcademico":String,
    "rangoEdadInicio":int,
    "rangoEdadFin":int,
    "cantidadHijos":int,
    "fk_lugar": int,
    "fk_ocupacion":int
  }
  */
  createMuestraPoblacion(data) 
  {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.post(this.url + '/muestrasPoblaciones/', JSON.stringify(data), {headers: headers});
  }

  /*
  id en el path
  {
    "genero":"genero",
    "nivelEconomico":int,
    "nivelAcademico":String,
    "rangoEdadInicio":int,
    "rangoEdadFin":int,
    "cantidadHijos":int
  }
  */
  updateMuestraPoblacion(id, data) {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.put(this.url + '/muestrasPoblaciones/' + id, JSON.stringify(data), {headers: headers});
  }

  //id en el path
  deleteMuestraPoblacion(id, data) {
    // Ignorar data por los momentos
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.put(this.url + '/muestrasPoblaciones/' + id + '/eliminar', data);
  }

}
