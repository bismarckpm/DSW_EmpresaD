import { Component, Injectable, Input, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { UsuarioService } from '@core/services/usuario/usuario.service';
import { Usuario } from '@models/usuario';
@Component({
  selector: 'app-update-user-dialog',
  templateUrl: './update-user-dialog.component.html',
  styleUrls: ['./update-user-dialog.component.css']
})
@Injectable()
export class UpdateUserDialogComponent implements OnInit {
  opStatus:string;//S,P,D,E
  updForm : FormGroup;

  @ViewChild('updUser') private modalContent: TemplateRef<UpdateUserDialogComponent>;
  private modalRef: NgbModalRef;
  constructor(private modalService: NgbModal,private formBuilder: FormBuilder,private  _service:UsuarioService){}
  @Input() _userSelection : number;
  //MODELO O REGISTRO DE DATOS A MODIFICAR RECIBIDO POR PROPIEDADES DESDE EL COMPONENTE PADRE
  @Input() _user : Usuario;
  //MODELO DE DATOS 
  toService: any;
  
  ngOnInit(): void {
    this.opStatus="S";
    this.updForm = this.formBuilder.group({
      nombre:null,
      apellido:null,
      estado:null,
      activo:null,
    });
  }
  open(){
    this.modalRef =this.modalService.open(this.modalContent);
    this.modalRef.result.then();
    //console.log('Usuario: ',this._user);
    
    /*

    ESTA ES LA VARIABLE/MODELO ENVIADO AL SERVICIO DE MODIFICACION
    UNA VEZ DESPLEGADO EL DIALOGO SE LE ASIGNAN LOS VALORES NO CAMBIANTES
    DE LA ENTIDAD Y SE DEJA LOS ABIERTOS A CAMBIOS EN NULL

    */
    this.toService ={
      _id:this._user._id,
      nombre:null,
      apellido:null,
      estado:null
    };


  }
  close(){
    this.opStatus="S";
    this.updForm = this.formBuilder.group({
      nombre:null,
      apellido:null,
      estado:null,
      activo:null,
    });
    this.modalRef.close();
  }
  updateUser(id,data){
    this._service.updateUser(id,data).subscribe(
      (response) => {
        console.log(response);
        this.opStatus="D";
      },
      (error) => {
        console.log(error);
        this.opStatus="E";
      }
    )
  }
  invokeService(){
    /*

    PROCESO DE ASIGNACION DE VALORES ANTIGUOS O NUEVOS AL MODELO A SER ENVIADO 
    A SERVICIO DE MODIFICACION

    */
    Object.entries(this.updForm.value).forEach(([key,field],ind)=>{
      if(field !== null){
        this.toService[key]=field;
      }
      else{
        this.toService[key] = this._user[key];
      }
    })
    //console.log(this.toService,this.updForm.value);
    this.opStatus="P";
    this.updateUser(this._userSelection,this.toService);
  }
}
