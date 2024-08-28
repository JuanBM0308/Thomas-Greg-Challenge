import { Component, OnInit } from '@angular/core';
import { ReportService } from './report.service';

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.css']
})
export class ReportComponent implements OnInit  {
  report1: any[] = [];
  report2: any[] = [];
  report3: any[] = [];

  constructor(
    private reportService: ReportService,
  ) {
  }

  ngOnInit() {
    this.loadReports();
  }

  loadReports(): void {
    this.reportService.getActiveProducts().subscribe((res: any) => {
      this.report1 = res.response;
    });

    this.reportService.getReportProducts().subscribe((res: any) => {
      this.report2 = res.response;
    });

    this.reportService.getReportCustomers().subscribe((res: any) => {
      this.report3 = res.response;
    });
  }
}
