import { Component, EventEmitter, Input, Output, ViewChild } from '@angular/core';
import { DatePipe, formatDate } from '@angular/common'
import { TimeService } from './service/time.service';
import { BsDatepickerDirective } from 'ngx-bootstrap/datepicker/bs-datepicker.component';
import { Data } from './model/data';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  title: String | null = 'app';
  currentDate: Date;
  angularDate: Date;
  utcTime: String;
  pipedDate: String | null;
  dateStr: String | null;
  date1: any | null;
  date2: String | null;
  date3: Date | String | null;
  date4: any | null;
  date5: any | null;
  date6: any | null;
  date7: any | null;


           myTime: Date ;
  @Input() myDate: Date;
  @Output() myDateChange = new EventEmitter<Date>();

  hstep = 1;
  mstep = 1;
  sstep = 1;
  timeService: TimeService;
  datepipe: DatePipe;

  @ViewChild('dp') datePicker: BsDatepickerDirective;

  constructor(timeService: TimeService, datepipe: DatePipe) {
    this.timeService = timeService;
    this.datepipe = datepipe;

    this.myTime = new Date();
    this.myTime.setMilliseconds(0);
    this.myDate = this.myTime;
    this.angularDate = this.myTime;
    this.utcTime = this.angularDate.toUTCString();
    this.pipedDate = datepipe.transform(this.angularDate, 'yyyy-MM-d H:mm:ssZZZZ');


    let data: Data = new Data();
    data.dateStr = this.myDate.toString();
    data.date = this.myDate;
    data.localTime = this.myDate.toString().split(' ')[4];
    data.localDate = datepipe.transform(this.myDate, 'yyyy-MM-dd');
    data.localDateTimeDt = this.myDate;
    data.localDateTimeTs = this.myDate;
    data.offsetDateTime = this.myDate;
    data.zonedDateTime = this.myDate;

    setInterval(() => {
      this.currentDate = new Date()
    }, 1000)

    //default constructor server call used jpa
    timeService.saveJpa(data).subscribe(
      (result) => { this.populateResult(result) },
      (error) => { this.populateError(error) },
      () => { console.log(this.myDate.toString().split(' ')[4]); });
  }


  ngAfterViewInit() {
    this.datePicker.bsValueChange.subscribe((date: Date) => {
      //console.log(date);
      date.setHours(this.myTime.getHours(), this.myTime.getMinutes(), this.myTime.getSeconds());
      date.setMilliseconds(0)
      this.myDate = date;
    });
  }

  timeChanged(): void {
    let date = new Date(this.myDate == null ? new Date() : this.myDate);
    date.setHours(this.myTime.getHours(), this.myTime.getMinutes(), this.myTime.getSeconds());
    date.setMilliseconds(0)
    this.myDate = date;
    this.myDateChange.emit(this.myDate);
  }

  //servercall for jpa
  saveJpa(): void {
    let data = this.createData()

    this.timeService.saveJpa(data).subscribe(
      (result) => { this.populateResult(result) },
      (error) => { this.populateError(error) },
      () => { console.log("saveJpa completed") });
  }

  //servercall for Spring jdbcTemplate
  saveJdbcTemplate(): void {
    let data = this.createData();
    this.timeService.saveJdbcTemplate(data).subscribe(
      (result) => { this.populateResult(result) },
      (error) => { this.populateError(error) },
      () => { console.log("saveJdbcTemplate completed") });
  }

    //servercall for Spring plan JDBC
  savejdbc(): void {
    let data = this.createData();
    this.timeService.saveJdbc(data).subscribe(
      (result) => { this.populateResult(result) },
      (error) => { this.populateError(error) },
      () => { console.log("savejdbc completed") });
  }

  private createData(): Data {
    let data: Data = new Data();

    this.angularDate = this.myTime;
    this.utcTime = this.angularDate.toISOString();
    this.pipedDate = this.datepipe.transform(this.myTime, 'full');

    data.dateStr = this.datepipe.transform(this.myDate, 'full');
    data.date = this.myDate;
    data.localTime = this.myDate.toString().split(' ')[4];
    data.localDate = this.datepipe.transform(this.myDate, 'yyyy-MM-dd');
    data.localDateTimeDt = this.myDate;
    data.localDateTimeTs = this.myDate;
    data.offsetDateTime = this.myDate;
    data.zonedDateTime = this.myDate;

    return data;
  }

  private populateResult(result: Data): void {
    this.dateStr = result.dateStr;
    this.date1 = result.date;
    this.date1 = this.datepipe.transform(result.localDateTimeDt, 'yyyy-MM-d, H:mm:ss zzzz');
    this.date2 = result.localTime;
    this.date3 = result.localDate;
    this.date4 = result.localDateTimeDt;
    this.date4 = this.datepipe.transform(result.localDateTimeDt, 'yyyy-MM-d, H:mm:ss zzzz');
    this.date5 = result.localDateTimeTs;
    this.date5 = this.datepipe.transform(result.localDateTimeTs, 'yyyy-MM-d, H:mm:ss zzzz');
    this.date6 = result.offsetDateTime;
    this.date6 = this.datepipe.transform(result.offsetDateTime, 'yyyy-MM-d, H:mm:ss zzzz');
    this.date7 = result.zonedDateTime;
    this.date7 = this.datepipe.transform(result.zonedDateTime, 'yyyy-MM-d, H:mm:ss zzzz');
  };

  private populateError(error: Error) {
    console.log(error.name)
    console.log(error.message)
    console.log(JSON.stringify(error))
  };

}
