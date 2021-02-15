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
import { EstudioService } from '@core/services/estudio/estudio.service';
@Component({
  selector: 'app-estudio-resultado-dialog',
  templateUrl: './estudio-resultado-dialog.component.html',
  styleUrls: ['./estudio-resultado-dialog.component.css']
})
export class EstudioResultadoDialogComponent implements OnInit {

  constructor(
  	private modalService: NgbModal,
  	private formBuilder: FormBuilder,
    private _estudioService: EstudioService,
  ){}

  @Input() _estId: any;
  @Input() _est: any;
  opStatus: string = 'S';
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
    const {encuestas_esperadas,tipo} = this._est;

    let updData = {
      encuestasEsperadas:encuestas_esperadas,
      tipo,
      estado:'Culminado',
      comentarios:this.resForm.get('result').value};
    console.log(updData);
    this.opStatus="P";
    this._estudioService.updateEstudio( this._estId, updData).subscribe(
    (res) => {
      console.log(res);
      this.opStatus="D";
    },
    (err) => {
      console.log(err);
       this.opStatus="E";
    }
    );
  }
  open() {
   this.modalRef = this.modalService.open(this.modalContent);
   this.modalRef.result.then();
 }
 close() {
   this.modalRef.close();
 }

}
