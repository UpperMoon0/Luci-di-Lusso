import { Component } from '@angular/core';

interface DiamondPrice {
  budget: string;
  factor: string;
}

interface DiamondPrice01 {
  carat: string;
  wholesome: string;
  retail: string;
}

@Component({
  selector: 'app-discover',
  templateUrl: './discover.component.html',
  styleUrls: ['./discover.component.css']
})
export class DiscoverComponent  {

  constructor() {

  }

  diamondPriceData: DiamondPrice[] = [
    { budget: '$4,000 - $6,000',
      factor: 'Best Quality: > .8ct, D-F, > VS2' +
        '<br>Best Value: > .9ct, G-H, > SI1' +
        '<br>Best Size: > 1ct, H-J, > SI2' },
    { budget: '$6,000 - $8,000',
      factor: 'Best Quality: > .95ct, D-F, VS2' +
        '<br>Best Value: > 1.15ct, G-H, > SI1' +
        '<br>Best Size: > 1.25ct,  H-I, > SI2' },
    { budget: '$10,000 - $12,000',
      factor: 'Best Quality: > 1.1ct, D-F, > VS2' +
        '<br>Best Value: > 1.5, G-H, > SI1' +
        '<br>Best Size: > 1.6ct,  H-I, > SI2' },
    { budget: '$15,000 - $18,000',
      factor: 'Best Quality: > 1.25ct, D-F, > VS1' +
        '<br>Best Value: > 1.8ct. G-H, > VS2' +
        '<br>Best Size: > 2ct,  H-I, > SI1' },
    { budget: '$20,000 - $25,000',
      factor: 'Best Quality: > 1.5ct, D-F, > VS1' +
        '<br>Best Value: > 2ct. G-H, > VS2' +
        '<br>Best Size: > 2.2ct,  H-I, > SI1' },
    // Add more rows as needed
  ];

  diamondPriceData01: DiamondPrice01[] = [
    {carat: 'SI1',
    wholesome: '$4,880',
    retail: '$6,071'},
    {carat: 'VS2',
      wholesome: '$5,438',
      retail: '$7,231'},
    {carat: 'VS1',
      wholesome: '$5,956',
      retail: '$7,918'},
    {carat: 'VVS2',
      wholesome: '$6,758',
      retail: '$8,986'},
    {carat: 'VVS1',
      wholesome: '$7,709',
      retail: '$10,249'},
    {carat: 'IF',
      wholesome: '$8,965',
      retail: '$11,920'},
    {carat: 'FL',
      wholesome: '$10,834',
      retail: '$14,404'},
    {carat: 'SI1',
      wholesome: '$1,000',
      retail: '$1,500'},
    // Add more rows as needed
    ];

}
