import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from "./components/projectInfo/home/home.component";
import { TaskComponent } from "./components/courseMain/task/task.component";
import { NewsComponent } from "./components/projectInfo/news/news.component";
import { ContactComponent } from "./components/projectInfo/contact/contact.component";
import { AboutComponent } from "./components/projectInfo/about/about.component";
import { CourseComponent } from "./components/courseMain/course/course.component";
import { LevelComponent } from "./components/courseMain/level/level.component";
import { LoginComponent } from "./components/users/login/login.component";
import { AddTaskComponent } from "./components/courseMain/add-task/add-task.component";
import { UsefulLinksComponent } from "./components/courseMain/useful-links/useful-links.component";
import { ErrorComponent } from "./components/common/error/error.component";
import { AddUserComponent } from "./components/users/add-user/add-user.component";
import { AuthGuard } from "./services/guards/auth.guard";



const routes: Routes = [
  { path: 'main', component: HomeComponent },
  { path: 'news', component: NewsComponent },
  { path: 'contact', component: ContactComponent },
  { path: 'about', component: AboutComponent },
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: AddUserComponent },
  { path: 'addTask', component: AddTaskComponent, canActivate: [ AuthGuard ] },
  { path: 'usefulLinks', component: UsefulLinksComponent },

  { path: 'course', component: CourseComponent, canActivate: [ AuthGuard ] },
  { path: 'course/:levelNumber', component: LevelComponent, canActivate: [ AuthGuard ] },
  { path: 'course/:levelNumber/:taskNumber', component: TaskComponent, canActivate: [ AuthGuard ] },

  { path: 'error', component: ErrorComponent },


  { path: '**', redirectTo: 'main' }
];

export const RootRouter = RouterModule.forRoot(routes);
