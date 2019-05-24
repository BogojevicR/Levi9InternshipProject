import { PurchaseService } from './../services/purchase.service';
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
  constructor(private cartService: ShoppingCartService, private purchaseService: PurchaseService) { }
  purchaseString : string
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
          title: 'Item already in Cart!',
          type: 'error'
        })
      }
    })
  }

  buyNow(userId : any, quantity: number, bookId: any){
    this.purchaseService.buyNow(userId,quantity,bookId).subscribe(data => {
        this.purchaseString = JSON.stringify(data);
        swal.fire({
          type: 'success',
          title: 'Purchase Successful!',
          text: this.purchaseString
          })

      }, err => {
         swal.fire({
          type: 'error',
          text: 'Something went wrong!'
          })
      })
  }
}
