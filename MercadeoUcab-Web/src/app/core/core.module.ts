import { NgModule, Optional, SkipSelf } from '@angular/core';
import { CommonModule } from '@angular/common';



@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ]
})
export class CoreModule {
  constructor( @Optional() @SkipSelf() core: CoreModule){
    if (core){
      throw new Error(
        'The CoreModule its already loaded. Import it in the AppModule only'
      );
    }
  }
}
