import {Component, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {MatTableDataSource} from '@angular/material/table';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import { Usuario } from '@models/usuario';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { UpdateUserDialogComponent } from '../../components/dialogs/update-user-dialog/update-user-dialog.component';
import { DeleteUserDialogComponent } from '../../components/dialogs/delete-user-dialog/delete-user-dialog.component';
import { UsuarioService } from '@core/services/usuario/usuario.service';
import { DatePipe } from '@angular/common';

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
  users: Usuario[] = [];

  //COLUMNAS DE TABLA DE RESULTADOS
  displayedColumns: string[] = ['id','selector','ops'];
  columnsToDisplay: string[] = this.displayedColumns.slice();

  //INDICE DE USUARIO SELECCIONADO
  userSelection:number = 0;

  //LISTA DE USUARIOS DEVUELTOS EN BÚSQUEDA
  dataSource : MatTableDataSource<Usuario>;

  //FORMULARIOS
  updForm:FormGroup;
  searchForm:FormGroup;
  searchModel:Usuario;
  addForm:FormGroup;
  opStatus:string;//S,P,D
  userRole:string = "";
  setTipoUsuario(tipo:string){
    this.userRole=tipo;
  }

   constructor(
     //private modalService: NgbModal,
     private formBuilder: FormBuilder,
     private _userService: UsuarioService,
    ) {
    this.updForm = this.formBuilder.group({
      nombre:'',
    });
    this.searchForm = this.formBuilder.group({
      nombre:null,
      apellido:null,
      rol:null,//SELECT
      estado:null,//SELECT
      activo:null,//CHECKBOX O SELECT
      creado_el:null,//DATE TO STRING
      modificado_el:null//DATE TO STRING
    })
   }

  getUsers(){
    this._userService.getUsers().subscribe(
      (response) => {
        console.log(response);
      },
      (error) => {
        console.log(error);
      }
    )
  }
  ngAfterViewInit() {}

  ngOnInit(): void {
    this.setOperation('');
    this.searchState="U";
    this.getUsers();
  }
  @ViewChild('updUser') private updComponent:UpdateUserDialogComponent;
  async openUpdModal() {
    return await this.updComponent.open();
  }
  @ViewChild('delUser') private delComponent:DeleteUserDialogComponent;
  async openDelModal() {
    return await this.delComponent.open();
  }

  serviceInvoke(role:string){
    //MEDIO PARA DETERMINAR SERVICIO A INVOCAR SEGUN FORMULARIO DE CREACION DE USUARIO
    switch(role){
      case 'Admnistrador':
        break;
      case 'Analista':
        break;
      case 'Cliente':
        break;
      case 'Encuestado':
        break;
      default:
        break;
    }
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
  dateFormat(){

  }
  dataFilter(dataArray:Usuario[]): Usuario[]{
    console.log(this.searchForm.value);
    let filtered: Usuario[] = [];
    dataArray.forEach((res,ind) => {
      let inc = true;
      Object.entries(this.searchForm.value).forEach(([key,field],_ind)=>{
        if(inc === true && field !== null){
          if(field instanceof Date && (res[key] >= field && res[key] <= Date.now())){
            return;
          }
          else if(typeof(field)==='string' && res[key].startsWith(field)){
            return;
          }
          else if(typeof(field)==='boolean' && res[key]===field){
            return;
          }
          else{
            inc = false;
          }
        }
      })
      if(inc === true){
        filtered.push(res);
      }
    })
    console.log(dataArray,filtered);
    return filtered;
  }
  invokeSearch(){
    if(this.searchForm.value['creado_el'] !== null){
      this.searchForm.get('creado_el').setValue(new Date(this.searchForm.value['creado_el']));
    }
    if(this.searchForm.value['modificado_el'] !== null){
      this.searchForm.get('modificado_el').setValue(new Date(this.searchForm.value['modificado_el']));
    }
    //this.searchForm.get('');
    this.searchState="P";
    setTimeout(()=>{
      for (let i = 0; i < Math.floor(Math.random()*(100-1)+1); i++) {
        this.users.push({
         _id:Math.floor(Math.random()*(1000-1)+1),
         nombre:Math.random().toString(36).substr(2, 5),
         apellido:Math.random().toString(36).substr(2, 5),
         rol:'A',
         correo:Math.random().toString(36).substr(2, 5),
         estado:'',
         activo:true,
         creado_el:new Date(),
         modificado_el:new Date(),
        });
      }
      this.dataSource = new MatTableDataSource<Usuario>(this.dataFilter(this.users));
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
}
