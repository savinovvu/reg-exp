import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NavigationComponent } from './components/common/navigation/navigation.component';
import { RootRouter } from './app.routes';
import { HomeComponent } from './components/projectInfo/home/home.component';
import { NewsComponent } from './components/projectInfo/news/news.component';
import { ContactComponent } from './components/projectInfo/contact/contact.component';
import { AboutComponent } from './components/projectInfo/about/about.component';
import { TaskComponent } from './components/courseMain/task/task.component';
import { CourseComponent } from './components/courseMain/course/course.component';
import { LevelComponent } from './components/courseMain/level/level.component';
import { RestDataSourceService } from './services/rest/rest-data-source.service';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { AddTaskComponent } from './components/courseMain/add-task/add-task.component';
import { CheckWrongStatusPipe } from './pipes/check-status/check-wrong-status.pipe';
import { UsefulLinksComponent } from './components/courseMain/useful-links/useful-links.component';
import { JoinPipe } from './pipes/join/join.pipe';
import { RegexpCheckResultComponent } from './components/courseMain/regexp-check-result/regexp-check-result.component';
import { ErrorComponent } from './components/common/error/error.component';
import { ErrorService } from './components/common/error/errorService/error.service';
import { AppComponent } from './appComponent/app.component';
import { AuthGuard } from './services/guards/auth.guard';
import { UserService } from './services/security/user.service';
import { TranslateModule, TranslateLoader } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { FooterComponent } from './components/common/footer/footer.component';
import { TranslateComponent } from './components/library/translate/translate.component';
import { DickTdComponent } from './components/library/tables/dick-td/dick-td.component';
import { DickThComponent } from './components/library/tables/dick-th/dick-th.component';
import { CookieService } from 'ngx-cookie-service';


export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http);
}



@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    HomeComponent,
    NewsComponent,
    ContactComponent,
    AboutComponent,
    TaskComponent,
    CourseComponent,
    LevelComponent,
    AddTaskComponent,
    CheckWrongStatusPipe,
    UsefulLinksComponent,
    JoinPipe,
    RegexpCheckResultComponent,
    ErrorComponent,
    FooterComponent,
    TranslateComponent,
    DickTdComponent,
    DickThComponent,
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
          deps: [ HttpClient ]
        }
      })
    )
  ],
  providers: [
    RestDataSourceService, ErrorService, AuthGuard, UserService, CookieService
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule {
}
