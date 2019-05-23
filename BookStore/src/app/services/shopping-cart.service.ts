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
      return this.http.get(this.url + '/getCart/11');
  }

  addItem(quantity: number, bookId: any) {
    console.log(quantity,bookId)
    return this.http.post( this.url + "/addItem/11/" + quantity + "/" + bookId, null );
  }


}
