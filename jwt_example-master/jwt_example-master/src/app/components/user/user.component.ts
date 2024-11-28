import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UsuarioService } from '../../services/usuario.service';

@Component({
    selector: 'app-user',
    templateUrl: './user.component.html'
})
export class UserComponent {
    registerForm: FormGroup;

    constructor(private fb: FormBuilder, private usuarioService: UsuarioService) {
        this.registerForm = this.fb.group({
            username: ['', [Validators.required]],
            password: ['', [Validators.required, Validators.minLength(6)]],
            email: ['', [Validators.required, Validators.email]]
        });
    }

    onRegister() {
        if (this.registerForm.valid) {
            this.usuarioService.registerUser(this.registerForm.value).subscribe({
                next: () => alert('Cadastro realizado com sucesso!'),
                error: err => console.error('Erro no cadastro:', err)
            });
        }
    }
}
