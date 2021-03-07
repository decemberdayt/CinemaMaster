import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { MatCarousel, MatCarouselComponent } from '@ngmodule/material-carousel';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  content?: string;
  public slides = [{'image': 'https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/the-mandalorian-1572282426.jpg'},
  {'image': 'https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/mandalorian-season-3-1608310180.jpg'},
    {'image': 'https://media2.s-nbcnews.com/i/newscms/2020_44/3424474/201030-mandalorian-al-1510_38f6dabe7794e7aac04b7f54ddcd28f4.jpg'},
    {'image': 'https://www.nme.com/wp-content/uploads/2019/12/screen-shot-2019-11-29-at-8.42.07-am.jpg'},
    {'image': 'https://static01.nyt.com/images/2020/12/11/arts/11mandalorian-recap/11mandalorian-recap-superJumbo-v2.jpg'}];

  constructor(private userService: UserService) {

  }

  ngOnInit(): void {
    this.userService.getPublicContent().subscribe(
      data => {
        this.content = data;
      },
      err => {
        this.content = JSON.parse(err.error).message;
      }
    );

  }


}
