import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  email = '';
  password = '';
  errorMessage = '';

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  login(): void {
    this.authService.login(this.email, this.password)
    .subscribe({
      next: () => this.router.navigate(["/home"]),
      error: (error) => this.errorMessage = 'Credenciais invalidas. tente novamente'
    })
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

}
