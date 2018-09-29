import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { MaterialModule } from './material/material.module';
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
import { LoginComponent } from './components/users/login/login.component';
import { AddTaskComponent } from './components/courseMain/add-task/add-task.component';
import { CheckWrongStatusPipe } from './pipes/check-status/check-wrong-status.pipe';
import { UsefulLinksComponent } from './components/courseMain/useful-links/useful-links.component';
import { JoinPipe } from './pipes/join/join.pipe';
import { RegexpCheckResultComponent } from './components/courseMain/regexp-check-result/regexp-check-result.component';
import { ErrorComponent } from './components/common/error/error.component';
import { ErrorService } from './components/common/error/errorService/error.service';
import { AddUserComponent } from './components/users/add-user/add-user.component';
import { AppComponent } from './appComponent/app.component';
import { AuthGuard } from './services/guards/auth.guard';
import { UserService } from './services/security/user.service';
import { FlexLayoutModule } from '@angular/flex-layout';
import { TranslateModule, TranslateLoader } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { FooterComponent } from './components/common/footer/footer.component';
import { TranslateComponent } from './components/library/translate/translate.component';



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
    LoginComponent,
    AddTaskComponent,
    CheckWrongStatusPipe,
    UsefulLinksComponent,
    JoinPipe,
    RegexpCheckResultComponent,
    ErrorComponent,
    AddUserComponent,
    FooterComponent,
    TranslateComponent,
  ],
  imports: [
    BrowserModule,
    MaterialModule,
    BrowserAnimationsModule,
    RootRouter,
    HttpClientModule,
    FormsModule,
    FlexLayoutModule,
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
    RestDataSourceService, ErrorService, AuthGuard, UserService
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule {
}
