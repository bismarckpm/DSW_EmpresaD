import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboardCliente.component.html',
  styleUrls: ['./dashboardCliente.component.css']
})
export class dashboardClienteComponent implements OnInit {

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    ){}

  slideConfig = {"slidesToShow": 4, "slidesToScroll": 4};

  ngOnInit(): void {
  }
  onDir(_route:string):void {
    try{
    console.log(_route);
    this.router.navigate([_route]);
    }catch(e){
      console.log(e.message);
    }
  }
}
