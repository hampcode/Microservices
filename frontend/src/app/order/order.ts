import { Product } from './../product/product';
export class Order {
    id: number;
    quantity: number;
    //productId: number;
    product:Product;
    total:number;
}
