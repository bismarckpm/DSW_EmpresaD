import { Component,  OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

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

  ngOnInit(): void {
    this.router.navigate(['analist/overview'],{relativeTo:this.route});
  }
}