import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { DatePipe } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { Ng2SearchPipeModule } from 'ng2-search-filter';

import { AppComponent } from './app.component';
import { SearchFormComponent } from './components/search-form/search-form.component';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { SearchComponent } from './components/search/search.component';
import { FlightDetailsComponent } from './components/search/flight-details/flight-details.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { HelpPageComponent } from './components/help-page/help-page.component';
import { MyTripsComponent } from './components/my-trips/my-trips.component';
import { ContactComponent } from './components/contact/contact.component';
import { LogInComponent } from './components/log-in/log-in.component';
import { SignUpComponent } from './components/sign-up/sign-up.component';
import { PopularDestinationsComponent } from './components/popular-destinations/popular-destinations.component';
import { HomepageExtraInfoComponent } from './components/homepage-extra-info/homepage-extra-info.component';
import { FooterComponent } from './components/footer/footer.component';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { PaymentComponent } from './components/payment/payment.component';
import { SuccessComponent } from './components/success/success.component';

@NgModule({
  declarations: [
    AppComponent,
    SearchFormComponent,
    SearchComponent,
    FlightDetailsComponent,
    HomePageComponent,
    HelpPageComponent,
    MyTripsComponent,
    ContactComponent,
    LogInComponent,
    SignUpComponent,
    PopularDestinationsComponent,
    HomepageExtraInfoComponent,
    FooterComponent,
    NavBarComponent,
    PaymentComponent,
    SuccessComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    CommonModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    Ng2SearchPipeModule,
  ],
  exports: [FooterComponent],
  providers: [DatePipe],
  bootstrap: [AppComponent],
})
export class AppModule {}
