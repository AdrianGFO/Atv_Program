import { isPlatformBrowser } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Inject, Injectable, PLATFORM_ID } from '@angular/core';
import { BehaviorSubject, map, Observable } from 'rxjs';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8083/auth/login';
  private tokenSubject = new BehaviorSubject<string | null>(null);
  authService: any;
  router: any;

  constructor(
    private http: HttpClient,
    @Inject(PLATFORM_ID) private platformId: Object
  ) { 
    if(isPlatformBrowser(this.platformId)) {
      const token = localStorage.getItem('token');
      if(token) {
        this.tokenSubject.next(token);
      }
    }
  }

  login(email: string, password: string) : Observable<void> {
    return this.http.post<{ token: string }>(this.apiUrl, { email, password }).pipe(
      map((response: { token: string; }) => {
        if(isPlatformBrowser(this.platformId)) {
          localStorage.setItem("token", response.token);
        }
        this.tokenSubject.next(response.token);
      })
    )
  }

  getToken() : string | null {
    return this.tokenSubject.value
  }

  logout(): void {
    if(isPlatformBrowser(this.platformId)) {
      localStorage.removeItem("token");
    }

    this.tokenSubject.next(null);
  }

  isAuthenticated() : boolean {
    return !!this.tokenSubject.value;
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const currentUser = this.authService.currentUserValue;
    if (currentUser && currentUser.role === 'ADMIN') {
        return true; // Somente admins podem acessar
    }
    this.router.navigate(['/login']);
    return false;
  }
}
