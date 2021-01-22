import { Component, OnInit, ViewChild } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators,
  FormControl,
} from '@angular/forms';
import { MatTableDataSource } from '@angular/material/table';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Usuario } from '@models/usuario';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { UpdateUserDialogComponent } from '../../components/dialogs/update-user-dialog/update-user-dialog.component';
import { DeleteUserDialogComponent } from '../../components/dialogs/delete-user-dialog/delete-user-dialog.component';
import { BasicInfoDialogComponent } from '../../components/dialogs/basic-info-dialog/basic-info-dialog.component';
import { UsuarioService } from '@core/services/usuario/usuario.service';
import { SubCategoria } from '@core/models/subcategoria';
import { Categoria } from '@core/models/categoria';

@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.component.html',
  styleUrls: ['./usuarios.component.css'],
  providers: [MatDatepickerModule],
})
export class UsuariosComponent implements OnInit {
  //CONTROL DE ESTADO DEL COMPONENTE
  op: string;
  searchState: string; //U.I,D
  users: Usuario[] = [];

  //COLUMNAS DE TABLA DE RESULTADOS
  displayedColumns: string[] = ['id', 'selector', 'ops'];
  columnsToDisplay: string[] = this.displayedColumns.slice();

  //INDICE DE USUARIO SELECCIONADO
  userSelection: number = 0;

  //LISTA DE USUARIOS DEVUELTOS EN BÚSQUEDA
  dataSource: MatTableDataSource<Usuario>;
  userTarget: Usuario = null;
  //FORMULARIOS
  searchForm: FormGroup;
  searchModel: Usuario;
  addForm: FormGroup;
  opStatus: string; //S,P,D
  userRole: string = '';
  setTipoUsuario(tipo: string) {
    this.userRole = tipo;
  }

  constructor(
    private formBuilder: FormBuilder,
    private _userService: UsuarioService
  ) {
    //FORMULARIO PARA REGISTRAR USUARIO
    //(FORMGROUP)
    this.addForm = this.formBuilder.group({
      //CAMPOS REQUERIDOS PARA EL REGISTRO (SIN IMPORTAR LA DATA QUE ERCIBEN DEBEN SER INSTANCIADOS ACA)
      //(FORMCONTROLNAME)
      nombre: null,
      apellido: null,
      rol: null,
      estado: 'Activo',
      correo: null,
      password: null,
    });

    this.searchForm = this.formBuilder.group({
      nombre: null,
      apellido: null,
      rol: null,
      estado: null,
      activo: null,
      creado_el: null,
      modificado_el: null,
    });
  }
  getUsers() {
    this.searchState = 'P';
    this._userService.getUsers().subscribe(
      (response) => {
        //console.log(response);
        //SE OBTIENE LA RESPUESTA DEL SERVICIO
        this.users = response.data;
        //console.log(this.users);
        //SE GENERA LA NUEVA TABLA DE RESULTADOS FILTRANDO SEGUN CAMPOS DADOS POR EL USUARIO
        this.dataSource = new MatTableDataSource<Usuario>(
          this.dataFilter(this.users)
        );
        this.searchState = 'D';
      },
      (error) => {
        //console.log(error);
        this.users = [
        {
          _id: Math.floor(Math.random() * (1000 - 1) + 1),
          nombre: Math.random().toString(36).substr(2, 5),
          apellido: Math.random().toString(36).substr(2, 5),
          rol: 'Administrador',
          correo: Math.random().toString(36).substr(2, 5),
          estado: 'Activo',
        }
        ];
        this.dataSource = new MatTableDataSource<Usuario>(
          this.dataFilter(this.users)
        );
        this.searchState = 'D';
      }
    );
  }

  addUser(data) {
    console.log(data.password);
    this._userService.createUser(data).subscribe(
      (response: any) => {
        console.log(response);
        if (response.status === 200) {
          //Se hace lo que se quiera en exito
          //alert(response.message);
        }
        this.opStatus = 'D';
      },
      (error) => {
        console.log(error);
        this.opStatus = 'E';
        //alert(error.error.message);
      }
    );
  }

  updateUser(id, data) {
    this._userService.updateUser(id, data).subscribe(
      (response) => {
        console.log(response);
        //alert('Se modifico el usuario correctamente');
      },
      (error) => {
        console.log(error);
      }
    );
  }

  deleteUser(id, data) {
    this._userService.deleteUser(id, data).subscribe(
      (response) => {
        console.log(response);
        //alert('Se modifico el usuario correctamente');
      },
      (error) => {
        console.log(error);
      }
    );
  }

