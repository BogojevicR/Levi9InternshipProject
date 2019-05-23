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
      this.cartService.getCart().subscribe(data => {
        this.cart = data;
      })
  }

  emptyCart(){
    console.log("USAO")
    this.cartService.emptyCart().subscribe(data => {
      this.cart = data;
    })
  }
}
