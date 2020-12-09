import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { GLOBAL} from '@env/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class Muestra_poblacionService {

  public url: string;

  constructor(
    public _http:HttpClient
  ) { 
    this.url = GLOBAL.urlOscar;
  }

}
