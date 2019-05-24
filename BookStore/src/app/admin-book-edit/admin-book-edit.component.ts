import { Component, OnInit, Input } from '@angular/core';
import { BookComponent } from '../book/book.component';
import { BookService } from '../services/book.service';
import { Router, ActivatedRoute } from '@angular/router';
import swal from 'sweetalert2';

@Component({
  selector: 'app-admin-book-edit',
  templateUrl: './admin-book-edit.component.html',
  styleUrls: ['./admin-book-edit.component.css']
})
export class AdminBookEditComponent implements OnInit {
  
  @Input() book: any;

 //disabledBott : boolean;

  constructor(private bookService: BookService, private router: Router, private activeRoute: ActivatedRoute,) { }

  ngOnInit() {
  }

  disableBook(bookId){
    console.log(bookId);
   // this.disabledBott = true;
    this.bookService.disableBook(bookId).subscribe();
    swal.fire('Book is desabled!');
    window.location.reload();
  }

  activateBook(bookId){
    console.log(bookId);
   // this.disabledBott = true;
    this.bookService.activateBook(bookId).subscribe();
    swal.fire('Book is activated!');
    window.location.reload();
  }

  navigateToUpdate(bookId){
    this.router.navigate(['adminBook/update/' + bookId]);
  }

  

 

}
