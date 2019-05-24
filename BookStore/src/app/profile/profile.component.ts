import { UserService } from './../services/user.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  user = []
  constructor(private userService : UserService) { }

  ngOnInit() {
    this.getUser(37)
  }

  getUser(id : any){
    this.userService.getUser(id).subscribe( data =>{
      console.log(data)
      this.user = data;
    })
  }

}
