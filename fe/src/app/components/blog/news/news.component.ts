import { Component } from '@angular/core';

interface DiamondPrice {
  budget: string;
  factor: string;
}

interface DiamondPrice01D {
  carat: string;
  wholesome: string;
  retail: string;
}

interface DiamondPrice01E {
  carat: string;
  wholesome: string;
  retail: string;
}

interface DiamondPrice01F {
  carat: string;
  wholesome: string;
  retail: string;
}

interface DiamondPrice01G {
  carat: string;
  wholesome: string;
  retail: string;
}

interface DiamondPrice01H {
  carat: string;
  wholesome: string;
  retail: string;
}

interface DiamondPrice02D {
  carat: string;
  wholesome: string;
  retail: string;
}

interface DiamondPrice02E {
  carat: string;
  wholesome: string;
  retail: string;
}

interface DiamondPrice02F {
  carat: string;
  wholesome: string;
  retail: string;
}

interface DiamondPrice02G {
  carat: string;
  wholesome: string;
  retail: string;
}

interface DiamondPrice02H {
  carat: string;
  wholesome: string;
  retail: string;
}

interface DiamondPrice03D {
  carat: string;
  wholesome: string;
  retail: string;
}

interface DiamondPrice03E {
  carat: string;
  wholesome: string;
  retail: string;
}

interface DiamondPrice03F {
  carat: string;
  wholesome: string;
  retail: string;
}

interface DiamondPrice03G {
  carat: string;
  wholesome: string;
  retail: string;
}

interface DiamondPrice03H {
  carat: string;
  wholesome: string;
  retail: string;
}

interface DiamondPrice04D {
  carat: string;
  wholesome: string;
  retail: string;
}

interface DiamondPrice04E {
  carat: string;
  wholesome: string;
  retail: string;
}

interface DiamondPrice04F {
  carat: string;
  wholesome: string;
  retail: string;
}

interface DiamondPrice04G {
  carat: string;
  wholesome: string;
  retail: string;
}

interface DiamondPrice04H {
  carat: string;
  wholesome: string;
  retail: string;
}
@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.css']
})
export class NewsComponent {
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

  diamondPriceData01D: DiamondPrice01D[] = [
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
    // Add more rows as needed
  ];

  diamondPriceData01E: DiamondPrice01E[] = [
    {carat: 'SI1',
      wholesome: '$4,787',
      retail: '$5,972'},
    {carat: 'VS2',
      wholesome: '$5,140',
      retail: '$6,834'},
    {carat: 'VS1',
      wholesome: '$5,555',
      retail: '$7,386'},
    {carat: 'VVS2',
      wholesome: '$5,504',
      retail: '$7,317'},
    {carat: 'VVS1',
      wholesome: '$6,957',
      retail: '$9,250'},
    {carat: 'IF',
      wholesome: '$7,580',
      retail: '$10,078'},
    {carat: 'FL',
      wholesome: '$8,994',
      retail: '$11,957'},
  ];

  diamondPriceData01F: DiamondPrice01F[] = [
    {carat: 'SI1',
      wholesome: '$4,719',
      retail: '$5,829'},
    {carat: 'VS2',
      wholesome: '$5,013',
      retail: '$6,665'},
    {carat: 'VS1',
      wholesome: '$5,379',
      retail: '$7,151'},
    {carat: 'VVS2',
      wholesome: '$5,400',
      retail: '$7,179'},
    {carat: 'VVS1',
      wholesome: '$5,490',
      retail: '$7,897'},
    {carat: 'IF',
      wholesome: '$6,019',
      retail: '$8,003'},
    {carat: 'FL',
      wholesome: '$6,922',
      retail: '$9,203'},
  ];

