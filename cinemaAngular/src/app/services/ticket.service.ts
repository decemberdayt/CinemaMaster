import { Time } from '@angular/common';
import { Injectable } from '@angular/core';
import { tick } from '@angular/core/testing';
import { NumberValueAccessor } from '@angular/forms';
import { Observable, Subject } from 'rxjs';
import { Seat } from '../models/seat';
import { Show } from '../models/show';
import { Ticket } from '../models/ticket';

@Injectable({
  providedIn: 'root'
})
export class TicketService {

  show: Show;
  movieTitle: string;
  buyerName: string;
  buyerSurname: string;
  numberOfTickets: number;
  numberOfTicketsObs = new Subject<number>();
  seats: Array<Seat> = [];
  buyedTickets: Array<Ticket> = [];

  constructor() { }

  buyTicket(show: Show){
    this.show = show;
  }

  setBuyerName(value: string){
    this.buyerName = value;
  }



  setBuyerSurname(value: string){
    this.buyerSurname = value;
  }

  setNumberOfTickets(value: number){
    this.numberOfTickets = value;
    this.numberOfTicketsObs.next(this.numberOfTickets);
  }

  getNumberOfTickets(): Observable<number>{
    return this.numberOfTicketsObs.asObservable();
  }

  setSeatsList(value: Array<Seat>){
    this.seats = value;
  }


  setBuyedTckets(value: Array<Ticket>){
    this.buyedTickets = value;
  }



}
