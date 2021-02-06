import { Component, Injectable, OnInit } from '@angular/core';
import { CinemaService } from '../services/cinema.service';


@Component({
  selector: 'app-actors',
  templateUrl: './actors.component.html',
  styleUrls: ['./actors.component.css']
})


export class ActorsComponent implements OnInit {

  constructor(private httpService: CinemaService) { }

  ngOnInit(): void {
    this.getActors();
  }

  actorsList: Array<Actor> = [];

  getActors(){
    this.httpService.getActors().subscribe(actors =>{
      this.actorsList = actors;
    })
  }

}

export interface Actor{
  idActor?: number,
  name?: string,
  surname?: string,
  country?: string
}
