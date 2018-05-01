import {NgModule} from '@angular/core';
import {MatButtonModule, MatCheckboxModule, MatMenuModule, MatSidenavModule} from "@angular/material";

@NgModule({
  imports: [
    MatButtonModule,
    MatCheckboxModule,
    MatMenuModule,
    MatSidenavModule,
  ],
  exports: [
    MatButtonModule,
    MatCheckboxModule,
    MatMenuModule,
    MatSidenavModule,
  ],
  declarations: []
})
export class MaterialModule {
}
