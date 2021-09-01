import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Data } from '../model/data';

@Injectable()
export class TimeService {

  private usersUrl: string;
  private postJpaUrl: string;
  private postJdbcTemplateUrl: string;
  private postJdbcUrl: string;

  constructor(private http: HttpClient) {
    this.usersUrl = 'http://localhost:8080/get';
    this.postJpaUrl = 'http://localhost:8080/post-data1';
    this.postJdbcTemplateUrl = 'http://localhost:8080/post-data2';
    this.postJdbcUrl = 'http://localhost:8080/post-data3';
  }

  public find(): Observable<Data> {
    return this.http.get<Data>(this.usersUrl);
  }

  public saveJpa(data: Data) {
    this.showData(data);
    return this.http.post<Data>(this.postJpaUrl, data);
  }

  public saveJdbcTemplate(data: Data) {
    this.showData(data);
    return this.http.post<Data>(this.postJdbcTemplateUrl, data);
  }
  public saveJdbc(data: Data) {
    this.showData(data);
    return this.http.post<Data>(this.postJdbcUrl, data);
  }

  private showData(data: Data){
    console.log(data.dateStr);
    console.log(data.date);
    console.log(data.localTime);
    console.log(data.localDate);
    console.log(data.localDateTimeDt);
    console.log(data.localDateTimeTs);
    console.log(data.offsetDateTime);
    console.log(data.zonedDateTime);
  }

}
