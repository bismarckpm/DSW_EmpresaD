import { Component,  OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { EstudioService } from '../../../shared/Services/estudio/estudio.service';

@Component({
  selector: 'app-analista',
  templateUrl: './analista.component.html',
  styleUrls: ['./analista.component.css']
})
export class AnalistaComponent implements OnInit {
  constructor(
    private route: ActivatedRoute,
    private router: Router,
  ){ }
  ngOnInit(): void {}

  onDir(_route:string):void {
    try{
    console.log(_route);
    this.router.navigate([_route]);
    }catch(e){
      console.log(e.message);
    }
  }
}