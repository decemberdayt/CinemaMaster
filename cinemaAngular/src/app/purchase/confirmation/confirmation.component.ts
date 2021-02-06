import { Component, Input, OnInit } from '@angular/core';
import { Show } from 'src/app/models/show';
import { Ticket } from 'src/app/models/ticket';

@Component({
  selector: 'app-confirmation',
  templateUrl: './confirmation.component.html',
  styleUrls: ['./confirmation.component.css']
})
export class ConfirmationComponent implements OnInit {

  @Input()
  buyedTickets: Array<Ticket> = [];

  @Input()
  show: Show;

  constructor() { }

  ngOnInit(): void {
  }

}
