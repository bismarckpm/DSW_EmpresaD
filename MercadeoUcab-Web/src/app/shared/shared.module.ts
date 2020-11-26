import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SharedRoutingModule } from './shared-routing.module';
import { AngularMaterialModule } from './angular-material/angular-material.module';


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    SharedRoutingModule,
    AngularMaterialModule
  ],
  exports:[AngularMaterialModule]
})
export class SharedModule { }
