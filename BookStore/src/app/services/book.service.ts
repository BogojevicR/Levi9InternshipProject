import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http'
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor(private http: HttpClient) { }
  url = "http://localhost:8081/api/book"
  getByCategory(id: any) : Observable<any>{
    if(id =="All")
      return this.http.get(this.url + '/getAll');
    return this.http.get(this.url + '/getByCategory/' + id);
  }

  getAllBooks() : Observable<any>{
    return this.http.get(this.url + '/getAll');
  }

  getAllCategories(): Observable<any>{
    return this.http.get(this.url + '/getAllCategories');
  }

  getTopTen(): Observable<any>{
    return this.http.get(this.url + '/getTopTen');
  }


  sort(searchValue: any) : Observable<any>{
    if(searchValue=="")
      return this.http.get(this.url + '/getAll');
    return this.http.get( this.url + '/sort/' + searchValue);
  }

  addBook (book){
    console.log(book)
    return this.http.post("http://localhost:8081/api/book/save", book);
  }

  disableBook(id){
    return this.http.put("http://localhost:8081/api/book/disable/" + id, id);
  }

  getActiveBooks(){
    return this.http.get("http://localhost:8081/api/book/getActiveBooks");
  }

}
