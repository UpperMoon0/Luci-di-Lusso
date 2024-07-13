import {Component, OnInit} from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';

import { CanvasJSAngularChartsModule } from '@canvasjs/angular-charts';
import {StatisticsService} from "../service/statistics.service";

@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.css']
})
export class StatisticsComponent implements OnInit {

  saleStatistics: number[] = [];
  saledJewelriesStatistics: number[] = [];
  customerCreationStatistics: number[] = [];

  title = 'Orders';
  orderChart = {
    title: {
      text: "Total jewelries sold in 30 days"
    },
    animationEnabled: true,
    axisY: {
      includeZero: true
    },
    data: [{
      type: "column", //change type to bar, line, area, pie, etc
      //indexLabel: "{y}", //Shows y value on all Data Points
      indexLabelFontColor: "#5A5757",
      dataPoints: [
        { x: 1, y: this.saleStatistics[0] },
        { x: 2, y: this.saleStatistics[1] },
        { x: 3, y: this.saleStatistics[2] },
        { x: 4, y: this.saleStatistics[3] },
        { x: 5, y: this.saleStatistics[4] },
        { x: 6, y: this.saleStatistics[5] },
        { x: 7, y: this.saleStatistics[6] },
        { x: 8, y: this.saleStatistics[7] },
        { x: 9, y: this.saleStatistics[8] },
        { x: 10, y: this.saleStatistics[9] },
        { x: 11, y: this.saleStatistics[10] },
        { x: 12, y: this.saleStatistics[11] },
        { x: 13, y: this.saleStatistics[12] },
        { x: 14, y: this.saleStatistics[13] },
        { x: 15, y: this.saleStatistics[14] },
        { x: 16, y: this.saleStatistics[15] },
        { x: 17, y: this.saleStatistics[16] },
        { x: 18, y: this.saleStatistics[17] },
        { x: 19, y: this.saleStatistics[18] },
        { x: 20, y: this.saleStatistics[19] },
        { x: 21, y: this.saleStatistics[20] },
        { x: 22, y: this.saleStatistics[21] },
        { x: 23, y: this.saleStatistics[22] },
        { x: 24, y: this.saleStatistics[23] },
        { x: 25, y: this.saleStatistics[24] },
        { x: 26, y: this.saleStatistics[25] },
        { x: 27, y: this.saleStatistics[26] },
        { x: 28, y: this.saleStatistics[27] },
        { x: 29, y: this.saleStatistics[28] },
        { x: 30, y: this.saleStatistics[29] }
      ]
    }]
  }

  profitChart = {
    title: {
      text: "Monthly Sales"
    },
    animationEnabled: true,
    axisY: {
      includeZero: true
    },
    data: [{
      type: "column", //change type to bar, line, area, pie, etc
      //indexLabel: "{y}", //Shows y value on all Data Points
      indexLabelFontColor: "#5A5757",
      dataPoints: [
        { x: 1, y: this.saledJewelriesStatistics[0] },
        { x: 2, y: this.saledJewelriesStatistics[1] },
        { x: 3, y: this.saledJewelriesStatistics[2] },
        { x: 4, y: this.saledJewelriesStatistics[3] },
        { x: 5, y: this.saledJewelriesStatistics[4] },
        { x: 6, y: this.saledJewelriesStatistics[5] },
        { x: 7, y: this.saledJewelriesStatistics[6] },
        { x: 8, y: this.saledJewelriesStatistics[7] },
        { x: 9, y: this.saledJewelriesStatistics[8] },
        { x: 10, y: this.saledJewelriesStatistics[9] },
        { x: 11, y: this.saledJewelriesStatistics[10] },
        { x: 12, y: this.saledJewelriesStatistics[11] },
        { x: 13, y: this.saledJewelriesStatistics[12] },
        { x: 14, y: this.saledJewelriesStatistics[13] },
        { x: 15, y: this.saledJewelriesStatistics[14] },
        { x: 16, y: this.saledJewelriesStatistics[15] },
        { x: 17, y: this.saledJewelriesStatistics[16] },
        { x: 18, y: this.saledJewelriesStatistics[17] },
        { x: 19, y: this.saledJewelriesStatistics[18] },
        { x: 20, y: this.saledJewelriesStatistics[19] },
        { x: 21, y: this.saledJewelriesStatistics[20] },
        { x: 22, y: this.saledJewelriesStatistics[21] },
        { x: 23, y: this.saledJewelriesStatistics[22] },
        { x: 24, y: this.saledJewelriesStatistics[23] },
        { x: 25, y: this.saledJewelriesStatistics[24] },
        { x: 26, y: this.saledJewelriesStatistics[25] },
        { x: 27, y: this.saledJewelriesStatistics[26] },
        { x: 28, y: this.saledJewelriesStatistics[27] },
        { x: 29, y: this.saledJewelriesStatistics[28] },
        { x: 30, y: this.saledJewelriesStatistics[29] }
      ]
    }]
  }

