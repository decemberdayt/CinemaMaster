import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { Movie } from '../../models/movie';
import { Show } from '../../models/show';

@Component({
  selector: 'app-movies-list',
  templateUrl: './movies-list.component.html',
  styleUrls: ['./movies-list.component.css']
})
export class MoviesListComponent implements OnInit {

  @Output()
  eventBuy = new EventEmitter<Show>();

  @Input()
  isLoggedIn: boolean;

  tryToBuy = false;

  constructor() {
  }

  ngOnInit(): void {
  }

  @Input()
  movies: Array<Movie> = [];

  @Input()
  shows: Array<Show> = [];


  @Input()
  isDatePicked: boolean

  @Input()
  actualDate: Date

  @Input()
  showDate: Date;

  select(show : Show){
    if(this.isLoggedIn){
      this.eventBuy.emit(show);
    }
    this.tryToBuy = true;
  }

  hideMovie(movieId : number) : boolean {
      let showsPerMovie = 0
      let futhureShowsPerMovie = 0
      for (var show of this.shows){
        if (show.idMovie == movieId && show.showDate == this.showDate)
        showsPerMovie =+ 1
      }

      for (var show of this.shows){
        if (show.idMovie == movieId && show.showDate >= this.actualDate)
        futhureShowsPerMovie =+ 1
      }

      if (futhureShowsPerMovie > 0 && (showsPerMovie > 0 || !this.isDatePicked))
        return false
      else return true;
  }

}

