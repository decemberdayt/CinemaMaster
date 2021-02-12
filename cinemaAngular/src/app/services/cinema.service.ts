import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Actor } from '../actors/actors.component';
import { Show } from '../models/show';
import { Seat } from '../models/seat';
import { Ticket } from '../models/ticket';
import { Movie } from '../models/movie';
import { PurchaseDetails } from '../models/purchseDetails';
import {TokenStorageService} from "./token-storage.service";

@Injectable({
  providedIn: 'root'
})
export class CinemaService {

  currentUser: any;
  idUser: any;

  constructor(private http: HttpClient, private token: TokenStorageService) { }

  ngOnInit(): void {
    this.currentUser = this.token.getUser();
  }

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
    var reqHeader = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + this.token.getToken()
    });
    return this.http.get<Array<Seat>>('http://localhost:8080/seats/occupancy/' + idShow, {headers: reqHeader});
  }

  postTicket(ticketList: Array<Ticket>) : Observable<Array<Ticket>>{
    var reqHeader = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + this.token.getToken()
    });
    const param = new HttpParams().set('idUser', this.token.getUser().id);
      return this.http.post<Array<Ticket>>('http://localhost:8080/tickets/addTickets/confirmed', ticketList, {headers: reqHeader, params: param});
  }

  getPuchaseDetails(idTicket: number): Observable<PurchaseDetails>{
    const param = new HttpParams().set('idTicket', idTicket +'');
    return this.http.get<PurchaseDetails>('http://localhost:8080/tickets/getTickets', { params: param});
  }
}
