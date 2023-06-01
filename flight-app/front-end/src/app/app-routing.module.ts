import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SearchComponent } from './components/search/search.component'
import { HomePageComponent } from './components/home-page/home-page.component';
import { HelpPageComponent } from './components/help-page/help-page.component';
import { MyTripsComponent } from './components/my-trips/my-trips.component';
import { ContactComponent } from './components/contact/contact.component';
import { LogInComponent } from './components/log-in/log-in.component';
import { SignUpComponent } from './components/sign-up/sign-up.component';
import { PaymentComponent } from './components/payment/payment.component';
import { SuccessComponent } from './components/success/success.component';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomePageComponent },
  { path: 'search', component: SearchComponent },
  { path: 'help', component: HelpPageComponent },
  { path: 'my-trips', component: MyTripsComponent },
  { path: 'contact', component: ContactComponent },
  { path: 'log-in', component: LogInComponent },
  { path: 'sign-up', component: SignUpComponent },
  { path: 'payment', component: PaymentComponent },
  { path: 'success', component: SuccessComponent },
  {
    path: 'admin',
    loadChildren: () =>
      import('./admin/admin.module').then((m) => m.AdminModule),
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
