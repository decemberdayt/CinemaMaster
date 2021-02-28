import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FlexLayoutModule } from '@angular/flex-layout';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './login/login.component';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { ActorsComponent } from './actors/actors.component';
import { HttpClientModule } from '@angular/common/http';
import { CinemaService } from './services/cinema.service';
import { MoviesComponent } from './movies/movies.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import { PurchaseFormComponent } from './purchase/purchase-form/purchase-form.component';
import { MoviesListComponent } from './movies/movies-list/movies-list.component';
import { PurchaseComponent } from './purchase/purchase.component';
import {MatSelectModule} from '@angular/material/select';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { TicketService } from './services/ticket.service';
import { MatNativeDateModule } from '@angular/material/core';
import { FormsModule } from '@angular/forms';
import {MatStepperModule} from '@angular/material/stepper';
import { PurchaseSeatsComponent } from './purchase/purchase-seats/purchase-seats.component';
import { DatePipe } from '@angular/common';
import {MatListModule} from '@angular/material/list';
import { ConfirmationComponent } from './purchase/confirmation/confirmation.component';
import { RegisterComponent } from './register/register.component';
import { ProfileComponent } from './profile/profile.component';
import { HomeComponent } from './home/home.component';
import { BoardAdminComponent } from './board-admin/board-admin.component';
import { BoardUserComponent } from './board-user/board-user.component';
import { BoardModeratorComponent } from './board-moderator/board-moderator.component';
import { ReservationsComponent } from './reservations/reservations.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ActorsComponent,
    MoviesComponent,
    PurchaseFormComponent,
    MoviesListComponent,
    PurchaseComponent,
    PurchaseSeatsComponent,
    ConfirmationComponent,
    RegisterComponent,
    ProfileComponent,
    HomeComponent,
    BoardAdminComponent,
    BoardUserComponent,
    BoardModeratorComponent,
    ReservationsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FlexLayoutModule,
    MatButtonModule,
    MatCardModule,
    MatInputModule,
    MatFormFieldModule,
    MatToolbarModule,
    HttpClientModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatDatepickerModule,
    FormsModule,
    MatStepperModule,
    MatListModule
  ],
  providers: [
    CinemaService,
    TicketService,
    DatePipe
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
