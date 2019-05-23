import { ShoppingCartService } from './../services/shopping-cart.service';
import { Component, OnInit, Input } from '@angular/core';
import swal from 'sweetalert2';

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
      if (data){
        swal.fire({
          title: 'Added item to Cart!',
          type: 'success'
        })
      } else{
        swal.fire({
          title: 'Item allready in Cart!',
          type: 'error'
        })
      }
      
    })
      
    
  }

}
