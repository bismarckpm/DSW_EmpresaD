import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ViewEncapsulation } from '@angular/core';
import { UsuarioService } from '@core/services/usuario/usuario.service';
import { Usuario } from '@core/models/usuario';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-Login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  encapsulation: ViewEncapsulation.None,
  providers: [UsuarioService],
})
export class LoginComponent implements OnInit {
  public user: Usuario;
  public status;
  public token;

  constructor(
    private _route: ActivatedRoute,
    private _router: Router,
    private _userService: UsuarioService
  ) {}

  ngOnInit() {}

  onSubmit(form: NgForm) {
    console.log(form.value);
    console.log(form.valid);
    /*this._userService.signup(this.user).subscribe(
      response =>{
        console.log(response.status);
        this.status = 'success';
        //Guardar credenciales en el local Storage
        
        // Agregar codigo para ir a la ruta dependiendo del rol
        
        console.log(response);
      },
      error => {
        this.status='error'
        console.log(<any>error);
      }
    );*/
  }
}
