import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [RouterModule, CommonModule],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css'
})
export class MenuComponent {

  constructor(
    public authService: AuthService,
    private router: Router
  ){
    console.log(this.autenticado())
    this.autenticado()
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  navigateTo(rota: string) {
    this.router.navigate([rota]);
  }

  autenticado(){
    return this.authService.isAuthenticated();
  }

}
