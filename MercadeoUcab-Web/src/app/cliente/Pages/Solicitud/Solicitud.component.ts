import {Component, OnInit, ViewChild} from '@angular/core';
import {Solicitud} from "@models/solicitud";
import {MatTableDataSource} from "@angular/material/table";
import {FormBuilder, FormGroup} from "@angular/forms";
import {SolicitudService} from "@core/services/solicitud/solicitud.service";
import {UpdateSolicitudDialogComponent} from "../../../cliente/components/dialogs/upd-solicitud-dialog/update-solicitud-dialog.component";
import {DeleteUserDialogComponent} from "../../../admin/components/dialogs/delete-user-dialog/delete-user-dialog.component";
import {Usuario} from "@models/usuario";
import {Marca} from "@models/marca";

@Component({
  selector: 'app-Solicitud',
  templateUrl: './Solicitud.component.html',
  styleUrls: ['./Solicitud.component.css']
})
export class SolicitudComponent implements OnInit {

  //CONTROL DE ESTADO DEL COMPONENTE
  op:string;
  searchState:string;//U.I,D
  solicitudes: Solicitud[] = [];

  //COLUMNAS DE TABLA DE RESULTADOS
  displayedColumns: string[] = ['id','selector','ops'];
  columnsToDisplay: string[] = this.displayedColumns.slice();

  //INDICE DE SOLICITUD SELECCIONADO
  solicitudSelection:number = 0;

  //LISTA DE SOLICITUDES DEVUELTOS EN BÚSQUEDA
  dataSource : MatTableDataSource<Solicitud>;
  solicitudTarget: Solicitud;
  //FORMULARIOS
  searchForm:FormGroup;
  searchModel:Solicitud;
  addForm:FormGroup;
  opStatus:string;//S,P,D
  userSolicitud:number;
  setUsuarioSolicitud(U:number){
    this.userSolicitud=U;
  }

  constructor(
    //private modalService: NgbModal,
    private formBuilder: FormBuilder,
    private _solicitudService: SolicitudService,
  ) {
    this.addForm = this.formBuilder.group({
      marca: null,
      presentacionTipo:null,
      subcategoria:null,
      usuario:null,
      activo:null,
      estado:'Activo',
    });
    this.searchForm = this.formBuilder.group({
      usuario:null,//SELECT
      presentacion:null,
      subcategoria:null,
      tipo:null,
      marca:null,//SELECT
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
  getSolicitudes(){
    this._solicitudService.getSolicitudes().subscribe(
      (response) => {
        console.log(response);
      },
      (error) => {
        console.log(error);
      }
    )
  }

  addSolicitudd(data){
    this._solicitudService.createSolicitud(data).subscribe(
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
    this.getSolicitudes();
  }
  @ViewChild('updSolicitud') private updComponent:UpdateSolicitudDialogComponent;
  async openUpdModal() {
    return await this.updComponent.open();
  }
  serviceInvoke(){
    /*
    "nombre": data.nombre,
    "apellido": data.apellido,
    "estado": data.estado,
    "rol": data.rol,
    "correo": data.correo
    */
    //FALTA VALIDACION
    //console.log(this.addForm.value);
    this.addSolicitudd(this.addForm.value);
    this.opStatus="P";
    console.log(this.op);
    console.log(this.opStatus);
    console.log(this.opStatus);
    setTimeout(()=>{
      this.addForm = this.formBuilder.group({
        marca: null,
        usuario:null,
        presentacionTipo:null,
        subcategoria:null,
        activo:null,
        estado:'Activo',
      });
      this.opStatus="D";
    },3000);
  }
  selectSolicitud(id: number,data:Solicitud){
    if(id === this.solicitudSelection){
      this.solicitudSelection = 0;
      this.solicitudTarget=null;
    }
    else{
      this.solicitudSelection=id;
      this.solicitudTarget=data;
    }
  }
  isSelected(id: number):boolean{
    if(id === this.solicitudSelection){
      return true;
    }
    return false;
  }
  dataFilter(dataArray:Solicitud[]): Solicitud[]{
    console.log(this.searchForm.value);
    let filtered: Solicitud[] = [];
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
    this.solicitudes = [];
    this.solicitudSelection=0;
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
        this.solicitudes.push({
          _id:Math.floor(Math.random()*(100-1)+1),
          estado:"activo",
          usuario:{
            _id:Math.floor(Math.random()*(100-1)+1),
            nombre:"A",
            apellido:"B",
            rol:"adminin",
            correo:"a@g.com",
            estado:"activo",
            activo:true,
            creado_el:new Date(),
            modificado_el:new Date(),
          },
          marca:{
            _id:Math.floor(Math.random()*(100-1)+1),
            nombre:"A",
            activo:true,
            creado_el:new Date(),
            modificado_el:new Date(),
          },
          activo:true,
          creado_el:new Date(),
          modificado_el:new Date(),
        });
      }
      this.dataSource = new MatTableDataSource<Solicitud>(this.dataFilter(this.solicitudes));
      this.searchState="D";
    },3000);
  }
  setOperation(chOp:string){
    this.op=chOp;
    console.log(this.searchState);
    if(chOp !== ''){
      this.searchState="I";
      this.opStatus="S";
      this.setUsuarioSolicitud(null);
    }
    else{
      this.searchState="U";
    }
  }
  doSearch(){
    this.searchState="I";
  }
}