  ngOnInit(): void {
    this.setOperation('');
    this.searchState = 'U';
    //this.getUsers();
    //let usuario = new Usuario( 1,"Pedro", "Perez", "encuestado","op@gmai.com","activo");
    //let subcategoria = new SubCategoria(1,"subFront", new Categoria(1,"nose"));
    //this.addSubCategory(subcategoria);
  }
  @ViewChild('updUser') private updComponent: UpdateUserDialogComponent;
  async openUpdModal() {
    return await this.updComponent.open();
  }
  @ViewChild('delUser') private delComponent: DeleteUserDialogComponent;
  async openDelModal() {
    return await this.delComponent.open();
  }
  @ViewChild('info') private infoComponent: BasicInfoDialogComponent;
  async openInfoModal() {
    return await this.infoComponent.open();
  }
  //METODO ENCARGADO DE DISPARAR PETICION DE REGISTRO
  serviceInvoke(role: string) {
    //FALTA VALIDACION

    /*

    PARA LEER LOS CAMPOS DEL FORMULARIO SE ACCEDE AL OBJETO this.addForm.value
    ESTO NO ES MAS QUE DONDE QUEDA CADA CAMPO COMO UNA PROPIEDAD CUYO CONTENIDO 
    ES EL VALOR DADO POR EL USUARIO

    */
    const values = this.addForm.value;
    const toAdd = {
      nombre: values.nombre,
      password: values.password,
      apellido: values.apellido,
      rol: values.rol,
      estado: values.estado,
      correo: values.correo,
    };
    //console.log(this.addForm.value);
    //console.log('-------------------');
    console.log(toAdd);

    this.opStatus = 'P';
    // En este caso los valores de los campos son iguales
    this.addUser(toAdd);
    this.addForm = this.formBuilder.group({
      nombre: null,
      apellido: null,
      rol: null,
      estado: 'Activo',
      correo: null,
      password: null,
    });
    /*setTimeout(() => {
      this.addForm = this.formBuilder.group({
        nombre: null,
        apellido: null,
        rol: null,
        estado: 'Activo',
        correo: null,
      });
      this.opStatus = 'D';
    }, 3000);*/
  }
  selectUser(id: number, data: Usuario) {
    if (id === this.userSelection) {
      this.userSelection = 0;
      this.userTarget = null;
    } else {
      this.userSelection = id;
      this.userTarget = data;
    }
  }
  isSelected(id: number): boolean {
    if (id === this.userSelection) {
      return true;
    }
    return false;
  }
  //PROCESO DE FILTRADO GENERAL
  dataFilter(dataArray: Usuario[]): Usuario[] {
    //console.log(this.searchForm.value);
    let filtered: Usuario[] = [];
    dataArray.forEach((res, ind) => {
      let inc = true;
      Object.entries(this.searchForm.value).forEach(([key, field], _ind) => {
        if (inc === true && field !== null) {
          if (
            field instanceof Date &&
            res[key] >= field &&
            res[key] <= Date.now()
          ) {
            return;
          } else if (typeof field === 'string' && res[key].startsWith(field)) {
            return;
          } else if (typeof field === 'boolean' && res[key] === field) {
            return;
          } else {
            inc = false;
          }
        }
      });
      if (inc === true) {
        filtered.push(res);
      }
    });
    //console.log(dataArray, filtered);
    return filtered;
  }
  invokeSearch() {
    //this.users = [];
    this.userSelection = 0;
    if (this.searchForm.value['creado_el'] !== null) {
      this.searchForm
        .get('creado_el')
        .setValue(new Date(this.searchForm.value['creado_el']));
    }
    if (this.searchForm.value['modificado_el'] !== null) {
      this.searchForm
        .get('modificado_el')
        .setValue(new Date(this.searchForm.value['modificado_el']));
    }
    this.searchState = 'P';
    this.getUsers();
    /*setTimeout(() => {
    for (let i = 0; i < Math.floor(Math.random()*(100-1)+1); i++) {
          this.users.push({
         _id:Math.floor(Math.random()*(1000-1)+1),
         nombre:Math.random().toString(36).substr(2, 5),
         apellido:Math.random().toString(36).substr(2, 5),
         rol:'Administrador',
         correo:Math.random().toString(36).substr(2, 5),
         estado:'Activo',
        });
      }
      this.dataSource = new MatTableDataSource<Usuario>(
        this.dataFilter(this.users)
      );
      this.searchState = 'D';
    }, 3000);*/
  }
  setOperation(chOp: string) {
    this.op = chOp;
    if (chOp !== '') {
      this.searchState = 'I';
      this.opStatus = 'S';
      this.setTipoUsuario('');
    } else {
      this.searchState = 'U';
    }
  }
  doSearch() {
    this.searchState = 'I';
  }
}
