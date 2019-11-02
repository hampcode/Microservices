import { ProductService } from './../product.service';
import { Product } from './../product';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import swal from 'sweetalert2';
@Component({
  selector: 'app-createproduct',
  templateUrl: './createproduct.component.html',
  styleUrls: ['./createproduct.component.css']
})
export class CreateproductComponent implements OnInit {

  product: Product = new Product();
  title: string = "Crear Producto";

  errors: string[];

  constructor(private productService: ProductService,
    private router: Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(params => {
      let id = +params.get('id');
      if (id) {
        this.productService.getProduct(id).subscribe((product) => this.product = product);
      }
    });
  }

  create(): void {
    console.log(this.product);
    this.productService.createProduct(this.product)
      .subscribe(
        product => {
          this.router.navigate(['/products']);
          swal.fire('Nuevo Producto', `El product ${product.name} ha sido creado con éxito`, 'success');
        },
        err => {
          this.errors = err.error.errors as string[];
          console.error('Código del error desde el backend: ' + err.status);
          console.error(err.error.errors);
        }
      );
  }

  update(): void {
    console.log(this.product);
    this.productService.updateProduct(this.product)
      .subscribe(
        json => {
          this.router.navigate(['/products']);
          swal.fire('Product Actualizado', `${json.mensaje}: ${json.product.name}`, 'success');
        },
        err => {
          this.errors = err.error.errors as string[];
          console.error('Código del error desde el backend: ' + err.status);
          console.error(err.error.errors);
        }
      )
  }

}
