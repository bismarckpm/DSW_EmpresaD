import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-data-cliente-table',
  templateUrl: './data-table-cliente.component.html',
  styleUrls: ['./data-table-cliente.component.css']
})
export class DataTableClienteComponent implements OnInit {

  constructor() {}
  /*@Input()
  _data;
  @Input()
  _displayedColumns;
  @Input()
  _columnsToDisplay;*/

  ngOnInit(): void {
    /*console.log(this._columnsToDisplay);
    console.log(this._data);*/
  }

}