  diamondPriceData01G: DiamondPrice01G[] = [
    {carat: 'SI1',
      wholesome: '$4,600',
      retail: '$5,701'},
    {carat: 'VS2',
      wholesome: '$4,821',
      retail: '$6,223'},
    {carat: 'VS1',
      wholesome: '$5,028',
      retail: '$6,620'},
    {carat: 'VVS2',
      wholesome: '$5,243',
      retail: '$6,907'},
    {carat: 'VVS1',
      wholesome: '$5,560',
      retail: '$7,449'},
    {carat: 'IF',
      wholesome: '$5,834',
      retail: '$7,817'},
    {carat: 'FL',
      wholesome: '$6,676',
      retail: '$8,943'},
  ];

  diamondPriceData01H: DiamondPrice01H[] = [
    {carat: 'SI1',
      wholesome: '$4,075',
      retail: '$5,272'},
    {carat: 'VS2',
      wholesome: '$4,379',
      retail: '$5,821'},
    {carat: 'VS1',
      wholesome: '$4,441',
      retail: '$5,997'},
    {carat: 'VVS2',
      wholesome: '$4,688',
      retail: '$6,328'},
    {carat: 'VVS1',
      wholesome: '$4,891',
      retail: '$6,602'},
    {carat: 'IF',
      wholesome: '$5,189',
      retail: '$7,006'},
    {carat: 'FL',
      wholesome: '$5,855',
      retail: '$7,904'},
  ];

  diamondPriceData02D: DiamondPrice02D[] = [
    {carat: 'SI1',
      wholesome: '$10,716',
      retail: '$13,081'},
    {carat: 'VS2',
      wholesome: '$12,460',
      retail: '$16,565'},
    {carat: 'VS1',
      wholesome: '$12,355',
      retail: '$16,427'},
    {carat: 'VVS2',
      wholesome: '$14,234',
      retail: '$18,924'},
    {carat: 'VVS1',
      wholesome: '$16,315',
      retail: '$21,692'},
    {carat: 'IF',
      wholesome: '$18,770',
      retail: '$24,956'},
    {carat: 'FL',
      wholesome: '$23,150',
      retail: '$30,779'},
  ];

  diamondPriceData02E: DiamondPrice02E[] = [
    {carat: 'SI1',
      wholesome: '$10,630',
      retail: '$12,711'},
    {carat: 'VS2',
      wholesome: '$11,405',
      retail: '$14,827'},
    {carat: 'VS1',
      wholesome: '$12,920',
      retail: '$16,796'},
    {carat: 'VVS2',
      wholesome: '$13,493',
      retail: '$17,541'},
    {carat: 'VVS1',
      wholesome: '$15,970',
      retail: '$20,761'},
    {carat: 'IF',
      wholesome: '$16,686',
      retail: '$21,692'},
    {carat: 'FL',
      wholesome: '$19,189',
      retail: '$24,945'},
  ];

  diamondPriceData02F: DiamondPrice02F[] = [
    {carat: 'SI1',
      wholesome: '$9,689',
      retail: '$12,110'},
    {carat: 'VS2',
      wholesome: '$11,048',
      retail: '$14,363'},
    {carat: 'VS1',
      wholesome: '$12,717',
      retail: '$16,532'},
    {carat: 'VVS2',
      wholesome: '$13,154',
      retail: '$17,101'},
    {carat: 'VVS1',
      wholesome: '$13,608',
      retail: '$17,690'},
    {carat: 'IF',
      wholesome: '$13,770',
      retail: '$17,901'},
    {carat: 'FL',
      wholesome: '$15,492',
      retail: '$20,138'},
  ];

  diamondPriceData02G: DiamondPrice02G[] = [
    {carat: 'SI1',
      wholesome: '$9,410',
      retail: '$11,601'},
    {carat: 'VS2',
      wholesome: '$10,356',
      retail: '$13,568'},
    {carat: 'VS1',
      wholesome: '$11,077',
      retail: '$14,512'},
    {carat: 'VVS2',
      wholesome: '$11,484',
      retail: '$15,044'},
    {carat: 'VVS1',
      wholesome: '$12,328',
      retail: '$16,150'},
    {carat: 'IF',
      wholesome: '$12,375',
      retail: '$16,212'},
    {carat: 'FL',
      wholesome: '$13,406',
      retail: '$17,563'},
  ];

