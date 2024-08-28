import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';

import { UserService } from './user.service';

import { UpdateModalComponent } from '../update-modal/update-modal.component';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css'],
})
export class UserComponent implements OnInit {
  userForm!: FormGroup;
  submittedUserForm: boolean = false;

  userList: any[] = [];

  constructor(
    private userService: UserService,
    private formBuilder: FormBuilder,
    public dialog: MatDialog
  ) {
    this.userForm = this.formBuilder.group({
      name: ['', Validators.required],
      lastname: ['', Validators.required],
      mail: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  ngOnInit() {
    this.loadUsers();
  }

  loadUsers(): void {
    this.userService.getAllUsers().subscribe((res: any) => {
      this.userList = res.response;
    });
  }

  createUser() {
    this.submittedUserForm = true;
    if (this.userForm.invalid) {
      return;
    }
    const formValue = this.userForm.value;
    let body = {
      name: formValue.name,
      lastname: formValue.lastname,
      mail: formValue.mail,
      password: formValue.password,
      role: 2,
    };

    this.userService.createUser(body).subscribe((res) => {
      this.submittedUserForm = false;
      this.loadUsers();
    },(error) => {
      console.log(error);
    });
  }

  openUpdateModal(id: number): void {
    const dialogRef = this.dialog.open(UpdateModalComponent, { width: '250px', data: { id: id } });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.loadUsers();
      }
      console.log('The dialog was closed');
    });
  }

  deleteUser(id: number): void {
    this.userService.deleteUser(id).subscribe((res) => {
      this.loadUsers();
    }, (error) => {
      console.log(error);
    });
  }
}