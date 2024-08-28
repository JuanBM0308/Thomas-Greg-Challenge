import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

import { UserService } from '../user/user.service';

@Component({
  selector: 'app-update-modal',
  templateUrl: './update-modal.component.html',
  styleUrls: ['./update-modal.component.css']
})
export class UpdateModalComponent implements OnInit {
  updateForm!: FormGroup;

  constructor(
    private userService: UserService,
    private formBuilder: FormBuilder,
    private dialogRef: MatDialogRef<UpdateModalComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { id: number },
  ) {
    this.updateForm = this.formBuilder.group({
      name: ['', Validators.required],
      lastname: ['', Validators.required],
      mail: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.loadData(this.data.id);
  }

  loadData(id: number): void {
    this.userService.getUserById(id).subscribe((res) => {
      const recordData = {
        name: res.response.name,
        lastname: res.response.lastname,
        mail: res.response.mail,
        password: res.response.password
      };
      this.updateForm.patchValue(recordData);
    }, (error) => {
      console.log(error);
    });
  }

  updateUser() {
    if (this.updateForm.invalid) {
      return;
    }
    const formValue = this.updateForm.value;
    let body = {
      id: this.data.id,
      name: formValue.name,
      lastname: formValue.lastname,
      mail: formValue.mail,
      password: formValue.password,
      role: 2,
    };

    this.userService.updateUser(body).subscribe((res) => {
      this.dialogRef.close(res);
    }, (error) => {
      console.log(error);
    });
  }

  onCancel(): void {
    this.dialogRef.close();
  }
}