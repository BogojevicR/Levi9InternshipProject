import { Component, OnInit } from '@angular/core';
import { BookService } from '../services/book.service';
import { CategoryServiceService } from '../services/category-service.service';
import { Router, ActivatedRoute } from '@angular/router';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import swal from 'sweetalert2';

@Component({
  selector: 'app-admin-book-update',
  templateUrl: './admin-book-update.component.html',
  styleUrls: ['./admin-book-update.component.css']
})
export class AdminBookUpdateComponent implements OnInit {

  bookForm: FormGroup;
  categories : any = [];
  id : any;
  book : any = [];
  submitted : any = false;
  categorySelected : any = {};
  updatedBook : any =[];

  constructor(private bookService: BookService, private categoryService : CategoryServiceService, private router: Router, private activeRoute: ActivatedRoute, private formBuilder: FormBuilder) { }

  ngOnInit() {

    this.activeRoute.params.subscribe(params => this.id = params);

    console.log(this.id);

    this.bookForm = this.formBuilder.group({
      name: ['', Validators.compose([
        Validators.required
      ])],
      author: ['', Validators.compose([
        Validators.required,
        Validators.pattern("^([a-zA-Z\u0080-\u024F]+(?:. |-| |'))*[a-zA-Z\u0080-\u024F]*$")
      ])],
      price: ['', Validators.compose([
        Validators.required,
        Validators.pattern("[0-9]*")
      ])],
      quantity:['', Validators.compose([
        Validators.required,
        Validators.pattern("[0-9]*")
      ])],
      category : ['', Validators.required],
      soldAmount: ['', Validators.compose([
        Validators.required,
        Validators.pattern("[0-9]*")
      ])]
    })

    this.categoryService.getAllCategories().subscribe
      (data => {
        console.log(data);
        this.categories = data;
      });

      this.loadData();
  }

  seeCategory(categoryId){
    this.categoryService.getCategory(categoryId).subscribe(
      (data)=>{
        this.categorySelected = data;
      })
  }

  loadData() {
    this.bookService.getBook(this.id).subscribe(res => {
      this.book = res;
      console.log(this.book);
      this.bookForm.setValue({
        name : this.book.title,
        author : this.book.author,
        category : this.book.category.name,
        price : this.book.price,
        soldAmount : this.book.soldAmount,
        quantity : this.book.quantity
      })
    });
  }

  updateBook() {
    
    this.updatedBook = {
      id : this.book.id,
      title : this.bookForm.controls['name'].value,
      author : this.bookForm.controls['author'].value,
      category : {
         id : this.categorySelected.id,
         name: this.categorySelected.name,
      },
      price : this.bookForm.controls['price'].value,
      state : this.book.state,
      quantity : this.bookForm.controls['quantity'].value,
      soldAmount : this.book.soldAmount
    }

    this.submitted = true;
    if (this.bookForm.invalid) {
      swal.fire("Your input is not valid.")
      console.log(this.updatedBook);
    }
    else {
      this.bookService.editBook(this.updatedBook).subscribe(res => {
        swal.fire('Successfully updated!');
        this.router.navigate(['adminBook/']);
      },
        err => {
          swal.fire('Something went wrong!')
          console.log(this.updatedBook);
        })
    }
  
  }


}
