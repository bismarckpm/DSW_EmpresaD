import { Component, Injectable, Input, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { SolicitudService } from '@core/services/solicitud/solicitud.service';
import { Solicitud } from '@models/solicitud';
import {Usuario} from '@models/usuario';
import {Marca} from '@models/marca';
import {MarcaService} from '@core/services/marca/marca.service';
import {SubcategoriaService} from '@core/services/subcategoria/subcategoria.service';
import {PresentacionService} from '@core/services/presentacion/presentacion.service';
import {TipoService} from '@core/services/tipo/tipo.service';
import {SubCategoria} from '@models/subcategoria';
import {Presentacion} from '@models/presentacion';
import {Tipo} from '@models/tipo';
@Component({
  selector: 'app-update-solicitud-dialog',
  templateUrl: './update-solicitud-dialog.component.html',
  styleUrls: ['./update-solicitud-dialog.component.css']
})
@Injectable()
export class UpdateSolicitudDialogComponent implements OnInit {
  opStatus: string; // S,P,D,E
  updForm: FormGroup;
  private marcaServices: MarcaService;
  private subcategoriaService: SubcategoriaService;
  private  presentacionService: PresentacionService;
  private tipoService: TipoService;

  solicitudes: Solicitud[] = [];
  solicitudes2: Solicitud[] = [];
  marcas2: Marca[] = [];
  subcategorias: SubCategoria[] = [];
  presentaciones: Presentacion[] = [];
  tipos: Tipo[] = [];

  @ViewChild('updSolicitud')
  private modalContent: TemplateRef<UpdateSolicitudDialogComponent>;
  private modalRef: NgbModalRef;
  constructor(private modalService: NgbModal, private formBuilder: FormBuilder, private  _service: SolicitudService){}
  @Input() _solicitudSelection: number;
  @Input() _solicitud: Solicitud;
  toService: any;

  ngOnInit(): void {
    this.opStatus = 'S';
    this.updForm = this.formBuilder.group({
      usuario: null,
      marca: null,
      presentacion: null,
      tipo: null,
      subcategoria: null,
      estado: null,
      activo: null,
    });
  }
  open(){
    this.modalRef = this.modalService.open(this.modalContent);
    this.modalRef.result.then();
    // console.log('Usuario: ',this._user);
    this.toService = {
      _id: this._solicitud._id,
      tipo: null,
      subcategoria: null,
      marca: null,
      estado: null
    };
  }
  close(){
    this.opStatus = 'S';
    this.updForm = this.formBuilder.group({
      usuario: null,
      marca: null,
      estado: null,
      activo: null,
    });
    this.modalRef.close();
  }

  getPresentacionUPD() {
    this.presentacionService.getPresentaciones().subscribe(
      (response) => {
        console.log(response);
        this.presentaciones = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
  }

  getTiposUPD() {
    this.tipoService.getTipos().subscribe(
      (response) => {
        console.log(response);
        this.tipos = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
  }

  getSubcategoriaUPD() {
    this.subcategoriaService.getSubCategorias().subscribe(
      (response) => {
        console.log(response);
        this.subcategorias = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
  }

  getMarcasUPD() {
    this.marcaServices.getMarcas().subscribe(
      (response) => {
        console.log(response.data.nombre);
        this.marcas2 = response.data;
      },
      (error) => {
        console.log(error);
      }
    );
  }

  updateUser(id, data){
    this._service.updateSolicitud(id, data).subscribe(
      (response: any) => {
        console.log(response);
        if (response.status === 200) {
          // Se hace lo que se quiera en exito
          alert(response.message);
        }
      },
      (error) => {
        console.log(error);
      }
    );
  }
  invokeService(){
    Object.entries(this.updForm.value).forEach(([key, field], ind) => {
      if (field !== null){
        this.toService[key] = field;
      }
      else{
        this.toService[key] = this._solicitud[key];
      }
    });
    // console.log(this.toService,this.updForm.value);
    this.opStatus = 'P';
    const toUpd: any = {};
    const values = this.updForm.value();
    toUpd.estado = values.estado;
    toUpd.usuario = 2;
    toUpd.marca = values.marca;
    toUpd.tipo = values.tipo;
    toUpd.subcategoria = values.subCategoria;
    toUpd.presentacion = values.presentacion;
    this.updateUser(2, toUpd);
    /*setTimeout(()=>{
      this.opStatus="D";
    },3000);*/
  }
}
