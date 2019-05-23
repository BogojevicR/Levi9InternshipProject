import { Component, OnInit, Input } from '@angular/core';
import { BookComponent } from '../book/book.component';

@Component({
  selector: 'app-admin-book-edit',
  templateUrl: './admin-book-edit.component.html',
  styleUrls: ['./admin-book-edit.component.css']
})
export class AdminBookEditComponent implements OnInit {
  
  @Input() book: any;
  constructor() { }

  ngOnInit() {
  }

}
