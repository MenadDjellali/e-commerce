import { Component, OnInit } from '@angular/core';
import { ProductService } from '../_services/product.service';
import { MyOrderDetails } from '../_model/order.model';

@Component({
  selector: 'app-order-detais',
  templateUrl: './order-detais.component.html',
  styleUrls: ['./order-detais.component.css']
})
export class OrderDetaisComponent implements OnInit {

  displayedColumns: string[] = ['Id', 'Product Name', 'Name', 'Address', 'Contact No' ,'Status'];
  dataSource: MyOrderDetails[] = [];
  constructor(private productService : ProductService) { }

  ngOnInit(): void {
    this.getAllOrderDetailsForAdmin();
  }

  getAllOrderDetailsForAdmin(){
    this.productService.getAllOrderDetailsForAdmin().subscribe(
      (resp) => {
        console.log(resp);
        this.dataSource = resp;
      }, (error) => {
        console.log(error);
      }
    );
  }
}
