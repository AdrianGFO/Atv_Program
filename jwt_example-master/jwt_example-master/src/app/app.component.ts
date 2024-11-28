import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { MenuComponent } from './components/menu/menu.component';


@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html'
})
export class UserListComponent implements OnInit {
  users: any[] = [];

  constructor(private usuarioService: UsuarioService) {}

  ngOnInit() {
      this.usuarioService.getAllUsers().subscribe({
          next: (data: any[]) => this.users = data,
          error: (err: any) => console.error('Erro ao listar usuários:', err)
      });
  }
}