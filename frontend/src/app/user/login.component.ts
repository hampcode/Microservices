import { Component, OnInit } from '@angular/core';
import { User } from './user';
import swal from 'sweetalert2';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  title: string ='Iniciar Sesión';
  user: User;
  
  constructor(private authService: AuthService, private router: Router) {
    this.user = new User();
   }

  ngOnInit() {
    if (this.authService.isAuthenticated()) {
      swal.fire('Login', `Hola ${this.authService.user.userName} ya estás autenticado!`, 'info');
      this.router.navigate(['/products']);
    }
  }

  login(): void {
    console.log(this.user);
    if (this.user.userName == null || this.user.password == null) {
      swal.fire('Error Login', 'Username o password vacías!', 'error');
      return;
    }

    this.authService.login(this.user).subscribe(response => {
      console.log(response);

      this.authService.saveUser(response.access_token);
      this.authService.saveToken(response.access_token);

    
      let user = this.authService.user;
      this.router.navigate(['/products']);
      swal.fire('Login', `Hola ${user.userName}, has iniciado sesión con éxito!`, 'success');
    }, err => {
      if (err.status == 400) {
        swal.fire('Error Login', 'Usuario o clave incorrectas!', 'error');
      }
    }
    );

    
  }
}
