import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http'
import { Observable } from 'rxjs';
import { connectableObservableDescriptor } from 'rxjs/internal/observable/ConnectableObservable';

@Injectable({
  providedIn: 'root'
})
export class ShoppingCartService {

  url = "http://localhost:8082/api/cart"
  constructor(private http: HttpClient) { }

  getCart() : Observable<any>{
      return this.http.get(this.url + '/getCart/34');
  }

  addItem(quantity: number, bookId: any) {
    return this.http.post( this.url + "/addItem/34/" + quantity + "/" + bookId, null );
  }

  emptyCart() : Observable<any>{

    return this.http.delete( this.url + "/emptyCart/34");
  }
  changeQuantity(cartId : any, quantity : number, bookORItemId : any) : Observable<any>{

    return this.http.put( this.url + "/changeQuantity/34" + "/" + quantity + "/" + bookORItemId, null);

  }

  removeItem(cartId : any, bookORItemId : any) : Observable<any>{
    
    return this.http.delete( this.url + "/removeItem/34" + "/" + bookORItemId);

  }

}
