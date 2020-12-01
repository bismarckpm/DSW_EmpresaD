import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-data-table',
  templateUrl: './data-table.component.html',
  styleUrls: ['./data-table.component.css']
})
export class DataTableComponent implements OnInit {

  constructor() {}
  @Input()
  _data;
  @Input()
  _displayedColumns;
  @Input()
  _columnsToDisplay;

  ngOnInit(): void {
    console.log(this._columnsToDisplay);
    console.log(this._data);
  }

}
