import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { UsersApiService } from '../../services/users-api.service';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrl: './user-form.component.css'
})
export class UserFormComponent {
  
  userForm: FormGroup = new FormGroup({});

  constructor(
    private userApiService: UsersApiService
  ) 
  {}

  ngOnInit() {
    this.initForm();
  }

  initForm(){
    this.userForm = new FormGroup({
      login: new FormControl('', [Validators.required]),
      pass: new FormControl('', [Validators.required]),
      description: new FormControl('', [Validators.required]),
    });
  }

  onSubmit(){
    this.userApiService.addUser(this.userForm).subscribe(
      res => {
        console.log(res)
      }
    );
  }
}
