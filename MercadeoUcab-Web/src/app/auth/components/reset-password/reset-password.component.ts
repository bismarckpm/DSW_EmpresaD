import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { UsuarioService } from '@core/services/usuario/usuario.service';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css'],
})
export class ResetPasswordComponent implements OnInit {
  public model: any = {};
  constructor(private _userService: UsuarioService, private _router: Router) {}

  ngOnInit(): void {}

  requestPasswordChange(data) {
    this._userService.forgotPasswordRequest(data).subscribe(
      (response) => {
        console.log(response);
        alert('Se ha enviado un correo para el cambio de clave');
        this._router.navigate(['login']);
      },
      (error) => {
        console.log(<any>error);
        alert('Ha ocurrido un error');
      }
    );
  }
  onSubmit() {
    this.requestPasswordChange(this.model);
  }
}
