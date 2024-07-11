import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';
import { UserService } from "../service/user.service";

@Component({
  selector: 'app-customer-profile-form',
  templateUrl: './customer-profile-form.component.html',
  styleUrls: ['./customer-profile-form.component.css']
})
export class CustomerProfileFormComponent implements OnInit, OnDestroy {
  profileForm = this.formBuilder.group({
    fullName: ['', Validators.required],
    phone: ['', Validators.required],
    dob: ['', Validators.required],
    address: ['', Validators.required],
    imageUrl: ['', Validators.required],
  });

  private profileSubscription: Subscription;

  constructor(private formBuilder: FormBuilder,
              private userService: UserService) {}

  ngOnInit(): void {
    this.profileSubscription = this.userService.getProfileData().subscribe(profileData => {
      if (profileData) {
        this.profileForm.setValue({
          fullName: profileData.fullName,
          phone: profileData.phone,
          dob: profileData.dob,
          address: profileData.address,
          imageUrl: profileData.imageUrl,
        }, {emitEvent: false});
      }
    });
  }

  ngOnDestroy(): void {
    this.profileSubscription.unsubscribe();
  }

  onSubmit(): void {
    if (this.profileForm.valid) {
      const formValue = {
        fullName: this.profileForm.value.fullName,
        phone: this.profileForm.value.phone,
        dob: new Date(this.profileForm.value.dob).toISOString().split('T')[0],
        address: this.profileForm.value.address,
        imageUrl: this.profileForm.value.imageUrl
      };
      this.userService.updateProfile(formValue).subscribe({
        next: () => console.log('Profile updated successfully'),
        error: error => console.log('Error updating profile:', error)
      });
    } else {
      console.log('Profile form is invalid');
    }
  }

  handleImageError(event) {
    event.target.src = 'assets/images/default_avatar.png';
  }
}
