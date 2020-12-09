import { NgModule } from '@angular/core';
import {NgbPaginationModule, NgbAlertModule, NgbModalModule} from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  imports: [NgbPaginationModule, NgbAlertModule, NgbModalModule],
  exports:[NgbModalModule]
})
export class LocalNgbModule {
}