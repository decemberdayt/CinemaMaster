import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { Show } from '../models/show';
import { Ticket } from '../models/ticket';
import { TicketService } from '../services/ticket.service';
import { CinemaService } from '../services/cinema.service';
import { Seat } from '../models/seat';
import { Router } from '@angular/router';
import { PurchaseDetails } from '../models/purchseDetails';
import {TokenStorageService} from "../services/token-storage.service";

@Component({
  selector: 'app-purchase',
  templateUrl: './purchase.component.html',
  styleUrls: ['./purchase.component.css'],
})
export class PurchaseComponent implements OnInit {

  show: Show;
  ticketList: Array<Ticket> = [];
  buyedTicket: Array<Ticket> = [];
  seatsList: Array<Seat> = [];
  rows: Array<number> = [];
  firstStepCompleted = false;
  secondStepCompleted = false;
  numberOfTicket: number;
  buyerName: string;
  buyerSurename: string;

  constructor(
    private httpService: CinemaService,
    private router: Router,
    private buy: TicketService,
  ) {}

  ngOnInit(): void {
    var currentShow: Show | undefined = undefined;

    this.buy.getShow().subscribe((buyShow) => {
      currentShow = buyShow;
    })

    if (currentShow == null || currentShow == undefined)
    {
      // if show wasn't choosen, redirect to movies view
      this.router.navigateByUrl('movies');
    }
    else
    {
      this.show = currentShow;

      this.getSeatsByShow(this.show.idShow);

      for (var seat of this.seatsList) {
        seat.selected = false;
      }

      if (
        this.show.numberOfRows != undefined
      ) {
        for(var i= 1; i<= this.show.numberOfRows; i++ ){
          this.rows.push(i);
        }
      }
    }
  }


  getSeatsByShow(showId: number) {
    this.httpService.getSeatsByShow(showId).subscribe((seats) => {
      this.seatsList = seats;
    });
  }

  addTicket() {
    this.httpService.postTicket(this.ticketList).subscribe((tickets) => {
      this.buyedTicket = tickets;
    });
  }

  isFirstStepCompleted(event: any){
    if(event == true){
      this.firstStepCompleted = true;
    }
    else this.firstStepCompleted = false;
  }

  isSecondStepCompleted(event: any){
    if(event == true){
      this.secondStepCompleted = true;
    }
    else this.secondStepCompleted = false;
  }

  getTicketValues(){
    this.buy.getBuyerName().subscribe((name) => {
      this.buyerName = name;
    })
    this.buy.getBuyerSurname().subscribe((surname) => {
      this.buyerSurename = surname;
    })
    this.buy.getNumberOfTickets().subscribe((number) => {
      this.numberOfTicket = number;
    })
    this.buy.getSeatList().subscribe((seats) => {
      this.seatsList = seats;
    })
  }

   buyTickets() : void{
   this.getTicketValues();
	 for(let seat of this.seatsList){
    let ticket: Ticket = {
    idTicket: 1,
    idShow: this.show.idShow,
    idHall: this.show.idHall,
    idSeat: seat.idSeat,
    buyerName: this.buyerName,
    buyerSurname: this.buyerSurename,
    status: 'reserved',
    price: 20,
    reduced: 0
    };
    this.ticketList.push(ticket);
	 }
	 this.addTicket();
 }

   backToMOvies(){
    this.router.navigateByUrl('movies');
   }
}
