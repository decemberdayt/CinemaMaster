import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { PurchaseDetails } from 'src/app/models/purchseDetails';
import { TicketService } from 'src/app/services/ticket.service';
import {TokenStorageService} from "../../services/token-storage.service";

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
  currentUser: any;

  constructor(private buy: TicketService, private token: TokenStorageService) { }

  ngOnInit(): void {
    this.currentUser = this.token.getUser();
  }

  formIsValid() {
    let buyerNameValid: boolean = false;
    let buyerSurnameValid: boolean = false;
    let ticketNumberValid: boolean = false;

    if(this.buyerName != null && this.buyerName.length >= 1){
      buyerNameValid = true;
    }
    if(this.buyerSurname != null && this.buyerSurname.length >= 1){
      buyerSurnameValid = true;
    }
    if(!isNaN(this.ticketsNumber) && this.ticketsNumber >= 1){
      ticketNumberValid = true;
    }
    if(buyerNameValid && buyerSurnameValid && ticketNumberValid){
      this.formIsCorrect.emit(true);
      this.buy.setBuyerName(this.buyerName);
      this.buy.setBuyerSurname(this.buyerSurname);
      this.buy.setNumberOfTickets(this.ticketsNumber);
    }
    else this.formIsCorrect.emit(false);
  }
}
