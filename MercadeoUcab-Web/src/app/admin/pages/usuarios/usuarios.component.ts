import { Component, OnInit, ViewChild } from '@angular/core';
import {FormBuilder, NgForm} from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';
import {MatDialog} from '@angular/material/dialog';
import { DataTableComponent } from '../../components/data-table/data-table.component';
import { BaseDialogComponent } from '../../components/base-dialog/base-dialog.component';
import { UserInfoDialogComponent } from '../../components/user-info-dialog/user-info-dialog.component';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import { FormGroup, FormControl, Validators }  from '@angular/forms';
import {FormsModule,ReactiveFormsModule} from '@angular/forms';

class UserModel {
  id:number;
}
/*
@Component({
  selector: 'dialog-update-user-dialog',
  templateUrl: '<app-base-dialog></app-base-dialog>',
})
class UserUpdateDialog {}

@Component({
  selector: 'dialog-delete-user-dialog',
  templateUrl: '<app-base-dialog></app-base-dialog>',
})
class UserDeleteDialog {}
*/
/*
@Component({
  selector: 'dialog-info-user-dialog',
  templateUrl: './user-info-dialog.component.html',
})
class UserInfoDialog {

}*/

@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.component.html',
  styleUrls: ['./usuarios.component.css'],
})
export class UsuariosComponent implements OnInit {
  
  users: UserModel[] = [];
  displayedColumns: string[] = ['id','selector','ops'];
  columnsToDisplay: string[] = this.displayedColumns.slice();
  userSelection:number = 0;
  dataSource : MatTableDataSource<UserModel>;
  updForm;
  //activeDialog: MatDialog;
   constructor(private modalService: NgbModal,private formBuilder: FormBuilder) { 
    this.updForm = this.formBuilder.group({
      nombre:'',
    });
   }

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(UserInfoDialogComponent) userInfo:UserInfoDialogComponent;
  /*@ViewChild(UserUpdateDialog) updateOperation:UserUpdateDialog;
  @ViewChild(UserDeleteDialog) deleteOperation: UserDeleteDialog;
  @ViewChild(UserInfoDialog) userInfo: UserInfoDialog;*/
 
  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

  ngOnInit(): void {
    for (let i = 0; i < Math.floor(Math.random()*(100-1)+1); i++) {
      this.users.push({id:i+1});
    }
    this.dataSource = new MatTableDataSource<UserModel>(this.users);
  }
  selectUser(id: number){
    if(id === this.userSelection){
      this.userSelection = 0;
    }
    else{
      this.userSelection=id;
    }
  }
  isSelected(id: number):boolean{
    if(id === this.userSelection){
      return true;
    }
    return false;
  }
  checkValues(){
    console.log(this.updForm.value);
  }
  openInfo(content){
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'});
  }
}