  diamondPriceData02H: DiamondPrice02H[] = [
    {carat: 'SI1',
      wholesome: '$7,493',
      retail: '$9,409'},
    {carat: 'VS2',
      wholesome: '$7,967',
      retail: '$10,517'},
    {carat: 'VS1',
      wholesome: '$8,327',
      retail: '$10,992'},
    {carat: 'VVS2',
      wholesome: '$8,622',
      retail: '$11,381'},
    {carat: 'VVS1',
      wholesome: '$8,714',
      retail: '$11,503'},
    {carat: 'IF',
      wholesome: '$9,576',
      retail: '$12,641'},
    {carat: 'FL',
      wholesome: '$10,373',
      retail: '$13,694'},
  ];

  diamondPriceData03D: DiamondPrice03D[] = [
    {carat: 'SI1',
      wholesome: '$19,390',
      retail: '$23,718'},
    {carat: 'VS2',
      wholesome: '$21,669',
      retail: '$26,003'},
    {carat: 'VS1',
      wholesome: '$23,531',
      retail: '$28,238'},
    {carat: 'VVS2',
      wholesome: '$27,968',
      retail: '$33,561'},
    {carat: 'VVS1',
      wholesome: '$30,499',
      retail: '$36,599'},
    {carat: 'IF',
      wholesome: '$33,696',
      retail: '$40,435'},
    {carat: 'FL',
      wholesome: '$44,928',
      retail: '$53,914'},
  ];

  diamondPriceData03E: DiamondPrice03E[] = [
    {carat: 'SI1',
      wholesome: '$18,797',
      retail: '$22,903'},
    {carat: 'VS2',
      wholesome: '$22,248',
      retail: '$26,698'},
    {carat: 'VS1',
      wholesome: '$23,328',
      retail: '$27,994'},
    {carat: 'VVS2',
      wholesome: '$25,920',
      retail: '$31,104'},
    {carat: 'VVS1',
      wholesome: '$29,828',
      retail: '$35,793'},
    {carat: 'IF',
      wholesome: '$32,465',
      retail: '$38,957'},
    {carat: 'FL',
      wholesome: '$40,581',
      retail: '$48,697'},
  ];

  diamondPriceData03F: DiamondPrice03F[] = [
    {carat: 'SI1',
      wholesome: '$17,231',
      retail: '$21,470'},
    {carat: 'VS2',
      wholesome: '$20,974',
      retail: '$25,169'},
    {carat: 'VS1',
      wholesome: '$22,356',
      retail: '$26,827'},
    {carat: 'VVS2',
      wholesome: '$24,926',
      retail: '$29,912'},
    {carat: 'VVS1',
      wholesome: '$27,238',
      retail: '$32,685'},
    {carat: 'IF',
      wholesome: '$28,512',
      retail: '$34,214'},
    {carat: 'FL',
      wholesome: '$33,264',
      retail: '$39,917'},
  ];

  diamondPriceData03G: DiamondPrice03G[] = [
    {carat: 'SI1',
      wholesome: '$16,889',
      retail: '$20,192'},
    {carat: 'VS2',
      wholesome: '$19,006',
      retail: '$22,997'},
    {carat: 'VS1',
      wholesome: '$20,060',
      retail: '$24,272'},
    {carat: 'VVS2',
      wholesome: '$21,816',
      retail: '$26,397'},
    {carat: 'VVS1',
      wholesome: '$22,702',
      retail: '$27,469'},
    {carat: 'IF',
      wholesome: '$24,192',
      retail: '$29,273'},
    {carat: 'FL',
      wholesome: '$27,216',
      retail: '$32,931'},
  ];

