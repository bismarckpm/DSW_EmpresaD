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

  displayedColumns: string[] = ['marca', 'ops'];
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
          this.encuestas = response.data;
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
        {"_id":1,"pregunta":{"_id":1,"nombre":"Que opina del producto? ",
        "tipo":"abierta","usuario":{"_id":26,"nombre":"Macon","apellido":"Mcleod","rol":"administrador","estado":"activo","correo":"MM10@gmail.com"}},"respuestas":[]},
        {"_id":2,"pregunta":{"_id":2,"nombre":"Cuentenos, tuvo algun problema con el producto?","tipo":"abierta","usuario":{"_id":26,"nombre":"Macon","apellido":"Mcleod","rol":"administrador","estado":"activo","correo":"MM10@gmail.com"}},"respuestas":[]},
        {"_id":3,"pregunta":{"_id":3,"nombre":"Como fue su experiencia con el producto?","tipo":"abierta","usuario":{"_id":26,"nombre":"Macon","apellido":"Mcleod","rol":"administrador","estado":"activo","correo":"MM10@gmail.com"}},"respuestas":[]}]},
        {"_id":2,"estado":"En ejecucion","tipo":"En linea","encuestas_esperadas":1,"solicitud":{"_id":2,"estado":"solicitada","usuario":{"_id":32,"nombre":"Brent","apellido":"Luna","rol":"cliente","estado":"activo","correo":"Bluna@gmail.com"},"marca":"Sin especificar","comentarios":"Sin comentarios","presentaciones":[{"_id":2,"tipo":"Peso","Cantidad":"5 g","fk_tipo":{"_id":2,"nombre":"Polvo","subCategoria":{"_id":2,"nombre":"Profesional","categoria":{"_id":2,"nombre":"Maquillaje"}}}}],"muestraPoblacion":{"_id":2,"genero":"masculino","nivel_economico":"Alto","nivel_academico":"Licenciado","rango_edad_inicio":"1940-01-01","rango_edad_fin":"2015-01-01","cantidad_hijos":1,"parroquia":{"_id":1,"nombre":"San Camilo","valorSocioEconomico":1,"municipio":{"_id":1,"nombre":"Manaos","estado":{"_id":1,"nombre":"Amazonas","pais":{"_id":1,"nombre":"Venezuela"}}}}}},"analista":{"_id":37,"nombre":"Harrison","apellido":"Dorsey","rol":"analista","estado":"activo","correo":"HARRI@gmail.com"},"encuesta":[{"_id":4,"pregunta":{"_id":4,"nombre":"Como se entero del producto?","tipo":"simple","usuario":{"_id":27,"nombre":"Warren","apellido":"Torres","rol":"administrador","estado":"activo","correo":"WARREN@gmail.com"},"opciones":[{"_id":1,"nombre":"redes sociales"},{"_id":2,"nombre":"radio"},{"_id":3,"nombre":"TV"},{"_id":4,"nombre":"conocidos"}]},"respuestas":[]},{"_id":5,"pregunta":{"_id":5,"nombre":"cuanto uso el producto?","tipo":"simple","usuario":{"_id":28,"nombre":"Jonas","apellido":"Mccray","rol":"administrador","estado":"activo","correo":"JON_M@gmail.com"},"opciones":[{"_id":5,"nombre":"Mucho"},{"_id":6,"nombre":"Poco"},{"_id":7,"nombre":"Nada"}]},"respuestas":[]},{"_id":6,"pregunta":{"_id":7,"nombre":"Recomendaria el producto?","tipo":"boolean","usuario":{"_id":29,"nombre":"Barclay","apellido":"Holt","rol":"administrador","estado":"activo","correo":"HOLT10@gmail.com"}},"respuestas":[]}]},{"_id":3,"estado":"En ejecucion","tipo":"En linea","encuestas_esperadas":1,"solicitud":{"_id":3,"estado":"solicitada","usuario":{"_id":33,"nombre":"Victor","apellido":"Paul","rol":"cliente","estado":"activo","correo":"V1@gmail.com"},"marca":"Sin especificar","comentarios":"Sin comentarios","presentaciones":[{"_id":3,"tipo":"n/a","Cantidad":"Cientificas","fk_tipo":{"_id":3,"nombre":"Calculadoras","subCategoria":{"_id":3,"nombre":"Oficina","categoria":{"_id":3,"nombre":"Electronicos"}}}}],"muestraPoblacion":{"_id":3,"genero":"masculino","nivel_economico":"Alto","nivel_academico":"Magister","rango_edad_inicio":"1940-01-01","rango_edad_fin":"2015-01-01","cantidad_hijos":1,"parroquia":{"_id":1,"nombre":"San Camilo","valorSocioEconomico":1,"municipio":{"_id":1,"nombre":"Manaos","estado":{"_id":1,"nombre":"Amazonas","pais":{"_id":1,"nombre":"Venezuela"}}}}}},"analista":{"_id":38,"nombre":"Nehru","apellido":"Winters","rol":"analista","estado":"activo","correo":"NEHR@gmail.com"},"encuesta":[{"_id":7,"pregunta":{"_id":8,"nombre":"Le gusto el producto?","tipo":"boolean","usuario":{"_id":29,"nombre":"Barclay","apellido":"Holt","rol":"administrador","estado":"activo","correo":"HOLT10@gmail.com"}},"respuestas":[]}]},{"_id":4,"estado":"En ejecucion","tipo":"En linea","encuestas_esperadas":1,"solicitud":{"_id":4,"estado":"solicitada","usuario":{"_id":34,"nombre":"Kasper","apellido":"Whitaker","rol":"cliente","estado":"activo","correo":"KW@gmail.com"},"marca":"Sin especificar","comentarios":"Sin comentarios","presentaciones":[{"_id":4,"tipo":"Caja","Cantidad":"24 unidades","fk_tipo":{"_id":4,"nombre":"Lapices","subCategoria":{"_id":4,"nombre":"Escolares","categoria":{"_id":4,"nombre":"Utiles"}}}}],"muestraPoblacion":{"_id":4,"genero":"masculino","nivel_economico":"Alto","nivel_academico":"Magister","rango_edad_inicio":"1940-01-01","rango_edad_fin":"2015-01-01","cantidad_hijos":1,"parroquia":{"_id":1,"nombre":"San Camilo","valorSocioEconomico":1,"municipio":{"_id":1,"nombre":"Manaos","estado":{"_id":1,"nombre":"Amazonas","pais":{"_id":1,"nombre":"Venezuela"}}}}}},"analista":{"_id":39,"nombre":"Reese","apellido":"Dillon","rol":"analista","estado":"activo","correo":"R_D@gmail.com"},"encuesta":[{"_id":8,"pregunta":{"_id":9,"nombre":"Lo volveria a comprar","tipo":"boolean","usuario":{"_id":29,"nombre":"Barclay","apellido":"Holt","rol":"administrador","estado":"activo","correo":"HOLT10@gmail.com"}},"respuestas":[]},{"_id":9,"pregunta":{"_id":10,"nombre":"Cuanta calidad le da al producto?","tipo":"rango","rango":"1&10","usuario":{"_id":30,"nombre":"Wyatt","apellido":"Jackson","rol":"administrador","estado":"activo","correo":"Wtt@gmail.com"}},"respuestas":[]},{"_id":10,"pregunta":{"_id":11,"nombre":"Cuanto le da al acabado del producto?","tipo":"rango","rango":"1&10","usuario":{"_id":30,"nombre":"Wyatt","apellido":"Jackson","rol":"administrador","estado":"activo","correo":"Wtt@gmail.com"}},"respuestas":[]},{"_id":11,"pregunta":{"_id":12,"nombre":"En que grado recomendaria el producto?","tipo":"rango","rango":"1&10","usuario":{"_id":30,"nombre":"Wyatt","apellido":"Jackson","rol":"administrador","estado":"activo","correo":"Wtt@gmail.com"}},"respuestas":[]},{"_id":12,"pregunta":{"_id":13,"nombre":"Como se entero del producto?","tipo":"multiple","usuario":{"_id":28,"nombre":"Jonas","apellido":"Mccray","rol":"administrador","estado":"activo","correo":"JON_M@gmail.com"},"opciones":[{"_id":12,"nombre":"redes sociales"},{"_id":13,"nombre":"radio"},{"_id":14,"nombre":"TV"},{"_id":15,"nombre":"conocidos"}]},"respuestas":[]}]}]
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
