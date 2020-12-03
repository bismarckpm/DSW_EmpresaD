import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {MatTableDataSource} from '@angular/material/table';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {STEPPER_GLOBAL_OPTIONS} from '@angular/cdk/stepper';

@Component({
  selector: 'app-estudios',
  templateUrl: './estudios.component.html',
  styleUrls: ['./estudios.component.css'],
  providers: [{
    provide: STEPPER_GLOBAL_OPTIONS, useValue: {displayDefaultIndicatorType: false}
  }]
})
export class EstudiosComponent implements OnInit {
  op:string;
  searchState:string;//U.I,D
  /*searchModel:Usuario;
  users: UserModel[] = [];
  displayedColumns: string[] = ['id','selector','ops'];
  columnsToDisplay: string[] = this.displayedColumns.slice();
  userSelection:number = 0;
  dataSource : MatTableDataSource<UserModel>;*/
  displayedColumns: string[] = ['id','selector','ops'];
  columnsToDisplay: string[] = this.displayedColumns.slice();
  updForm;
  addForm;
  searchForm;
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  constructor(private modalService: NgbModal,private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.searchForm = this.formBuilder.group({
      
    });
    this.firstFormGroup = this.formBuilder.group({
      firstCtrl: ['', Validators.required]
    });
    this.secondFormGroup = this.formBuilder.group({
      secondCtrl: ['', Validators.required]
    });
    this.setOperation('');
  }
  invokeSearch(){
    //console.log('Search works');
    //console.log(this.searchForm.value);
    setTimeout(()=>{
      this.searchState="D";
    },3000);
  }
  setOperation(chOp:string){
    this.op=chOp;
    if(chOp !== ''){
      this.searchState="I";
    }
    else{
      this.searchState="U";
    }
  }
  doSearch(){
    this.searchState="I";
  }
  /*checkUpdValues(){
    console.log(this.updForm.value);
  }
  checkAddValues(){
    console.log(this.updForm.value);
  }*/
  openModal(content){
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'});
  }
}
