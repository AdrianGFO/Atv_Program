import { Injectable } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
    constructor(private snackBar: MatSnackBar) {}

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(req).pipe(
            catchError((error: HttpErrorResponse) => {
                let errorMsg = 'Ocorreu um erro desconhecido.';
                
                if (error.error && error.error.message) {
                    errorMsg = error.error.message;  // Mensagem personalizada do back-end
                } else if (error.status === 401) {
                    errorMsg = 'Não autorizado. Por favor, faça login.';
                } else if (error.status === 403) {
                    errorMsg = 'Acesso negado. Permissão insuficiente.';
                }

                this.snackBar.open(errorMsg, 'Fechar', { duration: 3000 });
                return throwError(() => new Error(errorMsg));
            })
        );
    }
}
