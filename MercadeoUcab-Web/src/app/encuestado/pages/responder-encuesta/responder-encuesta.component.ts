import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
@Component({
  selector: 'app-responder-encuesta',
  templateUrl: './responder-encuesta.component.html',
  styleUrls: ['./responder-encuesta.component.css']
})
export class ResponderEncuestaComponent implements OnInit {
  _Id:number = 0;
  constructor(
    private route: ActivatedRoute,
    private router:Router,
    ){}

  ngOnInit(): void {
    console.log(this.route.snapshot.paramMap.get('id'));
    this._Id = parseInt(this.route.snapshot.paramMap.get('id'),10);
  }
}
