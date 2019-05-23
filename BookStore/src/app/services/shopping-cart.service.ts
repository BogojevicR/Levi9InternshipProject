import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http'
import { Observable } from 'rxjs';

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


}
