import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { PurchaseDetails } from 'src/app/models/purchseDetails';
import { TicketService } from 'src/app/services/ticket.service';

@Component({
  selector: 'app-purchase-form',
  templateUrl: './purchase-form.component.html',
  styleUrls: ['./purchase-form.component.css']
})
export class PurchaseFormComponent implements OnInit {

  @Output()
  formIsCorrect = new EventEmitter<boolean>();

  buyerName: string;
  buyerSurname: string;
  ticketsNumber: number;

  constructor(private buy: TicketService) { }

  ngOnInit(): void {
  }

  formIsValid() {
    console.log('formIsValid');
    if(this.buyerName!= null && this.buyerSurname!= null && this.ticketsNumber!= null){
      this.formIsCorrect.emit(true);
      this.buy.setBuyerName(this.buyerName);
      this.buy.setBuyerSurname(this.buyerSurname);
      this.buy.setNumberOfTickets(this.ticketsNumber);
    }
  }
}
