import { ShoppingCartService } from './../services/shopping-cart.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {
  cart = []
  constructor(private cartService : ShoppingCartService) { }

  ngOnInit() {
      this.getCart()
  }

  getCart(){
  this.cartService.getCart().subscribe(data => {
    this.cart = data;
  })
  }
  emptyCart(){
    this.cartService.emptyCart().subscribe(data => {
      this.cart = data;
    })
  }
  changeQuantity(cartId : any, quantity : number, bookORItemId : any){
    this.cartService.changeQuantity(cartId, quantity, bookORItemId).subscribe(data => {
      this.cart = this.cart;
    })
  }

    removeItem(cartId : any, bookORItemId : any){
      this.cartService.removeItem(cartId, bookORItemId).subscribe(data => {
        this.getCart()
      })
  }
}
