import { OrderService } from './../order.service';
import { Order } from './../order';
import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/user/auth.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {

  orders: Order[];

  constructor(private orderService: OrderService,
    private authService: AuthService,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.orderService.getOrders().subscribe(orders => this.orders = orders);
  }
}
