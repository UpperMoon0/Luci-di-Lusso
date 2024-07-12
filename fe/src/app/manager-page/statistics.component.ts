import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';

import { CanvasJSAngularChartsModule } from '@canvasjs/angular-charts';

@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.css']
})
export class StatisticsComponent {

  title = 'Orders';
  orderChart = {
    title: {
      text: "Amount of Orders have made in 30 days"
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
        { x: 10, y: 71 },
        { x: 20, y: 55 },
        { x: 30, y: 50 },
        { x: 40, y: 65 },
        { x: 50, y: 71 },
        { x: 60, y: 92 },
        { x: 70, y: 68 },
        { x: 80, y: 38 },
        { x: 90, y: 54 },
        { x: 100, y: 60 }
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
        { x: 10, y: 71 },
        { x: 20, y: 55 },
        { x: 30, y: 50 },
        { x: 40, y: 65 },
        { x: 50, y: 71 },
        { x: 60, y: 92 },
        { x: 70, y: 68 },
        { x: 80, y: 38 },
        { x: 90, y: 54 },
        { x: 100, y: 60 }
      ]
    }]
  }

  accountChart = {
    title: {
      text: "Amount of Accounts have made in 30 days"
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
        { x: 10, y: 71 },
        { x: 20, y: 55 },
        { x: 30, y: 50 },
        { x: 40, y: 65 },
        { x: 50, y: 71 },
        { x: 60, y: 92 },
        { x: 70, y: 68 },
        { x: 80, y: 38 },
        { x: 90, y: 54 },
        { x: 100, y: 60 }
      ]
    }]
  }

}
