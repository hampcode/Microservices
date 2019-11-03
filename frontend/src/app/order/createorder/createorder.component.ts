import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-createorder',
  templateUrl: './createorder.component.html',
  styleUrls: ['./createorder.component.css']
})
export class CreateorderComponent implements OnInit {

  title: string = "Comprar";
  constructor() { }

  ngOnInit() {
  }

}
