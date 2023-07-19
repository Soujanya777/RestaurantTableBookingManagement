import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  user = { email: '', password: '' };
  loginError = '';

  constructor(private authService: AuthService, private router: Router) {
    this.authService.logout();
  }

  login(): void {
    if (this.user.email && this.user.password) {
      this.authService.login(this.user.email, this.user.password).subscribe(
        (response) => {
          if (response.id) {
            this.loginError = '';
            this.authService.storeToken(response.id);
            this.router.navigate(['/home']);
          } else {
            alert('Invalid email or password');
          }
        },
        (error) => {
          alert('Invalid email or password');
        }
      );
    } else {
      alert('Invalid email or password');
    }
  }
}
