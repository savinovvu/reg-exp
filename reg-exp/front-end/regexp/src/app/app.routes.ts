import { RouterModule, Routes } from '@angular/router';



const routes: Routes = [
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
