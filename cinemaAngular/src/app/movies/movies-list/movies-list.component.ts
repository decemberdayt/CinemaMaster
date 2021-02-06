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

  constructor() {
  }

  ngOnInit(): void {
  }

  @Input()
  movies: Array<Movie> = [];

  @Input()
  shows: Array<Show> = [];

  @Input()
  showDate: Date;

  select(show : Show){
    this.eventBuy.emit(show);
  }

}

