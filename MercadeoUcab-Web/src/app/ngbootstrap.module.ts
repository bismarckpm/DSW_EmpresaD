import { NgModule } from '@angular/core';
import { NgbModalModule, NgbPopoverModule,} from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  imports: [NgbModalModule, NgbPopoverModule,],
  exports:[NgbModalModule, NgbPopoverModule,]
})
export class LocalNgbModule {
}