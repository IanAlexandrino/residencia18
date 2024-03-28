import { Component, OnInit } from '@angular/core';
import { ApiServiceService } from '../api-service.service';


@Component({
  selector: 'app-country',
  standalone: true,
  imports: [],
  templateUrl: './country.component.html',
  styleUrl: './country.component.css'
})
export class CountryComponent implements OnInit {

  camposDoForm: any[];

  constructor(private apiService: ApiServiceService) { }

  ngOnInit(): void {
    this.apiService.getFirstRecord().subscribe(
      campos => {
        this.camposDoForm = campos;
      },
      error => {
        console.error('Erro ao obter os campos do formulário:', error);
      }
    )
  }

}