import { Component, OnInit } from '@angular/core';
import { EstudioModel } from '../../Models/Estudio.model';
export interface PeriodicElement {
  name: string;
  state:string;
}

const ELEMENT_DATA: EstudioModel[] = [
  { nombre: 'Hydrogen', fecha_asig:new Date(),estado:'I'},
  { nombre: 'Helium', fecha_asig:new Date(),estado:'A'},
  { nombre: 'Lithium',fecha_asig:new Date(),estado:'I'},
  { nombre: 'Beryllium', fecha_asig:new Date(),estado:'A'},
  { nombre: 'Boron', fecha_asig:new Date(),estado:'A'},
  { nombre: 'Carbon',fecha_asig:new Date(),estado:'I'},
  { nombre: 'Nitrogen', fecha_asig:new Date(),estado:'A'},
  { nombre: 'Oxygen', fecha_asig:new Date(),estado:'I'},
  { nombre: 'Fluorine', fecha_asig:new Date(),estado:'I'},
  { nombre: 'Neon',fecha_asig:new Date(),estado:'I'},
];

@Component({
  selector: 'app-analista-tasks',
  templateUrl: './analista-tasks.component.html',
  styleUrls: ['./analista-tasks.component.css']
})
export class AnalistaTasksComponent implements OnInit {
  
  displayedColumns: string[] = ['nombre','fecha_asig','estado'];
  columnsToDisplay: string[] = this.displayedColumns.slice();
  data: EstudioModel[] = ELEMENT_DATA;

  constructor() {}
  ngOnInit(): void {
  }

}
