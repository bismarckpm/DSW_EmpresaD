import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboardCliente.component.html',
  styleUrls: ['./dashboardCliente.component.css'],
})
export class dashboardClienteComponent implements OnInit {
  constructor(private route: ActivatedRoute, private router: Router) {}
  openNote: boolean=false;

  ngOnInit(): void {
    setTimeout(()=>{
      this.openNote=true;
      setTimeout(() => {
        this.openNote=false;
      },10000)
    },2000);
  }
  onDir(_route: string): void {
    try {
      //console.log(_route);
      this.router.navigate([_route]);
    } catch (e) {
      console.log(e.message);
    }
  }
}
