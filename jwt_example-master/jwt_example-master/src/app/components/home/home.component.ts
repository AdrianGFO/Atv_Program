import { Component } from '@angular/core';
import { UsuarioService } from '../../services/usuario.service';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { MenuComponent } from '../menu/menu.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [MenuComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

  constructor(
    private usuarioService: UsuarioService, 
    public authService: AuthService,
    private router: Router
  ) {
    this.usuarioService.findAllusers()
    .forEach(user => {
      console.log(user);
    });
  }

}
