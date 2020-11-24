import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-analista',
  templateUrl: './analista.component.html',
  styleUrls: ['./analista.component.css']
})
export class AnalistaComponent {
  constructor(
    private route: ActivatedRoute,
    private router: Router,
  ){ }
}