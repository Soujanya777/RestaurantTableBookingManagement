import { Component } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { AuthService } from '../auth.service';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent {
  restaurants: any[] = [];
  selectedRestaurant: any;
  numOfPersons: number = 0;
  modalReference: any;
  bookingDate: any;
  closeResult = '';
  constructor(
    private http: HttpClient,
    private authService: AuthService,
    private modalService: NgbModal
  ) {
    this.http.get<any[]>('api/restaurant').subscribe((data) => {
      this.restaurants = data;
    });
  }

  open(content: any, restaurant: any) {
    this.selectedRestaurant = restaurant;
    this.modalReference = this.modalService.open(content, {
      ariaLabelledBy: 'modal-basic-title',
    });
    this.modalReference.result.then(
      (result: any) => {
        this.closeResult = `Closed with: ${result}`;
      },
      (reason: any) => {
        this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
      }
    );
  }

  submitBooking(): void {
    if (this.numOfPersons) {
      const userId = Number(this.authService.getId());
      const restaurantId = this.selectedRestaurant.id;
      const totalPersons = this.numOfPersons;

      const params = new HttpParams()
        .set('userId', userId)
        .set('restaurantId', restaurantId.toString())
        .set('bookingDate', this.bookingDate)
        .set('totalPersons', totalPersons.toString());

      this.http.post<any>('api/booking/book', {}, { params }).subscribe(
        (response) => {
          alert('Your booking is successful!');
        },
        (error) => {
          alert('Seats not available. Please try again later.');
        }
      );
    } else {
      alert('Please enter the number of persons.');
    }
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }
}
