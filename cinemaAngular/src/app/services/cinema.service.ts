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
import { tick } from '@angular/core/testing';
import { TicketService } from './ticket.service';

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
    return this.http.get<Array<Actor>>('http://localhost:8080/api/actors/');
  }

  getShows(): Observable<Array<Show>>{
    return this.http.get<Array<Show>>('http://localhost:8080/api/movies/allShows');
  }

  getMovies() : Observable<Array<Movie>>{
    return this.http.get<Array<Movie>>('http://localhost:8080/api/movies');
  }

  getSeatsByHall(idHall : number) : Observable<Array<Seat>>{
    const param = new HttpParams().set('idHall', idHall +'');
    return this.http.get<Array<Seat>>('http://localhost:8080/api/seats/idHall', { params: param });
  }

  getSeatsByShow(idShow : number) : Observable<Array<Seat>>{
    var reqHeader = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + this.token.getToken()
    });
    return this.http.get<Array<Seat>>('http://localhost:8080/api/seats/occupancy/' + idShow, {headers: reqHeader});
  }

  postTicket(ticketList: Array<Ticket>) : Observable<Array<Ticket>>{
    var reqHeader = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + this.token.getToken()
    });
    const param = new HttpParams().set('idUser', this.token.getUser().id);
      return this.http.post<Array<Ticket>>('http://localhost:8080/api/tickets/addTickets/confirmed', ticketList, {headers: reqHeader, params: param});
  }

  getPurchaseDetails(idTicket: number): Observable<PurchaseDetails>{
    var reqHeader = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + this.token.getToken()
    });
    const param = new HttpParams().set('idTicket', idTicket +'');
    param.append('idUser', this.token.getUser().id);
    return this.http.get<PurchaseDetails>('http://localhost:8080/api/tickets/getTickets', { headers: reqHeader, params: param});
  }

  getUserTickets(): Observable<Array<PurchaseDetails>>{
    var reqHeader = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + this.token.getToken()
    });
    console.log(reqHeader);
    const param = new HttpParams().set('idUser', this.token.getUser().id);
    return this.http.get<Array<PurchaseDetails>>('http://localhost:8080/api/tickets/all', { headers: reqHeader, params: param});
  }

  cancelTicket(idTicket: number): Observable<Ticket>{
    const reqHeader = {
      headers : new HttpHeaders({
        'Authorization': 'Bearer ' + this.token.getToken(),
        'Content-Type': 'application/json'
      })
    }
    const param = new HttpParams().set('idTicket', idTicket + '');
    var header = reqHeader;
    //console.log(reqHeader.getAll('Authorization'))
    return this.http.post<Ticket>('http://localhost:8080/api/tickets/cancelTicket/confirmed', { headers: reqHeader, params: param});
  }
}


