import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

import { OrderService } from '../order/order.service';

@Component({
  selector: 'app-order-modal',
  templateUrl: './order-modal.component.html',
  styleUrls: ['./order-modal.component.css']
})
export class OrderModalComponent implements OnInit {
  items: any[] = [];
  displayedColumns: string[] = ['name', 'unitPrice', 'quantity', 'total']

  constructor(
    private orderService: OrderService,
    private dialogRef: MatDialogRef<OrderModalComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { id: number },
  ) { }

  ngOnInit(): void {
    this.loadData(this.data.id);
  }

  loadData(id: number): void {
    this.orderService.orderDetails(id).subscribe((res) => {
      this.items = res.response;
    }, (error) => {
      console.log(error);
    });
  }
}