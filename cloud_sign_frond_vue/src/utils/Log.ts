/*
 * @Author: Jack(yebin.xm@gmail.com)
 * @Date: 2020-05-06 22:39:33
 * @LastEditors: Jack(yebin.xm@gmail.com)
 * @LastEditTime: 2020-05-06 22:41:00
 */
let env = "pro"; //设置全局的console是否生效的条件,开发情况下允许console
function log(message?: any, ...optionalParams: any[]) {
  if (env === "dev") {
    console.log(message, optionalParams);
  }
}

export default log;
