import { Component, OnInit } from '@angular/core';
import { PurchaseDetails } from '../models/purchseDetails';
import { Ticket } from '../models/ticket';
import { PurchaseComponent } from '../purchase/purchase.component';
import { CinemaService } from '../services/cinema.service';

@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrls: ['./reservations.component.css']
})
export class ReservationsComponent implements OnInit {


  ticketsList: Array<PurchaseDetails> = []
  canceledTicket: Ticket;

  constructor(private httpService: CinemaService) { }

  ngOnInit(): void {
    this.getAllUserTickets();
  }

  getAllUserTickets(){
    this.httpService.getUserTickets().subscribe((tickets) => {
      this.ticketsList = tickets;
    });
  }

  cancelTicket(idTicket : number){
    this.httpService.cancelTicket(idTicket).subscribe((cancelTicket) =>{
      this.canceledTicket = cancelTicket;
    });
  }

}
