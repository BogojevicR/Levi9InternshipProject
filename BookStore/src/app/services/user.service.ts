import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http'
import { Observable } from 'rxjs';
import { getDefaultService } from 'selenium-webdriver/opera';
@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor( private http: HttpClient) { }
  url = "http://localhost:8080/user"

  getUser(id : any) : Observable<any> {

      return this.http.get(this.url + "/getUser/" + id);
  }

  getAllUsers() : Observable<any> {

    return this.http.get(this.url + "/getAll")
  }

}
