import { Component, OnInit } from '@angular/core';
import { CategoryServiceService } from '../services/category-service.service';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import swal from 'sweetalert2';

@Component({
  selector: 'app-add-category',
  templateUrl: './add-category.component.html',
  styleUrls: ['./add-category.component.css']
})
export class AddCategoryComponent implements OnInit {

  constructor(private categoryService: CategoryServiceService, private router: Router, private activeRoute: ActivatedRoute, private formBuilder: FormBuilder) { }

  categoryForm: FormGroup;
  submitted : boolean;

  ngOnInit() {
    this.categoryForm = this.formBuilder.group({
      name: ['', Validators.compose([
        Validators.required,
        Validators.pattern("^([a-zA-Z\u0080-\u024F]+(?:. |-| |'))*[a-zA-Z\u0080-\u024F]*$")
      ])]
    });

  }

  addCategory(){
    this.submitted = true;
    if (this.categoryForm.invalid) {
     // swal("Your input is not valid.")
    }
    else {
      console.log(this.categoryForm.value.name);
      this.categoryService.addCategory(this.categoryForm.value.name).subscribe(res => {
      //  swal('Successfully added!')
        this.router.navigate(['category/']);
      },
        err => {
       //   swal('Something went wrong!')
        })
    }
  }



}
