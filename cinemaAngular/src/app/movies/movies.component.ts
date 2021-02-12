import { DatePipe, Time } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Show } from '../models/show';
import { Movie } from '../models/movie'
import { CinemaService } from '../services/cinema.service';
import { TicketService } from '../services/ticket.service';
import { Router } from '@angular/router';
import {TokenStorageService} from "../services/token-storage.service";

@Component({
  selector: 'app-movies',
  templateUrl: './movies.component.html',
  styleUrls: ['./movies.component.css']
})
export class MoviesComponent implements OnInit {

  moviesList : Array<Movie> = [];
  currentUser: any;

  constructor(private httpService: CinemaService, private buy: TicketService, private router: Router, public datepipe: DatePipe, private token: TokenStorageService) { }

  ngOnInit(): void {
    this.getMovies();
    this.getShows();
    this.pickedDate = new Date();
    this.pickedDate = this.datepipe.transform(this.pickedDate, 'yyyy-MM-dd') as unknown as Date;
    this.currentUser = this.token.getUser();
  }

  showList: Array<Show> = [];
  pickedDate: Date;

  getShows(){
    this.httpService.getShows().subscribe(shows =>{
      this.showList = shows;
    })
  }

  getMovies(){
    this.httpService.getMovies().subscribe(movies =>{
      this.moviesList = movies;
    })
  }

  selected(show : Show) : void{
    this.buy.buyTicket(show);
    this.router.navigateByUrl('buy')
  }

  dateChange(event: any){
    const data = event;
    this.pickedDate = this.datepipe.transform(data, 'yyyy-MM-dd') as unknown as Date;
  }

}
