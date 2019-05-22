import { Component, OnInit } from '@angular/core';
import { Subscriber } from 'rxjs';
import {BookService} from '../book.service'


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  books = {}

  constructor(private BookService: BookService) { }

  ngOnInit() {
    this.BookService.getAllBooks().subscribe(data => {
      this.books = data;
    });
  }

}
