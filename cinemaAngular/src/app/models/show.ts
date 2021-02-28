import { Time } from "@angular/common";

export interface Show{
  idShow: number,
  idMovie: number,
  idHall: number,
  title: string,
  showDate : Date,
  showTime : Time,
  numberOfSeats?: number,
  numberOfRows?: number
}
