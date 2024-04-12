import { Component, Inject, Input } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { UsersApiService } from '../../services/users-api.service';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrl: './user-form.component.css'
})
export class UserFormComponent {
  
  userForm: FormGroup = new FormGroup({});
  isEdit = false;

  constructor(
    private userApiService: UsersApiService,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) 
  {}

  ngOnInit() {
    this.initForm();
  }

  initForm(){
    if(!this.data.existingUser && Object.keys(this.data.existingUser).length < 0) {
      this.userForm = new FormGroup({
        login: new FormControl('', [Validators.required]),
        pass: new FormControl('', [Validators.required]),
        description: new FormControl('', [Validators.required]),
      });
    }
    else{
      this.userForm = new FormGroup({
        login: new FormControl('', [Validators.required]),
        pass: new FormControl('', [Validators.required]),
        description: new FormControl('', [Validators.required]),
        id : new FormControl(0, [Validators.required]),
        status: new FormControl(1, [Validators.required]),
      });
      this.userForm.patchValue({
        login: this.data.existingUser.login,
        pass: this.data.existingUser.pass,
        description: this.data.existingUser.description,
        id: this.data.existingUser.id,
        status: this.data.existingUser.status
      });
      this.isEdit = true;
    }
  }

  onSubmit(){
    if (this.isEdit) {
      this.editUser();
    }
    else{
      this.addUser();
    }
  }

  addUser(){
    this.userApiService.addUser(this.userForm).subscribe(
      res => {
        console.log(res)
      }
    );
  }

  editUser(){
    this.userApiService.editUser(this.userForm).subscribe(
      res => {
        console.log(res)
      }
    );
  }
}
