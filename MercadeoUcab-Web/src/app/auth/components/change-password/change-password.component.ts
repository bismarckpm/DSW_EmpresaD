import { analyzeAndValidateNgModules } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { UsuarioService } from '@core/services/usuario/usuario.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css'],
})
export class ChangePasswordComponent implements OnInit {
  public status;
  public model: any = {};
  constructor(
    private _userService: UsuarioService,
    private _route: ActivatedRoute,
    private _router: Router
  ) {}

  ngOnInit() {
    this.model.correo = this._route.snapshot.queryParamMap.get('correo');
  }

  changePassword(data) {
    this._userService.forgotPassword(data).subscribe(
      (response) => {
        console.log(response);
        alert('Se ha cambiado la clave exitosamente');
        this._router.navigate(['login']);
      },
      (error) => {
        this.status = 'error';
        console.log(<any>error);
        alert('Ha ocurrido un error');
        this._router.navigate(['login']);
      }
    );
  }
  onSubmit() {
    let user: any = {};
    user.correo = this.model.correo;
    user.password = this.model.password;
    if (this.model.password === this.model.confirmPassword) {
      this.changePassword(user);
    } else {
      alert('Claves no coinciden');
    }
  }
}
