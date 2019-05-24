import { UserService } from './../services/user.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin-users',
  templateUrl: './admin-users.component.html',
  styleUrls: ['./admin-users.component.css']
})
export class AdminUsersComponent implements OnInit {

  constructor(private userService: UserService) { }
  users = []
  ngOnInit() {
    this.getAllUsers();
  }

  getAllUsers(){
    this.userService.getAllUsers().subscribe( data =>{
      console.log(data)
      this.users = data;


    })
  }
}
