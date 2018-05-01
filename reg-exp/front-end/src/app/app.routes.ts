import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from "./components/home/home.component";
import {TaskComponent} from "./components/task/task.component";
import {NewsComponent} from "./components/news/news.component";
import {ContactComponent} from "./components/contact/contact.component";
import {AboutComponent} from "./components/about/about.component";
import {CourseComponent} from "./components/course/course.component";
import {LevelComponent} from "./components/level/level.component";


const routes: Routes = [
  {path: '', component: TaskComponent},
  {path: 'course', component: CourseComponent},
  {path: 'course', component: CourseComponent},
  {path: 'level', component: LevelComponent},

  {path: 'main', component: HomeComponent},
  {path: 'news', component: NewsComponent},
  {path: 'contact', component: ContactComponent},
  {path: 'about', component: AboutComponent},
];

export const RootRouter = RouterModule.forRoot(routes);
