import { Component, OnInit } from '@angular/core';
import axios from 'axios';

@Component({
  selector: 'app-country',
  standalone: true,
  imports: [],
  templateUrl: './country.component.html',
  styleUrl: './country.component.css'
})
export class CountryComponent implements OnInit {

  country: any; // Para armazenar o primeiro país
  camposDoForm: any;

  constructor() { }

  ngOnInit(): void {
    this.fetchCountryData();
  }

  async fetchCountryData() {
    try {
      const response = await axios.get('https://restcountries.com/v3.1/all');
      // Pegar o primeiro país da resposta
      this.country = response.data[0];
      // Transformar os dados para o formato desejado (se necessário)
      this.transformCountryData();
    } catch (error) {
      console.error('Erro ao buscar dados do país:', error);
    }
  }

  transformCountryData() {
    // Aqui você pode transformar os dados do país conforme necessário
    // Por exemplo, reorganizar propriedades, formatar valores, etc.
    // Para este exemplo, vamos apenas exibir o nome do país no console
    //console.log('Nome do país:', this.country.name);
    //console.log('Nome do país:', typeof(this.country));
    console.log('Nome do país:', this.country);

    this.camposDoForm = {
      tipo: typeof(this.country),
      nome: this.country.name,
      rotulo: this.country.
    };
  }

}