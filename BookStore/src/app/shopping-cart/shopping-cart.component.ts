import { PurchaseService } from './../services/purchase.service';
import { ShoppingCartService } from './../services/shopping-cart.service';
import { Component, OnInit } from '@angular/core';
import swal from 'sweetalert2';
@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {
  cart = []
  purchase = []
  text : string
  constructor(private cartService : ShoppingCartService, private purchaseService : PurchaseService) { }

  ngOnInit() {
      this.getCart()
  }

  getCart() {
  this.cartService.getCart().subscribe(data => {
    this.cart = data;
  })
  }
  emptyCart() {
    this.cartService.emptyCart().subscribe(data => {
      this.cart = data;
        swal.fire({
         type: 'success',
         text: 'Successful!'
         })
    })
  }
  changeQuantity(cartId : any, quantity : number, bookORItemId : any) {
    this.cartService.changeQuantity(cartId, quantity, bookORItemId).subscribe(data => {
      this.cart = this.cart;
    })
  }

    removeItem(cartId : any, bookORItemId : any) {
      this.cartService.removeItem(cartId, bookORItemId).subscribe(data => {
        this.getCart()
      })
  }

  buyCart(userId : any) {
    this.purchaseService.buyCart(userId).subscribe(data => {
      this.text = JSON.stringify(data) ;
      swal.fire({
        type: 'success',
        title: 'Purchase Successful!',
        text: this.text
        })
      this.purchase = data;
    }, err => {
       swal.fire({
        type: 'error',
        text: 'Something went wrong!'
        })
    })
  }
}
