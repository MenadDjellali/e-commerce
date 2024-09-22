import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { OrderDetails } from '../_model/order-details.model';
import { MyOrderDetails } from '../_model/order.model';
import { Product } from '../_model/product.model';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private httpClient: HttpClient) { }

  public getAllOrderDetailsForAdmin() : Observable<MyOrderDetails[]>{
    return this.httpClient.get<MyOrderDetails[]>("http://localhost:8090/getAllOrderDetails");
   }

  public getMyOrders() : Observable<MyOrderDetails[]>{
    return this.httpClient.get<MyOrderDetails[]>("http://localhost:8090/getOrderDetails");
   }

  public deleteCartItem(cartId:any){
    return this.httpClient.delete("http://localhost:8090/deleteCartItem/"+cartId);
   }

  public addProduct(product: FormData){
    return this.httpClient.post<Product>("http://localhost:8090/addNewProduct", product);
  }

  public getAllProducts(pageNumber:number, searchKeyword: string= ""){
    return this.httpClient.get<Product[]>("http://localhost:8090/getAllProducts?pageNumber="+pageNumber+"&searchKey="+searchKeyword);
  }

  public getProductDetailsById(productId:any){
    return this.httpClient.get<Product>("http://localhost:8090/getProductDetailsById/"+productId);
   }

  public deleteProduct(productId: number){
   return this.httpClient.delete("http://localhost:8090/deleteProductDetails/"+productId);
  }

  public getProductDetails(isSingeProductCheckout:any,productId:number):any{
    return this.httpClient.get<Product[]>("http://localhost:8090/getProductDetails/"+isSingeProductCheckout+"/"+productId);
   }


   public placeOrder(orderDetails: OrderDetails, isCartCheckout:Boolean){
    return this.httpClient.post("http://localhost:8090/placeOrder/"+isCartCheckout, orderDetails);
   }

   public addToCart(productId:number){
    return this.httpClient.get("http://localhost:8090/addToCart/"+productId);
   }

   public getCartDetails(){
    return this.httpClient.get("http://localhost:8090/getCartDetails");
   }


}
