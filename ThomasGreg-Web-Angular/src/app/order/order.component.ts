import { Component, OnInit } from '@angular/core';
import { OrderService } from './order.service';
import { MatDialog } from '@angular/material/dialog';

import { OrderModalComponent } from '../order-modal/order-modal.component';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {
  discountsList: any[] = [];

  constructor(
    private orderService: OrderService,
    public dialog: MatDialog
  ) { }

  ngOnInit() {
    this.loadDiscounts();
  }

  loadDiscounts(): void {
    this.orderService.getDiscounts().subscribe((res: any) => {
      this.discountsList = res.response;
    });
  }

  openOrderModal(id: number): void {
    const dialogRef = this.dialog.open(OrderModalComponent, { width: '80%', data: { id: id } });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.loadDiscounts();
      }
      console.log('The dialog was closed');
    });
  }
}