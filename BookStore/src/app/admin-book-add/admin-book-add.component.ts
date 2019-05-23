import { Component, OnInit } from '@angular/core';
import { BookService } from '../services/book.service';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CategoryServiceService } from '../services/category-service.service';
import swal from 'sweetalert2';

@Component({
  selector: 'app-admin-book-add',
  templateUrl: './admin-book-add.component.html',
  styleUrls: ['./admin-book-add.component.css']
})
export class AdminBookAddComponent implements OnInit {

  constructor(private bookService: BookService, private categoryService : CategoryServiceService, private router: Router, private activeRoute: ActivatedRoute, private formBuilder: FormBuilder) { }

  bookForm: FormGroup;
  categories : any = [];
  submitted : boolean;
  book : any = {};
  categorySelected : any = {};
  category: any = {};

  ngOnInit() {
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
    })

    this.categoryService.getAllCategories().subscribe
      (data => {
        console.log(data);
        this.categories = data;
      });

  }

  seeCategory(id){
    console.log("1  " + id);
    this.categoryService.getCategory(id).subscribe(
      (data)=>{
        this.categorySelected = data;
      }
    )
   // this.categorySelected = category;

  }

  addBook(){
    this.submitted = true;

    console.log("2" + this.categorySelected.id + this.categorySelected.name)
    
    this.book = {
      title : this.bookForm.controls['name'].value,
      author : this.bookForm.controls['author'].value,
      category : {
         id : this.categorySelected.id,
         name: this.categorySelected.name,
      },
      price : this.bookForm.controls['price'].value,
      state : "0",
      quantity : this.bookForm.controls['quantity'].value,
      soldAmount : 0
    }

    if (this.bookForm.invalid) {
      swal.fire("Your input is not valid.")
    }
    else {
      console.log(this.bookForm.value.name);
      this.bookService.addBook(this.book).subscribe(res => {
        swal.fire('Successfully added!')
        this.router.navigate(['adminBook/']);
      },
        err => {
          swal.fire('Something went wrong!')
        })
    }
  }

}
