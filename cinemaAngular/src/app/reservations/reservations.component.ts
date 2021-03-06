import { Component, OnInit } from '@angular/core';
import { tick } from '@angular/core/testing';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { PurchaseDetails } from '../models/purchseDetails';
import { Ticket } from '../models/ticket';
import { PurchaseComponent } from '../purchase/purchase.component';
import { CinemaService } from '../services/cinema.service';
import { TicketService } from '../services/ticket.service';

@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrls: ['./reservations.component.css']
})
export class ReservationsComponent implements OnInit {


  ticketsList: Array<PurchaseDetails> = [];
  canceledTicket: Array<PurchaseDetails> = [];
  canceledTicketId: number;

  constructor(private httpService: CinemaService,private router: Router, private cancel: TicketService) { }

  ngOnInit(): void {
    this.getAllUserTickets();
  }

  getAllUserTickets(){
    this.httpService.getUserTickets().subscribe((tickets) => {
      this.ticketsList = tickets;
    });
  }

  cancelTicket(idTicket : number){
    this.canceledTicket = this.ticketsList.filter((ticket: PurchaseDetails) => ticket.idTicket = idTicket);
    this.cancel.setCancelTicketId(idTicket);
    this.cancel.setCancelTicketDetails(this.canceledTicket[0]);
    this.router.navigateByUrl('cancel');
  }


}

