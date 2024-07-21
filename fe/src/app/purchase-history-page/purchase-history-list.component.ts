import { Component, OnInit } from '@angular/core';
import { OrderService } from '../service/order.service';
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-purchase-history-list',
  templateUrl: './purchase-history-list.component.html',
  styleUrls: ['./purchase-history-list.component.css']
})
export class PurchaseHistoryListComponent implements OnInit {
  orders: any[] = [];
  orderLabels: {order: any, label: string}[] = [];
  selectedOrder: any = null;

  constructor(private orderService: OrderService,
              private toastrService: ToastrService) {}

  ngOnInit(): void {
    this.getPurchaseHistory();
  }

  private getPurchaseHistory() {
    this.orderService.getPurchaseHistory().subscribe({
      next: (res) => {
        this.orders = res.orders
        for (let order of this.orders) {
          // Calculate total price and item count
          let totalPrice: number = 0;
          let itemCount: number = 0;
          for (let orderItem of order.orderItems) {
            totalPrice += orderItem.price;
            itemCount++;
          }

          // Calculate time ago
          let timeAgo: string = "";
          let createdAt: Date = new Date(order.order.createAt);
          let now: Date = new Date();
          let diffMs: number = now.getTime() - createdAt.getTime();
          let diffDays: number = Math.floor(diffMs / 86400000); // days
          let diffHrs: number = Math.floor((diffMs % 86400000) / 3600000); // hours
          if (diffDays > 1) {
            timeAgo = `${diffDays} days ago`;
          } else {
            timeAgo = `${diffHrs} hours ago`;
          }

          let orderLabel: string = `Order #${order.order.id} - $${totalPrice} (${itemCount} items) - ${timeAgo}`;
          this.orderLabels.push({order: order, label: orderLabel});
        }
      },
      error: () => this.toastrService.error("Failed to get purchase history"),
    });
  }

  selectOrder(order: any): void {
    this.selectedOrder = order;
  }
}
