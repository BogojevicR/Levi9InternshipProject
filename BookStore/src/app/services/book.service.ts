import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http'
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor(private http: HttpClient) { }

  getByCategory(id: any) : Observable<any>{
    if(id =="All")
      return this.http.get('http://localhost:8081/api/book/getAll');
    return this.http.get('http://localhost:8081/api/book/getByCategory/' + id);
  }

  getAllBooks() : Observable<any>{
    return this.http.get('http://localhost:8081/api/book/getAll');
  }

  getAllCategories(): Observable<any>{
    return this.http.get("http://localhost:8081/api/book/getAllCategories");
  }

  sort(searchValue: any) : Observable<any>{
    if(searchValue=="")
      return this.http.get('http://localhost:8081/api/book/getAll');
    return this.http.get('http://localhost:8081/api/book/sort/' + searchValue);
  }

}
