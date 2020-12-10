import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css'],
})
export class ResetPasswordComponent implements OnInit {
  constructor() {}

  ngOnInit(): void {}

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