  diamondPriceData03H: DiamondPrice03H[] = [
    {carat: 'SI1',
      wholesome: '$15,750',
      retail: '$19,019'},
    {carat: 'VS2',
      wholesome: '$16,632',
      retail: '$20,624'},
    {carat: 'VS1',
      wholesome: '$17,690',
      retail: '$21,936'},
    {carat: 'VVS2',
      wholesome: '$19,418',
      retail: '$24,079'},
    {carat: 'VVS1',
      wholesome: '$20,691',
      retail: '$25,656'},
    {carat: 'IF',
      wholesome: '$20,822',
      retail: '$25,820'},
    {carat: 'FL',
      wholesome: '$22,905',
      retail: '$28,402'},
  ];

  diamondPriceData04D: DiamondPrice04D[] = [
    {carat: 'SI1',
      wholesome: '$47,889',
      retail: '$55,074'},
    {carat: 'VS2',
      wholesome: '$53,581',
      retail: '$61,619'},
    {carat: 'VS1',
      wholesome: '$64,027',
      retail: '$73,631'},
    {carat: 'VVS2',
      wholesome: '$82,880',
      retail: '$95,312'},
    {carat: 'VVS1',
      wholesome: '$94,012',
      retail: '$108,113'},
    {carat: 'IF',
      wholesome: '$103,383',
      retail: '$118,890'},
    {carat: 'FL',
      wholesome: '$137,804',
      retail: '$158,474'},
  ];

  diamondPriceData04E: DiamondPrice04E[] = [
    {carat: 'SI1',
      wholesome: '$45,660',
      retail: '$53,422'},
    {carat: 'VS2',
      wholesome: '$53,361',
      retail: '$62,432'},
    {carat: 'VS1',
      wholesome: '$56,347',
      retail: '$65,926'},
    {carat: 'VVS2',
      wholesome: '$78,339',
      retail: '$91,658'},
    {carat: 'VVS1',
      wholesome: '$85,370',
      retail: '$99,884'},
    {carat: 'IF',
      wholesome: '$96,519',
      retail: '$112,928'},
    {carat: 'FL',
      wholesome: '$105,987',
      retail: '$124,005'},
  ];

  diamondPriceData04F: DiamondPrice04F[] = [
    {carat: 'SI1',
      wholesome: '$43,889',
      retail: '$51,350'},
    {carat: 'VS2',
      wholesome: '$59,624',
      retail: '$69,761'},
    {carat: 'VS1',
      wholesome: '$64,911',
      retail: '$75,946'},
    {carat: 'VVS2',
      wholesome: '$73,669',
      retail: '$86,193'},
    {carat: 'VVS1',
      wholesome: '$83,030',
      retail: '$97,146'},
    {carat: 'IF',
      wholesome: '$91,079',
      retail: '$106,562'},
    {carat: 'FL',
      wholesome: '$95,591',
      retail: '$111,842'},
  ];

  diamondPriceData04G: DiamondPrice04G[] = [
    {carat: 'SI1',
      wholesome: '$43,491',
      retail: '$51,319'},
    {carat: 'VS2',
      wholesome: '$58,188',
      retail: '$68,662'},
    {carat: 'VS1',
      wholesome: '$60,599',
      retail: '$71,507'},
    {carat: 'VVS2',
      wholesome: '$64,616',
      retail: '$76,247'},
    {carat: 'VVS1',
      wholesome: '$68,601',
      retail: '$80,949'},
    {carat: 'IF',
      wholesome: '$73,317',
      retail: '$86,515'},
    {carat: 'FL',
      wholesome: '$76,373',
      retail: '$89,469'},
  ];

  diamondPriceData04H: DiamondPrice04H[] = [
    {carat: 'SI1',
      wholesome: '$34,150',
      retail: '$40,297'},
    {carat: 'VS2',
      wholesome: '$40,009',
      retail: '$47,211'},
    {carat: 'VS1',
      wholesome: '$45,198',
      retail: '$53,334'},
    {carat: 'VVS2',
      wholesome: '$47,207',
      retail: '$55,704'},
    {carat: 'VVS1',
      wholesome: '$48,579',
      retail: '$57,323'},
    {carat: 'IF',
      wholesome: '$52,564',
      retail: '$62,025'},
    {carat: 'FL',
      wholesome: '$53,903',
      retail: '$63,605'},
  ];


}
