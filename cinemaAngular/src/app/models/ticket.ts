export interface Ticket{
  idTicket: number,
  idShow?: number,
  idSeat?: number,
  idHall?: number,
  buyerName?: string,
  buyerSurname?: string,
  status?: string,
  price?: number,
  reduced?: number
}
