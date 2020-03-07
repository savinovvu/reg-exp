import { RouterModule, Routes } from '@angular/router';
import { CourseComponent } from "./pages/course/course.component";
import { ProposeTaskComponent } from "./pages/propose-task/propose-task.component";
import { UsefulLinksComponent } from "./pages/useful-links/useful-links.component";
import { UserRatingComponent } from "./pages/rating/user-rating.component";
import { LevelComponent } from "./pages/level/level.component";
import { ChallengeComponent } from "./pages/challenge/challenge.component";
import { TemporaryLoginComponent } from "./pages/temporary-login/temporary-login.component";
import { SettingsComponent } from "./pages/settings/settings.component";



const routes: Routes = [

  { path: 'users', component: UserRatingComponent },
  { path: 'course', component: CourseComponent },
  { path: 'useful-links', component: UsefulLinksComponent },
  { path: 'propose-task', component: ProposeTaskComponent },
  { path: 'course/:levelNumber', component: LevelComponent },
  { path: 'course/:levelNumber/:taskNumber', component: ChallengeComponent },
  { path: 'temporary-login', component: TemporaryLoginComponent },
  { path: 'settings', component: SettingsComponent },
  /*  { path: 'main', component: HomeComponent },
    { path: 'news', component: NewsComponent },
    { path: 'contact', component: ContactComponent },
    { path: 'about', component: AboutComponent },
    { path: 'course', component: CourseComponent, canActivate: [ AuthGuard ] },

    { path: 'error', component: ErrorComponent },*/


  { path: '**', redirectTo: 'main' }
];

export const RootRouter = RouterModule.forRoot(routes);
