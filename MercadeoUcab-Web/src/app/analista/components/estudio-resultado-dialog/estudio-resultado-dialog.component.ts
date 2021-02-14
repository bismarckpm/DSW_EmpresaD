import { 
	Component, 
	OnInit,
	TemplateRef,
  ViewChild,
  Input,           
} from '@angular/core';
import {
  Form,
  FormBuilder,
  FormGroup,
  Validators,
  FormControl,
} from '@angular/forms';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
@Component({
  selector: 'app-estudio-resultado-dialog',
  templateUrl: './estudio-resultado-dialog.component.html',
  styleUrls: ['./estudio-resultado-dialog.component.css']
})
export class EstudioResultadoDialogComponent implements OnInit {

  constructor(
  	private modalService: NgbModal,
  	private formBuilder: FormBuilder,
  ){}

  @Input() _estId: any;
  
  resForm:FormGroup;

  @ViewChild('respDiag')
  private modalContent: TemplateRef<EstudioResultadoDialogComponent>;
  private modalRef: NgbModalRef;
  
  ngOnInit(): void {
    this.resForm = this.formBuilder.group({
     result:null,
    });
  }
  sendRes(){

  }
  open() {
   this.modalRef = this.modalService.open(this.modalContent);
   this.modalRef.result.then();
 }
 close() {
   this.modalRef.close();
 }

}
