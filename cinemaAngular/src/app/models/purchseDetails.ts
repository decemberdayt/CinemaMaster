import { Time } from "@angular/common";
import { Seat } from "./seat";
import { Ticket } from "./ticket";

export interface PurchaseDetails{
  idTicket?: number,
  buyerName?: string,
  buyerSurname?: string,
  title?: string,
  hallName?: string,
  showDate?: Date,
  showTime?: Time,
  idSeat?: number,
  hallRow?: number,
  price?: number
}
