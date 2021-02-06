import { Component, Input, OnInit, EventEmitter, Output, OnChanges, SimpleChanges} from '@angular/core';
import { Seat } from 'src/app/models/seat';
import { TicketService } from 'src/app/services/ticket.service';

@Component({
  selector: 'app-purchase-seats',
  templateUrl: './purchase-seats.component.html',
  styleUrls: ['./purchase-seats.component.css']
})
export class PurchaseSeatsComponent implements OnInit, OnChanges {

  @Input()
  seats: Array<Seat>= [];

  @Input()
  rows: Array<number> = [];

  @Output()
  formIsCorrect = new EventEmitter<boolean>();

  // TO DO uzaleznic to od listy biletow
  numberOfTickets: number;
  chosenSeats: number = 0;

  constructor(private buy: TicketService) { }

  ngOnInit(): void {
    this.buy.getNumberOfTickets().subscribe((value)=>{
      this.numberOfTickets = value;
    });
  }


  ngOnChanges(changes: SimpleChanges) : void{
    this.seats = this.seatOrder();
  }


  seatColor(seat: Seat) : string{
    if (seat.selected == true){
      return 'accent'
    }
    else if (seat.ifOccupied == true){
      return 'warn'
    }
    else {
      return 'primary'
    }
  }

  select(seat: Seat){
    if ((seat.ifOccupied != true && this.chosenSeats < this.numberOfTickets) || seat.selected == true){
      seat.selected = !seat.selected;
      if (seat.selected == true){
        this.chosenSeats++;
      }
      else {
        this.chosenSeats--;
      }
    }
  }

  seatOrder(): Array<Seat> {
    return this.seats.sort((a, b) => b.idSeat - a.idSeat);
  }

  seatFilterByRows(rowId: number): Array<Seat>{
    return this.seats.filter((seat: Seat) => seat.hallRow == rowId);
  }

  formIsValid(){
    if(this.numberOfTickets == this.chosenSeats){
      this.formIsCorrect.emit(true);
      this.buy.setSeatsList(this.seats.filter((seat: Seat) => seat.selected == true));
    }
  }

}
