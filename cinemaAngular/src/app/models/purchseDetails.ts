import { Time } from "@angular/common";
import { Seat } from "./seat";
import { Ticket } from "./ticket";

export interface PurchaseDetails{
  idTicket: number,
  buyerName: string,
  buyerSurname: string,
  price: number,
  reduced: number,
  showDate: Date,
  showTime: Time,
  title: string,
  subtitle: string,
  durationMin: number,
  hallName: string,
  idSeat: number,
  hallRow: number,
  status: string
}
