import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ViewEncapsulation } from '@angular/core';
import { UsuarioService } from '@core/services/usuario/usuario.service';
import { Usuario } from '@core/models/usuario';
import { NgForm } from '@angular/forms';
import { Ldap } from '@core/models/ldap';

@Component({
  selector: 'app-Login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  encapsulation: ViewEncapsulation.None,
  providers: [UsuarioService],
})
export class LoginComponent implements OnInit {
  public token;
  public model: Ldap = new Ldap('', '');

  constructor(
    private _route: ActivatedRoute,
    private _router: Router,
    private _userService: UsuarioService
  ) {}

  ngOnInit() {}

  signup(user) {
    this._userService.signup(user).subscribe(
      (response: any) => {
        console.log(response);
        //Guardar credenciales en el local Storage
        localStorage.setItem('_id', response.data._id);
        localStorage.setItem('rol', response.data.rol);
        // Agregar codigo para ir a la ruta dependiendo del rol
        if (response.data.rol === 'administrador') {
          this._router.navigate(['administrador']);
        }
        if (response.data.rol === 'encuestado') {
          this._router.navigate(['encuestado']);
        }
        if (response.data.rol === 'cliente') {
          this._router.navigate(['cliente']);
        }
        if (response.data.rol === 'analista') {
          this._router.navigate(['analista']);
        }
        console.log(response);
      },
      (error) => {
        console.log(<any>error);
      }
    );
  }
  onSubmit() {
    this.signup(this.model);
  }
}
