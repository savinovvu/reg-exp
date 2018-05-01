import {NgModule} from '@angular/core';
import {MatButtonModule, MatCheckboxModule, MatMenuModule, MatSidenavModule, MatTableModule} from "@angular/material";

@NgModule({
  imports: [
    MatButtonModule,
    MatCheckboxModule,
    MatMenuModule,
    MatSidenavModule,
    MatTableModule
  ],
  exports: [
    MatButtonModule,
    MatCheckboxModule,
    MatMenuModule,
    MatSidenavModule,
    MatTableModule
  ],
  declarations: []
})
export class MaterialModule {
}
