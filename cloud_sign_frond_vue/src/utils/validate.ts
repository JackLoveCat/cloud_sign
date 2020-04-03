const REG_LIST = {
  IMG_CODE: /^[0-9a-zA-Z]{4}$/,
  SMS_CODE: /^\d{4}$/,
  MAIL_CODE: /^\d{4}$/,
  USER_NAME: /^[\w|\d]{4,16}$/,
  PASSWORD: /^[\w!@#$%^&*.]{6,16}$/,
  MOBILE: /^1[3|4|5|7|8]\d{9}$/,
  REALNAME: /^[\u4e00-\u9fa5|·]{2,16}$|^[a-zA-Z|\s]{2,20}$/,
  BANK_NAME: /^\d{10,19}$/,
  MONEY: /^([1-9]\d*|[0-9]\d*\.\d{1,2}|0)$/,
  ANSWER: /^\S+$/,
  MAIL: /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/
};
