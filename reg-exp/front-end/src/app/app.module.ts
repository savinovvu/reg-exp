import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { MaterialModule } from "./material/material.module";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { NavigationComponent } from './components/navigation/navigation.component';
import { RootRouter } from "./app.routes";
import { HomeComponent } from './components/home/home.component';
import { NewsComponent } from './components/news/news.component';
import { ContactComponent } from './components/contact/contact.component';
import { AboutComponent } from './components/about/about.component';
import { TaskComponent } from './components/task/task.component';
import { CourseComponent } from './components/course/course.component';
import { LevelComponent } from './components/level/level.component';
import { RestDataSourceService } from "./services/rest/rest-data-source.service";
import { HttpClientModule } from "@angular/common/http";
import { FormsModule } from "@angular/forms";
import { LoginComponent } from './components/login/login.component';
import { AddTaskComponent } from './components/add-task/add-task.component';
import { CheckWrongStatusPipe } from './pipes/check-status/check-wrong-status.pipe';
import { UsefulLinksComponent } from './components/useful-links/useful-links.component';
import { JoinPipe } from './pipes/join/join.pipe';
import { RegexpCheckResultComponent } from './components/regexp-check-result/regexp-check-result.component';
import { ErrorComponent } from './components/error/error.component';
import { ErrorService } from "./components/error/errorService/error.service";



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
