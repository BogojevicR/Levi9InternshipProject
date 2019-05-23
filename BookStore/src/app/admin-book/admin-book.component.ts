import { Component, OnInit, Input } from '@angular/core';
import { BookService } from '../services/book.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-admin-book',
  templateUrl: './admin-book.component.html',
  styleUrls: ['./admin-book.component.css']
})
export class AdminBookComponent implements OnInit {

  
  constructor(private bookService : BookService, private router: Router, private activeRoute: ActivatedRoute,) { }

  books: any = [];
  
  ngOnInit() {

    this.bookService.getActiveBooks().subscribe(data => {
      this.books = data;
    });
  }

  addBook(){
      this.router.navigate(['adminBook/add'])
  }

}
