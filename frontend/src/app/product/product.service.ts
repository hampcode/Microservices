
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, throwError } from 'rxjs';

import { map, catchError, tap } from 'rxjs/operators';
import swal from 'sweetalert2';
import { Product } from './product';
import { AuthService } from '../user/auth.service';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private urlEndPoint: string = 'http://localhost:8090/api/service-products/products';
  private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });


  constructor(private http: HttpClient, private authService: AuthService,private router: Router) { }

  private addAuthorizationHeader() {
    let token = this.authService.token;
    if (token != null) {
      return this.httpHeaders.append('Authorization', 'Bearer ' + token);
    }
    return this.httpHeaders;
  }

  private unAthorized(e): boolean {
    if (e.status == 401) {
      //Cuando el token expira en el backend en el frontend cerramos session
      if (this.authService.isAuthenticated) {
        this.authService.logout();
      }

      this.router.navigate(['/login']);
      return true;
    }

    if (e.status == 403) {
    
      this.router.navigate(['/products']);
      return true;
    }
    return false;
  }

  public getProducts(): Observable<Product[]> {
     return this.http.get<Product[]>(`${this.urlEndPoint}`).pipe(
      catchError(e => { 
      
        return throwError(e);
      })
    );
  }


  public getProduct(id:number): Observable<Product> {
    return this.http.get<Product>(`${this.urlEndPoint}/${id}`, { headers: this.addAuthorizationHeader() }).pipe(
      catchError(e => {

        if (this.unAthorized(e)) {
          return throwError(e);
        }

        this.router.navigate(['/products']);
        console.error(e.error.mensaje);
        swal.fire('Error al editar', e.error.mensaje, 'error');
        return throwError(e);
      })
    );
  }
 
  public createProduct(product: Product): Observable<Product> {
    return this.http.post(this.urlEndPoint, product, { headers: this.addAuthorizationHeader() })
      .pipe(
        map((response: any) => response.product as Product),
        catchError(e => {
          if (this.unAthorized(e)) {
            return throwError(e);
          }

          if (e.status == 400) {
            return throwError(e);
          }

          console.error(e.error.mensaje);
          swal.fire(e.error.mensaje, e.error.error, 'error');
          return throwError(e);
        })
      );
  }



  public updateProduct(product: Product): Observable<any> {
    return this.http.put<any>(`${this.urlEndPoint}/${product.id}`, product, { headers: this.addAuthorizationHeader() }).pipe(
      catchError(e => {

        if (this.unAthorized(e)) {
          return throwError(e);
        }

        if (e.status == 400) {
          return throwError(e);
        }

        console.error(e.error.mensaje);
        swal.fire(e.error.mensaje, e.error.error, 'error');
        return throwError(e);
      })
    );
  }


  
  public deleteProduct(id: number): Observable<Product> {
    return this.http.delete<Product>(`${this.urlEndPoint}/${id}`, { headers: this.addAuthorizationHeader() }).pipe(
      catchError(e => {

        if (this.unAthorized(e)) {
          return throwError(e);
        }

        console.error(e.error.mensaje);
        swal.fire(e.error.mensaje, e.error.error, 'error');
        return throwError(e);
      })
    );
  }
}
