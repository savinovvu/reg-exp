import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { MaterialModule } from "./material/material.module";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { NavigationComponent } from './components/common/navigation/navigation.component';
import { RootRouter } from "./app.routes";
import { HomeComponent } from './components/projectInfo/home/home.component';
import { NewsComponent } from './components/projectInfo/news/news.component';
import { ContactComponent } from './components/projectInfo/contact/contact.component';
import { AboutComponent } from './components/projectInfo/about/about.component';
import { TaskComponent } from './components/courseMain/task/task.component';
import { CourseComponent } from './components/courseMain/course/course.component';
import { LevelComponent } from './components/courseMain/level/level.component';
import { RestDataSourceService } from "./services/rest/rest-data-source.service";
import { HttpClientModule } from "@angular/common/http";
import { FormsModule } from "@angular/forms";
import { LoginComponent } from './components/users/login/login.component';
import { AddTaskComponent } from './components/courseMain/add-task/add-task.component';
import { CheckWrongStatusPipe } from './pipes/check-status/check-wrong-status.pipe';
import { UsefulLinksComponent } from './components/courseMain/useful-links/useful-links.component';
import { JoinPipe } from './pipes/join/join.pipe';
import { RegexpCheckResultComponent } from './components/courseMain/regexp-check-result/regexp-check-result.component';
import { ErrorComponent } from './components/common/error/error.component';
import { ErrorService } from "./components/common/error/errorService/error.service";
import { AddUserComponent } from './components/users/add-user/add-user.component';



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
  ],
  imports: [
    BrowserModule,
    MaterialModule,
    BrowserAnimationsModule,
    RootRouter,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    RestDataSourceService, ErrorService
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule {
}
