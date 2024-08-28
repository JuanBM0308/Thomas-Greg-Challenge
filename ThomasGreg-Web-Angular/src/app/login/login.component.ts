import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;
  submittedLoginForm: boolean = false;

  constructor(
    private authService: AuthService,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      mail: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  onLogin() {
    this.submittedLoginForm = true;
    if (this.loginForm.invalid) {
      return;
    }
    const formValue = this.loginForm.value;
    let body = {
      mail: formValue.mail,
      password: formValue.password,
    };

    this.authService.login(body).subscribe((res) => {
      if (!res) {
        alert('Credenciales inválidas');
      }
    }, (error) => {
      console.log(error);
    }); 
  }
}