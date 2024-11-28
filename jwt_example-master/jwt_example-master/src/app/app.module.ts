import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { ErrorInterceptor } from './interceptors/error.interceptor';

@NgModule({
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
  ],
})
export class AppModule {}
function NgModule(arg0: { providers: { provide: any; useClass: typeof ErrorInterceptor; multi: boolean; }[]; }): (target: typeof AppModule) => void | typeof AppModule {
  throw new Error('Function not implemented.');
}

