import {Component,} from '@angular/core';

import {SlideInterface} from "../types.interface";

@Component({
  selector: 'app-home-banner',
  templateUrl: './home-banner.component.html',
  styleUrls: ['./home-banner.component.css']
})
export class HomeBannerComponent{
  slides: SlideInterface[] = [
    { url: '../assets/images/luci-banner.png',
      title: ''},
    { url: '../assets/images/pink-banner.jpg',
      title: 'Top jewelry trend for 2024',
      link: 'top-jewelry-trend-for-2024'},
    { url: '../assets/images/blog-banner02.png',
      title: '',
      link: ''},
    { url: '../assets/images/price-banner.jpg',
      title: 'Diamond Prices Chart 2024',
      link: 'diamond-prices'},
    { url: '../assets/images/size-banner.jpg',
      title: 'Bracelet and Ring Size Guide',
      link: 'bracelet-and-ring-size-guide'},
    // Add more slides as needed
  ];



}
