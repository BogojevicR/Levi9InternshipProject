import { ShoppingCartService } from './../services/shopping-cart.service';
import { Subscriber } from 'rxjs';
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})

export class BookComponent implements OnInit {

  @Input() book: any;
  constructor(private cartService: ShoppingCartService) { }

  ngOnInit() {
  }

  addItem(quantity: number, bookId: any){
    this.cartService.addItem(quantity,bookId).subscribe(data => {
    })
  }

}
