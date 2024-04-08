import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import {Router} from '@angular/router';
import { LoginService } from './login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username: string = '';
  password: string = '';
  
  loginForm: FormGroup = new FormGroup({});

  constructor(
    private loginService: LoginService,
    private router: Router
  ) { }

  ngOnInit() {
    this.initForm();
  }

  initForm(){
    this.loginForm = new FormGroup({
      username: new FormControl('', [Validators.required, Validators.minLength(4),]),
      password: new FormControl('', [Validators.required, Validators.minLength(4)])
    });
  }

  onSubmit(){
    console.log('submitted')
    this.loginService.login(this.loginForm).subscribe(
      res => {
        if (res.code == 'OK') {
          this.router.navigate(['/users']);
        }
      }
    );
  }
  register(){
    this.loginService.register({username: "admin", password: "admin"}).subscribe(
      res => {
        console.log('res', res)
      }
    );
  }
}