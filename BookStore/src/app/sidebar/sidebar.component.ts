import { BookService } from './../services/book.service';
import { Component, OnInit, Input, Output, EventEmitter} from '@angular/core';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})

export class SidebarComponent implements OnInit {
  @Output() booksEvent = new EventEmitter<any>();
  books = []
  categories = []
  constructor(private bookService: BookService ) { }
    
  ngOnInit() {
    this.bookService.getAllCategories().subscribe(data => {
      this.categories = data;
    });
  }
  
  getBookByCategory(id:any){
    this.bookService.getByCategory(id).subscribe(data => {
      this.books = data;
      this.booksEvent.emit(data);
    });
  }

}
