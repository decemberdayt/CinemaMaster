import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { Show } from '../models/show';
import { Ticket } from '../models/ticket';
import { TicketService } from '../services/ticket.service';
import { CinemaService } from '../services/cinema.service';
import { Seat } from '../models/seat';
import { Router } from '@angular/router';
import { PurchaseDetails } from '../models/purchseDetails';

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
    private buy: TicketService
  ) {}

  ngOnInit(): void {
    if (this.buy.show == null)
    {
      // if show wasn't choosen, redirect to movies view
      this.router.navigateByUrl('movies');
    }
    else
    {
      this.show = this.buy.show;

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
    console.log(this.ticketList);
    this.httpService.postTicket(this.ticketList).subscribe((tickets) => {
      this.buyedTicket = tickets;
    });
  }

  isFirstStepCompleted(event: any){
    this.firstStepCompleted = true;
  }

  isSecondStepCompleted(event: any){
    this.secondStepCompleted = true;
  }

   buyTickets() : void{
 
	 console.log('Buy ticket ' +this.buy.buyerName);
	 this.buyerName = this.buy.buyerName;
	 this.buyerSurename = this.buy.buyerSurname;
	 this.numberOfTicket = this.buy.numberOfTickets;
	 this.seatsList = this.buy.seats;
	 console.log(this.seatsList);
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
