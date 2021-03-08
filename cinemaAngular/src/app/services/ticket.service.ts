import { Time } from '@angular/common';
import { Injectable } from '@angular/core';
import { tick } from '@angular/core/testing';
import { NumberValueAccessor } from '@angular/forms';
import { BehaviorSubject, Observable, Subject } from 'rxjs';
import { PurchaseDetails } from '../models/purchseDetails';
import { Seat } from '../models/seat';
import { Show } from '../models/show';
import { Ticket } from '../models/ticket';
import { PurchaseComponent } from '../purchase/purchase.component';

@Injectable({
  providedIn: 'root'
})
export class TicketService {

  show = new BehaviorSubject<any>(undefined);
  movieTitle: string;
  buyerName = new BehaviorSubject<string>("");
  buyerSurname = new BehaviorSubject<string>("");
  numberOfTickets = new BehaviorSubject<number>(0);
  seats = new BehaviorSubject<any[]>([]);
  cancelTicketId = new BehaviorSubject<number>(0);
  cancelTicketDetails = new BehaviorSubject<any>(undefined);

  constructor() { }

  buyTicket(value: Show){
    this.show.next(value);
  }

  getShow(): Observable<any>{
    return this.show.asObservable();
  }

  setBuyerName(value: string){
    this.buyerName.next(value);
  }

  getBuyerName(): Observable<string> {
    return this.buyerName.asObservable();
  }

  setBuyerSurname(value: string){
    this.buyerSurname.next(value);
  }

  getBuyerSurname(): Observable<string>{
    return this.buyerSurname.asObservable();
  }

  setNumberOfTickets(value: number){
    this.numberOfTickets.next(value);
  }

  getNumberOfTickets(): Observable<number>{
    return this.numberOfTickets.asObservable();
  }

  setSeatsList(value: Array<Seat>){
    this.seats.next(value);
  }

  getSeatList(): Observable<Array<Seat>>{
    return this.seats.asObservable();
  }

  setCancelTicketId(value: number){
    this.cancelTicketId.next(value);
  }

  setCancelTicketDetails(value: PurchaseDetails){
    this.cancelTicketDetails.next(value);
  }

  getCancelTicketId(): Observable<number>{
    return this.cancelTicketId.asObservable();
  }

  getCancelTicketDetails(): Observable<PurchaseDetails>{
    return this.cancelTicketDetails.asObservable();
  }



}
