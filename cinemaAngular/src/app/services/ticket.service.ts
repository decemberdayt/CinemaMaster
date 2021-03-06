import { Time } from '@angular/common';
import { Injectable } from '@angular/core';
import { tick } from '@angular/core/testing';
import { NumberValueAccessor } from '@angular/forms';
import { Observable, Subject } from 'rxjs';
import { PurchaseDetails } from '../models/purchseDetails';
import { Seat } from '../models/seat';
import { Show } from '../models/show';
import { Ticket } from '../models/ticket';
import { PurchaseComponent } from '../purchase/purchase.component';

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
  cancelTicketId: number;
  cancelTicketDetails: PurchaseDetails;
  cancelTicketIdObs = new Subject<number>();
  cancelTicketDetailsObs = new Subject<PurchaseDetails>();

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

  setCancelTicketId(value: number){
    this.cancelTicketId = value;
    this.cancelTicketIdObs.next(this.cancelTicketId);
  }

  setCancelTicketDetails(value: PurchaseDetails){
    this.cancelTicketDetails  = value;
    this.cancelTicketDetailsObs.next(this.cancelTicketDetails);
  }

  getCancelTicketId(): Observable<number>{
    return this.cancelTicketIdObs.asObservable();
  }

  getCancelTicketDetails(): Observable<PurchaseDetails>{
    return this.cancelTicketDetailsObs.asObservable();
  }



}
