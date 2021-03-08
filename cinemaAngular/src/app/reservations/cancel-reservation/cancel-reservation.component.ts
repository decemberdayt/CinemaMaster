import { Component, Input, OnInit } from '@angular/core';
import { tick } from '@angular/core/testing';
import { Router } from '@angular/router';
import { PurchaseDetails } from 'src/app/models/purchseDetails';
import { Ticket } from 'src/app/models/ticket';
import { PurchaseComponent } from 'src/app/purchase/purchase.component';
import { CinemaService } from 'src/app/services/cinema.service';
import { TicketService } from 'src/app/services/ticket.service';

@Component({
  selector: 'app-cancel-reservation',
  templateUrl: './cancel-reservation.component.html',
  styleUrls: ['./cancel-reservation.component.css']
})
export class CancelReservationComponent implements OnInit {

  ticketId: number;
  reservationDetails: PurchaseDetails;
  canceledTicket: Ticket;

  constructor(private httpService: CinemaService, private router: Router, private cancel: TicketService) { }

  ngOnInit(): void {
    this.cancel.getCancelTicketId().subscribe((ticket) => {
      this.ticketId = ticket;
    })
    this.cancel.getCancelTicketDetails().subscribe((details) => {
      this.reservationDetails = details;
    })

    if (this.ticketId == 0 || this.ticketId == undefined)
      this.backToReservations();
  }

  backToReservations(){
      this.router.navigateByUrl('reservations');
  }

  cancelReservation(){
    this.httpService.cancelTicket(this.ticketId).subscribe((cancelTicket) =>{
      this.canceledTicket = cancelTicket;
    });
    this.router.navigateByUrl('movies');
  }


}
