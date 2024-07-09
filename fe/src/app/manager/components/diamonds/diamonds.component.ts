import {Component, OnInit} from '@angular/core';
import {ManagerService} from "../../services/manager.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Title} from "@angular/platform-browser";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-diamonds',
  templateUrl: './diamonds.component.html',
  styleUrls: ['./diamonds.component.css']
})
export class DiamondsComponent implements OnInit{

    diamonds: any[] = [];
    searchDiamondForm!: FormGroup;

    constructor(private managerService: ManagerService,
                private titleService: Title,
                private fb: FormBuilder,
                private snackBar: MatSnackBar,

) {}

    ngOnInit() {
      this.getDiamonds();
      this.titleService.setTitle('Diamond List | Luci di Lusso');
      this.searchDiamondForm = this.fb.group({
        title: [null,[Validators.minLength(1)]]
      });
      }


    getDiamonds(): void {
      this.diamonds = [];
      this.managerService.getAllDiamonds().subscribe(response => {
        this.diamonds = response;
      });
    }

    deleteDiamond(diamondId: any) {
      this.managerService.deleteDiamond(diamondId).subscribe(response => {
        if (response == null) {
          if(typeof window !== 'undefined') {
            localStorage.setItem('showSnackbar', 'true');
          }
          window.location.reload();
        } else {
          this.snackBar.open(response.message, 'Close', {
            duration: 5000,
            panelClass: 'error-snackbar'
          });
        }
      });
    }

  updateDiamond(diamondId: any): void {

  }
}

