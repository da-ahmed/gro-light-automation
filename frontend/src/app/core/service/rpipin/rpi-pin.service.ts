import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { REQUEST_OPTIONS_DEFAULT } from '../request-options.default';
import { CrudService } from '../crud.service';
import { RPiPin } from '@app/shared/model/rpipin/rpi-pin.model';

@Injectable()
export class RPiPinService extends CrudService<RPiPin, number> {

  constructor(http: Http) {
    super('http://localhost:8080/api/rpipin', http, REQUEST_OPTIONS_DEFAULT);
  }

}
