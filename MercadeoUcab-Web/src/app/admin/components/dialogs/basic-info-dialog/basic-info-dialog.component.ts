import {
  Component,
  Input,
  OnInit,
  TemplateRef,
  ViewChild,
} from '@angular/core';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-basic-info-dialog',
  templateUrl: './basic-info-dialog.component.html',
  styleUrls: ['./basic-info-dialog.component.css']
})
export class BasicInfoDialogComponent implements OnInit {

  constructor(
  	 private modalService: NgbModal,
  ) { }

  @ViewChild('info')
  private modalContent: TemplateRef<BasicInfoDialogComponent>;
  private modalRef: NgbModalRef;

  @Input() _tipo: string;

  ngOnInit(): void {
  }

  open() {
    this.modalRef = this.modalService.open(this.modalContent);
    this.modalRef.result.then();
  }
  close() {
    this.modalRef.close();
  }
}
