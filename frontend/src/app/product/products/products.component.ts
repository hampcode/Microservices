import { ProductService } from './../product.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Product } from '../product';

import swal from 'sweetalert2';
import { AuthService } from 'src/app/user/auth.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  products: Product[];
  
  productSelected: Product;

  constructor(private productService: ProductService,
    private authService: AuthService,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.productService.getProducts().subscribe(products => this.products = products);
  }


  delete(product: Product): void {
    swal.fire({
      title: 'Está seguro?',
      text: `¿Seguro que desea eliminar el producto ${product.name}?`,
      type: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si, eliminar!',
      cancelButtonText: 'No, cancelar!',
      confirmButtonClass: 'btn btn-success',
      cancelButtonClass: 'btn btn-danger',
      buttonsStyling: false,
      reverseButtons: true
    }).then((result) => {
      if (result.value) {

        this.productService.deleteProduct(product.id).subscribe(
          () => {
            this.products = this.products.filter(pro => pro !== product)
            swal.fire(
              'Cliente Eliminado!',
              `Producto ${product.name} eliminado con éxito.`,
              'success'
            )
          }
        )

      }
    });
  }
}
