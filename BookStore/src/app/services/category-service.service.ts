import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CategoryServiceService {

  

  private url = 'http://localhost:8081/api/book';

  constructor(private http: HttpClient) { }

  getAllCategories(): Observable<any>{
    console.log("u servisu sam!")
    return this.http.get(this.url + '/getAllCategories');
  }

  addCategory(name){
    console.log("USAOO " + name);
      return this.http.post(this.url + "/addCategory/" + name, name);
      
  }

  

}