  accountChart = {
    title: {
      text: "Total customer registered in 30 days"
    },
    animationEnabled: true,
    axisY: {
      includeZero: true
    },
    data: [{
      type: "column", //change type to bar, line, area, pie, etc
      //indexLabel: "{y}", //Shows y value on all Data Points
      indexLabelFontColor: "#5A5757",
      dataPoints: [
        { x: 1, y: this.customerCreationStatistics[0] },
        { x: 2, y: this.customerCreationStatistics[1] },
        { x: 3, y: this.customerCreationStatistics[2] },
        { x: 4, y: this.customerCreationStatistics[3] },
        { x: 5, y: this.customerCreationStatistics[4] },
        { x: 6, y: this.customerCreationStatistics[5] },
        { x: 7, y: this.customerCreationStatistics[6] },
        { x: 8, y: this.customerCreationStatistics[7] },
        { x: 9, y: this.customerCreationStatistics[8] },
        { x: 10, y: this.customerCreationStatistics[9] },
        { x: 11, y: this.customerCreationStatistics[10] },
        { x: 12, y: this.customerCreationStatistics[11] },
        { x: 13, y: this.customerCreationStatistics[12] },
        { x: 14, y: this.customerCreationStatistics[13] },
        { x: 15, y: this.customerCreationStatistics[14] },
        { x: 16, y: this.customerCreationStatistics[15] },
        { x: 17, y: this.customerCreationStatistics[16] },
        { x: 18, y: this.customerCreationStatistics[17] },
        { x: 19, y: this.customerCreationStatistics[18] },
        { x: 20, y: this.customerCreationStatistics[29] },
        { x: 21, y: this.customerCreationStatistics[20] },
        { x: 22, y: this.customerCreationStatistics[21] },
        { x: 23, y: this.customerCreationStatistics[22] },
        { x: 24, y: this.customerCreationStatistics[23] },
        { x: 25, y: this.customerCreationStatistics[24] },
        { x: 26, y: this.customerCreationStatistics[25] },
        { x: 27, y: this.customerCreationStatistics[26] },
        { x: 28, y: this.customerCreationStatistics[27] },
        { x: 29, y: this.customerCreationStatistics[28] },
        { x: 30, y: this.customerCreationStatistics[29] }
      ]
    }]
  }

  constructor(private statisticsService: StatisticsService) {
  }

  public ngOnInit() {
    this.getSaleStatistics();
    this.getJewelriesSaleStatistics();
    this.getCustomersCreationStatistics();
  }

  private getSaleStatistics() {
    this.statisticsService.getSaleStatistics().subscribe(response => {
      this.saleStatistics = response.statistics;
    });
  }

  private getJewelriesSaleStatistics() {
    this.statisticsService.getJewelriesSaleStatistics().subscribe(response => {
      this.saledJewelriesStatistics = response.statistics;
    });
  }

  private getCustomersCreationStatistics() {
    this.statisticsService.getCustomersCreationStatistics().subscribe(response => {
      this.customerCreationStatistics = response.statistics;
    });
  }

}
