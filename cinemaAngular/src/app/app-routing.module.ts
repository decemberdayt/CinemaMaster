import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ActorsComponent } from './actors/actors.component';
import { LoginComponent } from './login/login.component';
import { MoviesComponent } from './movies/movies.component';
import { PurchaseComponent } from './purchase/purchase.component';
import {HomeComponent} from "./home/home.component";
import {RegisterComponent} from "./register/register.component";
import {BoardAdminComponent} from "./board-admin/board-admin.component";
import {ProfileComponent} from "./profile/profile.component";
import {BoardUserComponent} from "./board-user/board-user.component";
import {BoardModeratorComponent} from "./board-moderator/board-moderator.component";

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  {path: 'actors', component: ActorsComponent},
  {path:'movies', component: MoviesComponent},
  {path: 'buy', component: PurchaseComponent},
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'user', component: BoardUserComponent },
  { path: 'mod', component: BoardModeratorComponent },
  { path: 'admin', component: BoardAdminComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
