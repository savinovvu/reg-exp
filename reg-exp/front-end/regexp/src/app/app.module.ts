import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RootRouter } from './app.routes';
import { RestDataSourceService } from './services/rest/rest-data-source.service';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { AppComponent } from './appComponent/app.component';
import { AuthGuard } from './services/guards/auth.guard';
import { TranslateModule, TranslateLoader } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { CookieService } from 'ngx-cookie-service';
import {
  NbCardModule,
  NbChatModule,
  NbDatepickerModule,
  NbDialogModule,
  NbMenuModule,
  NbSidebarModule,
  NbToastrModule,
  NbWindowModule
} from '@nebular/theme';
import { ThemeModule } from './@theme/theme.module';
import { JwtModule } from '@auth0/angular-jwt';
import { AuthenticationService } from './services/jwt/authentication.service';
import { UserTableComponent } from './pages/user-table/user-table.component';
import { CourseComponent } from './pages/course/course.component';
import { ProposeTaskComponent } from './pages/propose-task/propose-task.component';
import { UsefulLinksComponent } from './pages/useful-links/useful-links.component';
import { SmartTableComponent } from './components/tables/smart-table/smart-table.component';
import { Ng2SmartTableModule } from 'ng2-smart-table';


export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http);
}


export function jwtLoader(): string {
  return localStorage.getItem('jwt_token');
}



@NgModule({
  declarations: [
    AppComponent,
    UserTableComponent,
    CourseComponent,
    ProposeTaskComponent,
    UsefulLinksComponent,
    SmartTableComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    RootRouter,
    HttpClientModule,
    FormsModule,


    TranslateModule.forRoot(
      ({
        loader: {
          provide: TranslateLoader,
          useFactory: HttpLoaderFactory,
          deps: [HttpClient]
        }
      })
    ),
    ThemeModule.forRoot(),

    NbSidebarModule.forRoot(),
    NbMenuModule.forRoot(),
    NbDatepickerModule.forRoot(),
    NbDialogModule.forRoot(),
    NbWindowModule.forRoot(),
    NbToastrModule.forRoot(),
    NbCardModule,
    Ng2SmartTableModule,

    NbChatModule.forRoot({
      messageGoogleMapKey: 'AIzaSyA_wNuCzia92MAmdLRzmqitRGvCF7wCZPY',
    }),
    JwtModule.forRoot({
      config: {
        tokenGetter: jwtLoader
      }
    })
  ],
  providers: [
    RestDataSourceService, AuthGuard, CookieService, AuthenticationService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
