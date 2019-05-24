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
  value : any;

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
      ])],
    /*  state: ['', Validators.compose([
        Validators.required
      ])]*/
    })

    this.categoryService.getAllCategories().subscribe
      (data => {
        console.log(data);
        this.categories = data;
      });

      this.loadData();
  }

  seeCategory(categoryId){
    //console.log(this.value);
    //console.log("category id" + categoryId)
    this.categoryService.getCategory(categoryId).subscribe(
      (data)=>{
        this.categorySelected = data;
      })
  }

  loadData() {
    this.bookService.getBook(this.id).subscribe(res => {
      this.book = res;
      console.log(this.book);
     /* this.bookForm.setValue({
        name : this.book.title,
        author : this.book.author,
        price : this.book.price,
        soldAmount : this.book.soldAmount,
        quantity : this.book.quantity
      })*/
      console.log(this.book.category.name)
      this.bookForm.controls['category'].setValue(this.book.category.name);
      this.bookForm.controls['name'].setValue(this.book.title);
      this.bookForm.controls['author'].setValue(this.book.author);
      this.bookForm.controls['price'].setValue(this.book.price);
      this.bookForm.controls['soldAmount'].setValue(this.book.soldAmount);
      this.bookForm.controls['quantity'].setValue(this.book.quantity);
     // this.bookForm.controls['state'].setValue(this.book.state);

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
