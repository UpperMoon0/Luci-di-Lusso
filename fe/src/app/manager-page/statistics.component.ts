import {Component, OnInit} from '@angular/core';
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

  title = 'Statistics';

  constructor(private statisticsService: StatisticsService) {
    this.getSaleStatistics();
    this.getJewelriesSaleStatistics();
    this.getCustomersCreationStatistics();
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

  public showSaleStatistics() {
    return {
      title: {
        text: "Monthly Sales"
      },
      animationEnabled: true,
      axisY: {
        includeZero: true,
        valueFormatString: '#0 $'
      },
      data: [{
        type: "column", //change type to bar, line, area, pie, etc
        //indexLabel: "{y}", //Shows y value on all Data Points
        indexLabelFontColor: "#5A5757",
        dataPoints: this.saleStatistics.map((y, index) => ({x: index + 1, y}))
      }]
    }
  }

  public showJewelriesSaleStatistics() {
    return {
      title: {
        text: "Total jewelries sold in 30 days"
      },
      animationEnabled: true,
      axisY: {
        includeZero: true,
        valueFormatString: '#0 Jewelries'
      },
      data: [{
        type: "column", //change type to bar, line, area, pie, etc
        //indexLabel: "{y}", //Shows y value on all Data Points
        indexLabelFontColor: "#5A5757",
        dataPoints: this.saledJewelriesStatistics.map((y, index) => ({x: index + 1, y}))
      }]
    }
  }

  public showCustomerCreationStatistics() {
    return {
      title: {
        text: "Total customer registered in 30 days"
      },
      animationEnabled: true,
      axisY: {
        includeZero: true,
        valueFormatString: '#0 Account'
      },
      data: [{
        type: "column", //change type to bar, line, area, pie, etc
        //indexLabel: "{y}", //Shows y value on all Data Points
        indexLabelFontColor: "#5A5757",
        dataPoints: this.customerCreationStatistics.map((y, index) => ({x: index + 1, y}))
      }]
    }
  }
}
