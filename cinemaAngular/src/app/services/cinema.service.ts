import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Actor } from '../actors/actors.component';
import { Show } from '../models/show';
import { Seat } from '../models/seat';
import { Ticket } from '../models/ticket';
import { Movie } from '../models/movie';
import { PurchaseDetails } from '../models/purchseDetails';

@Injectable({
  providedIn: 'root'
})
export class CinemaService {

  constructor(private http: HttpClient) { }

  getActors(): Observable<Array<Actor>>{
    return this.http.get<Array<Actor>>('http://localhost:8080/actors/');
  }

  getShows(): Observable<Array<Show>>{
    return this.http.get<Array<Show>>('http://localhost:8080/movies/allShows');
  }

  getMovies() : Observable<Array<Movie>>{
    return this.http.get<Array<Movie>>('http://localhost:8080/movies');
  }

  getSeatsByHall(idHall : number) : Observable<Array<Seat>>{
    const param = new HttpParams().set('idHall', idHall +'');
    return this.http.get<Array<Seat>>('http://localhost:8080/seats/idHall', { params: param });
  }

  getSeatsByShow(idShow : number) : Observable<Array<Seat>>{
    return this.http.get<Array<Seat>>('http://localhost:8080/seats/occupancy/' + idShow);
  }

  postTicket(ticketList: Array<Ticket>) : Observable<Array<Ticket>>{
      return this.http.post<Array<Ticket>>('http://localhost:8080/tickets/addTickets/confirmed', ticketList);
  }

  getPuchaseDetails(idTicket: number): Observable<PurchaseDetails>{
    const param = new HttpParams().set('idTicket', idTicket +'');
    return this.http.get<PurchaseDetails>('http://localhost:8080/tickets/getTickets', { params: param});
  }
}
