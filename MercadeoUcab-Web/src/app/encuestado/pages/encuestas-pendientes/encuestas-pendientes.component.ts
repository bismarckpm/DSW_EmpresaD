import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { MatTableDataSource } from '@angular/material/table';
import { Encuesta } from '@core/models/encuesta';

import { Estudio } from '@core/models/estudio';
import { EstudioService } from '@core/services/estudio/estudio.service';
import { UtilService } from '@core/services/utils/util.service';

@Component({
  selector: 'app-encuestas-pendientes',
  templateUrl: './encuestas-pendientes.component.html',
  styleUrls: ['./encuestas-pendientes.component.css'],
})
export class EncuestasPendientesComponent implements OnInit {
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private _utilService: UtilService
  ) {}
  // public idUserLogged: number = 2;
  public idUserLogged: number = parseInt(JSON.parse(localStorage.getItem('user_data'))['_id'],10);
  enc: string = '';
  searchState: string = '';
  encuestas: any[] = [];
  dataSource: MatTableDataSource<any>;

  displayedColumns: string[] = ['marca','pregs','subcat','cat','ops'];
  columnsToDisplay: string[] = this.displayedColumns.slice();
  ngOnInit(): void {
    this.getEncuestasOfEncuestado();
  }

  setEnc(t: string) {
    if (t !== '') {
      this.invokeSearch(t);
    }
    this.enc = t;
  }

  onDirEncuesta(_route: string, Id: number): void {
    try {
      this.router.navigate([`encuestado/survey/${Id}`]);
    } catch (e) {
      console.log(e.message);
    }
  }

  getEncuestasOfEncuestado() {
    this._utilService.getEstudiosOfEncuestado(this.idUserLogged).subscribe(
      (response: any) => {
        if (response.status === 200) {
          console.log(response);
          this.encuestas = [...response.data.filter((est,ind) => est.solicitud.Tipo !== 'Via telefonica' && est.estado !=='Culminado')];
        } else {
          //alert(response.message);
        }
      },
      (error) => {
        console.log(error);
        this.encuestas = [
        {"_id":1,"estado":"En ejecucion",
        "tipo":"En linea","encuestas_esperadas":1,
        "solicitud":{"_id":1,"estado":"solicitada","usuario":{"_id":31,"nombre":"Caesar","apellido":"Mosley","rol":"cliente","estado":"activo","correo":"CM10@gmail.com"},"marca":"Sin especificar","comentarios":"Sin comentarios",
        "presentaciones":[{"_id":1,"tipo":"Madera","Cantidad":"30x50","fk_tipo":{"_id":1,"nombre":"Camas","subCategoria":{"_id":1,"nombre":"Dormitorios","categoria":{"_id":1,"nombre":"Muebles"}}}}],
        "muestraPoblacion":{"_id":1,"genero":"masculino","nivel_economico":"Alto","nivel_academico":"Licenciado","rango_edad_inicio":"1940-01-01","rango_edad_fin":"2015-01-01",
        "cantidad_hijos":1,"parroquia":{"_id":1,"nombre":"San Camilo","valorSocioEconomico":1,"municipio":{"_id":1,"nombre":"Manaos",
        "estado":{"_id":1,"nombre":"Amazonas","pais":{"_id":1,"nombre":"Venezuela"}}}}}},
        "analista":{"_id":36,"nombre":"Harper","apellido":"Vance",
        "rol":"analista","estado":"activo","correo":"Harper20@gmail.com"},
        "encuesta":[
        {"_id":1,"pregunta":{"_id":1,"nombre":"Que opina del producto? ","tipo":"abierta","usuario":{"_id":26,"nombre":"Macon","apellido":"Mcleod","rol":"administrador","estado":"activo","correo":"MM10@gmail.com"}},"respuestas":[]},
        {"_id":2,"pregunta":{"_id":2,"nombre":"Cuentenos, tuvo algun problema con el producto?","tipo":"abierta","usuario":{"_id":26,"nombre":"Macon","apellido":"Mcleod","rol":"administrador","estado":"activo","correo":"MM10@gmail.com"}},"respuestas":[]},
        {"_id":3,"pregunta":{"_id":3,"nombre":"Como fue su experiencia con el producto?","tipo":"abierta","usuario":{"_id":26,"nombre":"Macon","apellido":"Mcleod","rol":"administrador","estado":"activo","correo":"MM10@gmail.com"}},"respuestas":[]}]},
        ]
      }
    );
  }

  invokeSearch(toSearch) {
    this.searchState = 'P';
    setTimeout(() => {
      /*this.encuestas = [
        {
          _id: 1,
        },
      ];*/
      this.dataSource = new MatTableDataSource<any>(this.encuestas);
      this.searchState = 'D';
    }, 3000);
  }
}
