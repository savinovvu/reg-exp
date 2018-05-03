import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from "./components/home/home.component";
import {TaskComponent} from "./components/task/task.component";
import {NewsComponent} from "./components/news/news.component";
import {ContactComponent} from "./components/contact/contact.component";
import {AboutComponent} from "./components/about/about.component";
import {CourseComponent} from "./components/course/course.component";
import {LevelComponent} from "./components/level/level.component";
import {LoginComponent} from "./components/login/login.component";


const routes: Routes = [
  {path: 'main', component: HomeComponent},
  {path: 'news', component: NewsComponent},
  {path: 'contact', component: ContactComponent},
  {path: 'about', component: AboutComponent},
  {path: 'login', component: LoginComponent},

  {path: 'course', component: CourseComponent},
  {path: 'course', component: CourseComponent},
  {path: 'course/:level', component: LevelComponent},
  {path: 'course/:level/:task', component: TaskComponent},

  {path: '**', redirectTo: 'main'}
];

export const RootRouter = RouterModule.forRoot(routes);
