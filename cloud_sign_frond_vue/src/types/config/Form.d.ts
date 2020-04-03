/*
 * @Author: Jack(yebin.xm@gmail.com)
 * @Date: 2020-03-28 18:04:02
 * @LastEditors: Jack(yebin.xm@gmail.com)
 * @LastEditTime: 2020-03-28 18:34:30
 */

export interface FormItem {
  validate: Function;
  reset: Function;
}

export interface ValidateRules {
  require: boolean;
  type?: string;
  reg?: string;
  minLenth?: number;
  maxLength?: number;
}
