import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http'
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PurchaseService {

  url = "http://localhost:8082/api/purchase"
  constructor(private http: HttpClient) { }

  buyCart(userId : any) : Observable<any > {
    
    return this.http.post(this.url + "/buyCart/"+ userId , null);
  }
}

