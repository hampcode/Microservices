import { Order } from './order';
import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { AuthService } from '../user/auth.service';
import { Router } from '@angular/router';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

import swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private urlEndPoint: string = 'http://localhost:8090/api/service-items/items';
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

  public getOrders(): Observable<Order[]> {
    return this.http.get<Order[]>(`${this.urlEndPoint}`,{ headers: this.addAuthorizationHeader() }).pipe(
     catchError(e => { 
     
       return throwError(e);
     })
   );
 }

 public createOrder(order: Order): Observable<Order> {
  return this.http.post(this.urlEndPoint, order, { headers: this.addAuthorizationHeader() })
    .pipe(
      map((response: any) => response.order as Order),
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


}
