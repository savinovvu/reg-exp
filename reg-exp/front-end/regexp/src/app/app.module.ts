import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RootRouter } from './app.routes';
import { RestDataSourceService } from './services/rest/rest-data-source.service';
import { HTTP_INTERCEPTORS, HttpClient, HttpClientModule } from '@angular/common/http';
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
  NbWindowModule,
  NbAccordionModule,
  NbActionsModule,
  NbButtonModule, NbCheckboxModule, NbRadioModule, NbSelectModule, NbInputModule, NbTabsetModule, NbRouteTabsetModule
} from '@nebular/theme';
import { ThemeModule } from './@theme/theme.module';
import { JwtModule } from '@auth0/angular-jwt';
import { AuthenticationService } from './services/jwt/authentication.service';
import { CourseComponent } from './pages/course/course.component';
import { ProposeTaskComponent } from './pages/propose-task/propose-task.component';
import { UsefulLinksComponent } from './pages/useful-links/useful-links.component';
import { UserRatingComponent } from './pages/rating/user-rating.component';
import { Ng2SmartTableModule } from 'ng2-smart-table';
import { TokenInterceptor } from "./services/interceptors/token-interceptor.service";
import { ChallengeComponent } from './pages/challenge/challenge.component';
import { LevelComponent } from './pages/level/level.component';
import { TemporaryLoginComponent } from './pages/temporary-login/temporary-login.component';
import { SettingsComponent } from './pages/settings/root/settings.component';
import { UserSettingsComponent } from './pages/settings/user/user-settings/user-settings.component';
import { RatingSettingsComponent } from './pages/settings/rating/rating-settings/rating-settings.component';


export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http);
}


export function jwtLoader(): string {
  return localStorage.getItem('jwt_token');
}



@NgModule({
  declarations: [
    AppComponent,
    UserRatingComponent,
    CourseComponent,
    ProposeTaskComponent,
    UsefulLinksComponent,
    UserRatingComponent,
    ChallengeComponent,
    LevelComponent,
    TemporaryLoginComponent,
    SettingsComponent,
    UserSettingsComponent,
    RatingSettingsComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    RootRouter,
    HttpClientModule,
    FormsModule,
    NbAccordionModule,
    NbActionsModule,
    NbButtonModule,

    NbCheckboxModule,
    NbRadioModule,
    NbSelectModule,

    NbInputModule,
    NbTabsetModule,
    NbRouteTabsetModule,
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
    RestDataSourceService, AuthGuard, CookieService, AuthenticationService,
    { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
