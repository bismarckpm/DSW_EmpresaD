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
  public idUserLogged: number = 2;
  enc: string = '';
  searchState: string = '';
  encuestas: Encuesta[] = [];
  dataSource: MatTableDataSource<Encuesta>;

  displayedColumns: string[] = ['id', 'selector', 'ops'];
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
      this.dataSource = new MatTableDataSource<Encuesta>(this.encuestas);
      this.searchState = 'D';
    }, 3000);
  }
}
