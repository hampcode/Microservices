import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './user/login.component';
import { HeaderComponent } from './header/header.component';

import { CreateproductComponent } from './product/createproduct/createproduct.component';
import { ProductsComponent } from './product/products/products.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CreateorderComponent } from './order/createorder/createorder.component';
import { OrdersComponent } from './order/orders/orders.component';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HeaderComponent,
    ProductsComponent,
    CreateproductComponent,
    CreateorderComponent,
    OrdersComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
