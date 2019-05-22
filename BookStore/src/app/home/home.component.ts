import { CategoryServiceService } from './../services/category-service.service';
import { Component, OnInit } from '@angular/core';
import { Subscriber } from 'rxjs';
import {BookService} from '../services/book.service'

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  searchValue: any
  books = {}
  categories = {}
  constructor(private bookService: BookService ,private categoryService: CategoryServiceService) { }

  ngOnInit() {
    this.bookService.getAllBooks().subscribe(data => {
      this.books = data;
    });
    this.categoryService.getAllCategories().subscribe(data => {
        this.categories = data;
    });
  }

  sort(searchValue : any){
    this.bookService.sort(searchValue).subscribe(data => {
      this.books = data;
    });
  }

  reciveBooks($event){
    this.books = $event;
  }

}
