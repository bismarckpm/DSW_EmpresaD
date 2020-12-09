import { Component, Injectable, Input, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { UsuarioService } from '@core/services/usuario/usuario.service';
@Component({
  selector: 'app-update-user-dialog',
  templateUrl: './update-user-dialog.component.html',
  styleUrls: ['./update-user-dialog.component.css']
})
@Injectable()
export class UpdateUserDialogComponent implements OnInit {
  opStatus:string;//S,P,D
  updForm : FormGroup;

  @ViewChild('updUser') private modalContent: TemplateRef<UpdateUserDialogComponent>;
  private modalRef: NgbModalRef;
  constructor(private modalService: NgbModal,private formBuilder: FormBuilder,  _service:UsuarioService){}
  @Input() _userSelection : number;

  ngOnInit(): void {
    this.opStatus="S";
    this.updForm = this.formBuilder.group({
      nombre:'',
    });
  }
  open(){
    this.modalRef =this.modalService.open(this.modalContent);
    this.modalRef.result.then();
  }
  close(){
    this.opStatus="S";
    this.modalRef.close();
  }
  invokeService(){
    this.opStatus="P";
    setTimeout(()=>{
      this.opStatus="D";
    },3000);
  }
}
