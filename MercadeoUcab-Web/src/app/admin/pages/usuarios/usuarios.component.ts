import { Component, OnInit} from '@angular/core';
import { FormBuilder} from '@angular/forms';
import { MatTableDataSource} from '@angular/material/table';
import { NgbModal} from '@ng-bootstrap/ng-bootstrap';
import { Usuario } from '@models/usuario';
import { MatDatepickerModule } from '@angular/material/datepicker';

class UserModel {
  id:number;
}

@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.component.html',
  styleUrls: ['./usuarios.component.css'],
  providers:[MatDatepickerModule]
})
export class UsuariosComponent implements OnInit {
  //CONTROL DE ESTADO DEL COMPONENTE
  op:string;
  searchState:string;//U.I,D
  users: UserModel[] = [];
  
  //COLUMNAS DE TABLA DE RESULTADOS
  displayedColumns: string[] = ['id','selector','ops'];
  columnsToDisplay: string[] = this.displayedColumns.slice();
  
  //INDICE DE USUARIO SELECCIONADO
  userSelection:number = 0;
  
  //LISTA DE USUARIOS DEVUELTOS EN BÚSQUEDA
  dataSource : MatTableDataSource<UserModel>;
  
  //FORMULARIOS
  updForm;
  searchForm;
  searchModel:Usuario;
  addForm;
  opStatus:string;//S,P,D
  userRole:string = "";
  setTipoUsuario(tipo:string){
    this.userRole=tipo;
  }
  
   constructor(private modalService: NgbModal,private formBuilder: FormBuilder) { 
    this.updForm = this.formBuilder.group({
      nombre:'',
    });
    this.searchForm = this.formBuilder.group({
      nombre:'',
      apellido:'',
      rol:'',//SELECT
      estado:'',//SELECT
      activo:true,//CHECKBOX O SELECT
      creado_el:'',//DATE TO STRING
      modificado_el:''//DATE TO STRING
    })
   }

  ngAfterViewInit() {}

  ngOnInit(): void {
    this.setOperation('');
    this.searchState="U";
  }
  serviceInvoke(){
    this.opStatus="P";
    setTimeout(()=>{
      this.opStatus="D";
    },3000);
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
  invokeSearch(){
    console.log(this.searchForm.value);
    setTimeout(()=>{
      for (let i = 0; i < Math.floor(Math.random()*(100-1)+1); i++) {
        this.users.push({id:i+1});
      }
      this.dataSource = new MatTableDataSource<UserModel>(this.users);
      this.searchState="D";
    },3000);
  }
  setOperation(chOp:string){
    this.op=chOp;
    if(chOp !== ''){
      this.searchState="I";
      this.opStatus="S";
      this.setTipoUsuario('');
    }
    else{
      this.searchState="U";
    }
  }
  doSearch(){
    this.searchState="I";
  }
  checkUpdValues(){
    console.log(this.updForm.value);
  }
  checkAddValues(){
    console.log(this.updForm.value);
  }
  openModal(content){
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'});
  }
}
