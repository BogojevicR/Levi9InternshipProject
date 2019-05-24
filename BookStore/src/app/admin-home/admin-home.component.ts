import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.css']
})
export class AdminHomeComponent implements OnInit {

  cards = [
    { "name": "Catrgory", "url": "category" },
    { "name": "Book", "url" : "adminBook"},
    { "name": "Users", "url" : "users"},   
  ];


  constructor() { }

  ngOnInit() {
  }

}
