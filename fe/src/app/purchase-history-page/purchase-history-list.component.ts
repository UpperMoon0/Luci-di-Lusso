import { Component, OnInit } from '@angular/core';
import { OrderService } from '../service/order.service';
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-purchase-history-list',
  templateUrl: './purchase-history-list.component.html',
  styleUrls: ['./purchase-history-list.component.css']


})
export class PurchaseHistoryListComponent implements OnInit {
  orders: {
    id: number;
    firstProductName: string;
    firstProductSize: string;
    firstProductQuantity: string;
    price: number;
    timeAgo: string;
  }[] = [];
  selectedOrderId: number = null;
  selectedOrderDetails: {
    customerName: string;
    totalPrice: number;
    createAt: string;
  } = {
    customerName: '',
    totalPrice: 0,
    createAt: '',
  };
  selectedOrderProductList: {
    name: string;
    size: string;
    price: number;
    quantity: number;
    type: string;
    imageUrl: string;
  }[] = [];

  displayedColumns: string[] = ['productName', 'quantity', 'price', 'orderTime'];


  constructor(private orderService: OrderService,
              private toastrService: ToastrService) {}

  ngOnInit(): void {
    this.getOrders();
  }

  private getOrders() {
    this.orderService.getOrders().subscribe({
      next: (res) => (this.orders = res.orders),
      error: () => this.toastrService.error("Failed to get orders"),
    });
  }

  onOrderSelect(orderId: number) {
    this.selectedOrderId = orderId;
    this.orderService.getOrderDetails(orderId).subscribe({
      next: (res) => {
        this.selectedOrderDetails.customerName = res.customerName;
        this.selectedOrderDetails.totalPrice = res.totalPrice;
        this.selectedOrderDetails.createAt = res.createAt;
        this.selectedOrderProductList = res.productList;
      },
      error: () => this.toastrService.error("Failed to get order details"),
    });
  }
}
