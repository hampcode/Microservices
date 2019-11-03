import { OrderService } from './../order.service';
import { ProductService } from './../../product/product.service';
import { Order } from './../order';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import swal from 'sweetalert2';

@Component({
  selector: 'app-createorder',
  templateUrl: './createorder.component.html',
  styleUrls: ['./createorder.component.css']
})
export class CreateorderComponent implements OnInit {

  title: string = "Comprar";
  errors: string[];

  order: Order = new Order();
  constructor(private productService: ProductService,
    private orderService: OrderService,
    private router: Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(params => {
      let productId = +params.get('id');
      this.productService.getProduct(productId).subscribe(product => this.order.product = product);
    });
  }

  create(): void {
    console.log(this.order);
    this.orderService.createOrder(this.order)
      .subscribe(
        order => {
          this.router.navigate(['/products']);
          swal.fire('Nuevo Producto', `La orden del product  ${order.product.name} ha sido creado con éxito`, 'success');
        },
        err => {
          this.errors = err.error.errors as string[];
          console.error('Código del error desde el backend: ' + err.status);
          console.error(err.error.errors);
        }
      );
  }


}
