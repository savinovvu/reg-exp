import { RouterModule, Routes } from '@angular/router';
import { CourseComponent } from "./pages/course/course.component";
import { ProposeTaskComponent } from "./pages/propose-task/propose-task.component";
import { UsefulLinksComponent } from "./pages/useful-links/useful-links.component";
import { UserTableComponent } from "./pages/users/user-table.component";



const routes: Routes = [

  { path: 'users', component: UserTableComponent },
  { path: 'course', component: CourseComponent },
  { path: 'useful-links', component: UsefulLinksComponent },
  { path: 'propose-task', component: ProposeTaskComponent },
  /*  { path: 'main', component: HomeComponent },
    { path: 'news', component: NewsComponent },
    { path: 'contact', component: ContactComponent },
    { path: 'about', component: AboutComponent },
    { path: 'addTask', component: AddTaskComponent, canActivate: [ AuthGuard ] },
    { path: 'usefulLinks', component: UsefulLinksComponent },

    { path: 'course', component: CourseComponent, canActivate: [ AuthGuard ] },
    { path: 'course/:levelNumber', component: LevelComponent, canActivate: [ AuthGuard ] },
    { path: 'course/:levelNumber/:taskNumber', component: TaskComponent, canActivate: [ AuthGuard ] },

    { path: 'error', component: ErrorComponent },*/


  { path: '**', redirectTo: 'main' }
];

export const RootRouter = RouterModule.forRoot(routes);
