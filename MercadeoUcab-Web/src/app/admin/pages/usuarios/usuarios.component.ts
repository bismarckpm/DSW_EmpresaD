import {Component, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators, FormControl} from '@angular/forms';
import {MatTableDataSource} from '@angular/material/table';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import { Usuario } from '@models/usuario';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { UpdateUserDialogComponent } from '../../components/dialogs/update-user-dialog/update-user-dialog.component';
import { DeleteUserDialogComponent } from '../../components/dialogs/delete-user-dialog/delete-user-dialog.component';
import { UsuarioService } from '@core/services/usuario/usuario.service';
import { SubCategoria } from '@core/models/subcategoria';
import { Categoria } from '@core/models/categoria';
import { SubcategoriaService } from '@core/services/subcategoria/subcategoria.service';

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
  userTarget: Usuario;
  //FORMULARIOS
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
     private _subCategoryService: SubcategoriaService
    ) {
    this.addForm = this.formBuilder.group({
      nombre: null,
      apellido:null,
      rol:null,
      estado:'Activo',
      correo:null,
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
  /*getTarget(id:number){
    this.users.forEach((user,ind) => {
      if(user._id === id){

      }
    });
  };*/
  getUsers(){
    this._userService.getUsers().subscribe(
      (response) => {
        console.log(response);
        this.users = response.data;
        console.log(this.users);
      },
      (error) => {
        console.log(error);
      }
    )
  }


  addUser(data){
    this._userService.createUser(data).subscribe(
      (response) => {
        console.log(response);
      },
      (error) => {
        console.log(error);
      }
    )
  }
  addSubCategory(data){
    this._subCategoryService.createSubCategoria(data).subscribe(
      (response) => {
        console.log(response);
      },
      (error) => {
        console.log(error);
      }
    )
  }

  ngOnInit(): void {
    this.setOperation('');
    this.searchState="U";
    this.getUsers();
    //let usuario = new Usuario( 1,"Pedro", "Perez", "encuestado","op@gmai.com","activo");
    //let subcategoria = new SubCategoria(1,"subFront", new Categoria(1,"nose"));
    //this.addSubCategory(subcategoria);
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
    /*
    "nombre": data.nombre,
    "apellido": data.apellido,
    "estado": data.estado,
    "rol": data.rol,
    "correo": data.correo
    */
    //FALTA VALIDACION 
    //console.log(this.addForm.value);
    this.addUser(this.addForm.value);
    this.opStatus="P";
    setTimeout(()=>{
      this.addForm = this.formBuilder.group({
      nombre:null,
      apellido:null,
      rol:null,
      estado:'Activo',
      correo:null,
      });
      this.opStatus="D";
    },3000);
  }
  selectUser(id: number,data:Usuario){
    if(id === this.userSelection){
      this.userSelection = 0;
      this.userTarget=null;
    }
    else{
      this.userSelection=id;
      this.userTarget=data;
    }
  }
  isSelected(id: number):boolean{
    if(id === this.userSelection){
      return true;
    }
    return false;
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
    //this.users = [];
    this.userSelection=0;
    if(this.searchForm.value['creado_el'] !== null){
      this.searchForm.get('creado_el').setValue(new Date(this.searchForm.value['creado_el']));
    }
    if(this.searchForm.value['modificado_el'] !== null){
      this.searchForm.get('modificado_el').setValue(new Date(this.searchForm.value['modificado_el']));
    }
    //this.searchForm.get('');
    this.searchState="P";
    setTimeout(()=>{
      /*for (let i = 0; i < Math.floor(Math.random()*(100-1)+1); i++) {
        this.users.push({
         _id:Math.floor(Math.random()*(1000-1)+1),
         nombre:Math.random().toString(36).substr(2, 5),
         apellido:Math.random().toString(36).substr(2, 5),
         rol:'Administrador',
         correo:Math.random().toString(36).substr(2, 5),
         estado:'Activo',
        });
      }*/
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
