import Mock from 'mockjs'

// 生成数据列表
var dataList = []
for (let i = 0; i < Math.floor(Math.random() * 10 + 1); i++) {
  dataList.push(Mock.mock({
    'userId': '@increment',
    'name': '管理员',
    'email': '@email',
    'mobile': /^1[0-9]{10}$/,
    'status': 1,
    'roleIdList': null,
    'createUserId': 1,
    'createTime': 'datetime',
    'userImgSrc': '/static/img/avatar.03cb40b.png'
  }))
}

// 获取用户信息
export function info () {
  console.log(dataList[0])
  return {
    // isOpen: false,
    url: 'role/getRole.do',
    type: 'get',
    data: {
      'msg': 'success',
      'status': 200,
      'role': dataList[0]
    }
  }
}

// 修改密码
export function updatePassword () {
  return {
    // isOpen: false,
    url: '/sys/user/password',
    type: 'post',
    data: {
      'msg': 'success',
      'code': 0
    }
  }
}

// 添加用户
export function add () {
  return {
    // isOpen: false,
    url: '/sys/user/save',
    type: 'post',
    data: {
      'msg': 'success',
      'code': 0
    }
  }
}

// 修改用户
export function update () {
  return {
    // isOpen: false,
    url: '/sys/user/update',
    type: 'post',
    data: {
      'msg': 'success',
      'code': 0
    }
  }
}

// 删除用户
export function del () {
  return {
    // isOpen: false,
    url: '/sys/user/delete',
    type: 'post',
    data: {
      'msg': 'success',
      'code': 0
    }
  }
}

export function getClass () {
  return {
    url: '/student/queryJoinedCourses.do',
    type: 'get',
    data: {
      'joinedCourses': [
        {
          'cId': 11112,
          'cName': '工程实践',
          'scName': '计算机学院',
          'tName': 'hmm',
          'cImgSrc': `/static/img/avatar.03cb40b.png`,
          'tImgSrc': `/static/img/avatar.03cb40b.png`
        },
        {
          'cId': 11112,
          'cName': '工程实践',
          'scName': '计算机学院',
          'tName': 'hmm',
          'cImgSrc': `/static/img/avatar.03cb40b.png`,
          'tImgSrc': `/static/img/avatar.03cb40b.png`
        },
        {
          'cId': 11112,
          'cName': '工程实践',
          'scName': '计算机学院',
          'tName': 'hmm',
          'cImgSrc': `/static/img/avatar.03cb40b.png`,
          'tImgSrc': `/static/img/avatar.03cb40b.png`
        },
        {
          'cId': 11112,
          'cName': '工程实践',
          'scName': '计算机学院',
          'tName': 'hmm',
          'cImgSrc': `/static/img/avatar.03cb40b.png`,
          'tImgSrc': `/static/img/avatar.03cb40b.png`
        },
        {
          'cId': 11112,
          'cName': '工程实践',
          'scName': '计算机学院',
          'tName': 'hmm',
          'cImgSrc': `/static/img/avatar.03cb40b.png`,
          'tImgSrc': `/static/img/avatar.03cb40b.png`
        },
        {
          'cId': 11112,
          'cName': '工程实践',
          'scName': '计算机学院',
          'tName': 'hmm',
          'cImgSrc': `/static/img/avatar.03cb40b.png`,
          'tImgSrc': `/static/img/avatar.03cb40b.png`
        },
        {
          'cId': 11112,
          'cName': '工程实践',
          'scName': '计算机学院',
          'tName': 'hmm',
          'cImgSrc': `/static/img/avatar.03cb40b.png`,
          'tImgSrc': `/static/img/avatar.03cb40b.png`
        },
        {
          'cId': 11112,
          'cName': '工程实践',
          'scName': '计算机学院',
          'tName': 'hmm',
          'cImgSrc': `/static/img/avatar.03cb40b.png`,
          'tImgSrc': `/static/img/avatar.03cb40b.png`
        }

      ],
      'status': 200
    }
  }
}

export function getSchoolList () {
  return {
    url: '/student/getSchoolList.do',
    type: 'get',
    data: {
      'joinedCourses': [
        {
          'cId': 11112,
          'cName': '福州大学',
          'scName': '计算机学院',
          'tName': 'hmm',
          'cImgSrc': `/static/img/avatar.03cb40b.png`,
          'tImgSrc': `/static/img/avatar.03cb40b.png`
        },
        {
          'cId': 11112,
          'cName': '福建师范大学',
          'scName': '计算机学院',
          'tName': 'hmm',
          'cImgSrc': `/static/img/avatar.03cb40b.png`,
          'tImgSrc': `/static/img/avatar.03cb40b.png`
        },
        {
          'cId': 11112,
          'cName': '江夏学院',
          'scName': '计算机学院',
          'tName': 'hmm',
          'cImgSrc': `/static/img/avatar.03cb40b.png`,
          'tImgSrc': `/static/img/avatar.03cb40b.png`
        },
        {
          'cId': 11112,
          'cName': '闽江学院',
          'scName': '计算机学院',
          'tName': 'hmm',
          'cImgSrc': `/static/img/avatar.03cb40b.png`,
          'tImgSrc': `/static/img/avatar.03cb40b.png`
        }
      ],
      'status': 200
    }
  }
}

// 获取验证码
export function getcaptcha () {
  return {
    url: '/role/checkCode',
    type: 'get',
    data: {
      'status': 200,
      'captchaPath': '111'
    }
  }
}

export function queryCourse () {
  return {
    url: '/student/queryCourse.do',
    type: 'post',
    data: {
      'course':
      [
        {
          'cName': '大学英语',
          'msgJoinAble': '是',
          'tName': '杨雷',
          'resultClassId': '123',
          'joinAble': true
        }
      ],
      'status': 200,
      'msg': 'success'
    }
  }
}

export function joinCourse () {
  return {
    url: '/student/joinCourse.do',
    type: 'post',
    data: {
      'status': 200
    }
  }
}

// 获取成员信息
export function getPartner () {
  return {
    url: '/course/queryStudents.do',
    type: 'post',
    data:
    {
      'students':
      [
        {
          'sImgSrc': '/static/img/avatar.03cb40b.png',
          'sName': 'hmm',
          'sId': '1706034111'
        },
        {
          'sImgSrc': '/static/img/avatar.03cb40b.png',
          'sName': 'hmm',
          'sId': '1706034106'
        },
        {
          'sImgSrc': '/static/img/avatar.03cb40b.png',
          'sName': 'hmm',
          'sId': '1706034108'
        },
        {
          'sImgSrc': '/static/img/avatar.03cb40b.png',
          'sName': 'hmm',
          'sId': '1706034111'
        },
        {
          'sImgSrc': '/static/img/avatar.03cb40b.png',
          'sName': 'hmm',
          'sId': '1706034106'
        },
        {
          'sImgSrc': '/static/img/avatar.03cb40b.png',
          'sName': 'hmm',
          'sId': '1706034108'
        },
        {
          'sImgSrc': '/static/img/avatar.03cb40b.png',
          'sName': 'hmm',
          'sId': '1706034111'
        },
        {
          'sImgSrc': '/static/img/avatar.03cb40b.png',
          'sName': 'hmm',
          'sId': '1706034108'
        },
        {
          'sImgSrc': '/static/img/avatar.03cb40b.png',
          'sName': 'hmm',
          'sId': '1706034111'
        },
        {
          'sImgSrc': '/static/img/avatar.03cb40b.png',
          'sName': 'hmm',
          'sId': '1706034106'
        },
        {
          'sImgSrc': '/static/img/avatar.03cb40b.png',
          'sName': 'hmm',
          'sId': '1706034108'
        },
        {
          'sImgSrc': '/static/img/avatar.03cb40b.png',
          'sName': 'hmm',
          'sId': '1706034111'
        },
        {
          'sImgSrc': '/static/img/avatar.03cb40b.png',
          'sName': 'hmm',
          'sId': '1706034106'
        },
        {
          'sImgSrc': '/static/img/avatar.03cb40b.png',
          'sName': 'hmm',
          'sId': '1706034108'
        },
        {
          'sImgSrc': '/static/img/avatar.03cb40b.png',
          'sName': 'hmm',
          'sId': '1706034111'
        },
        {
          'sImgSrc': '/static/img/avatar.03cb40b.png',
          'sName': 'hmm',
          'sId': '1706034106'
        },
        {
          'sImgSrc': '/static/img/avatar.03cb40b.png',
          'sName': 'hmm',
          'sId': '1706034108'
        },
        {
          'sImgSrc': '/static/img/avatar.03cb40b.png',
          'sName': 'hmm',
          'sId': '1706034111'
        },
        {
          'sImgSrc': '/static/img/avatar.03cb40b.png',
          'sName': 'hmm',
          'sId': '1706034108'
        },
        {
          'sImgSrc': '/static/img/avatar.03cb40b.png',
          'sName': 'hmm',
          'sId': '1706034111'
        },
        {
          'sImgSrc': '/static/img/avatar.03cb40b.png',
          'sName': 'hmm',
          'sId': '1706034106'
        },
        {
          'sImgSrc': '/static/img/avatar.03cb40b.png',
          'sName': 'hmm',
          'sId': '1706034108'
        }, {
          'sImgSrc': '/static/img/avatar.03cb40b.png',
          'sName': 'hmm',
          'sId': '1706034111'
        },
        {
          'sImgSrc': '/static/img/avatar.03cb40b.png',
          'sName': 'hmm',
          'sId': '1706034106'
        },
        {
          'sImgSrc': '/static/img/avatar.03cb40b.png',
          'sName': 'hmm',
          'sId': '1706034108'
        },
        {
          'sImgSrc': '/static/img/avatar.03cb40b.png',
          'sName': 'hmm',
          'sId': '1706034111'
        },
        {
          'sImgSrc': '/static/img/avatar.03cb40b.png',
          'sName': 'hmm',
          'sId': '1706034106'
        },
        {
          'sImgSrc': '/static/img/avatar.03cb40b.png',
          'sName': 'hmm',
          'sId': '1706034108'
        },
        {
          'sImgSrc': '/static/img/avatar.03cb40b.png',
          'sName': 'hmm',
          'sId': '1706034111'
        },
        {
          'sImgSrc': '/static/img/avatar.03cb40b.png',
          'sName': 'hmm',
          'sId': '1706034108'
        }

      ],
      'pageNum': 5,
      'status': 200
    }

  }
}
// 获取课堂详情
export function getClassInfo () {
  return {
    url: '/course/details.do',
    type: 'post',
    data:
    {
      'cImgSrc': '/static/img/avatar.03cb40b.png',
      'cName': '工程实践',
      'cId': 'EK2305',
      'status': 200,
      'pubWNum': 3,
      'stusNum': 32,
      'pubENum': '1'
    }
  }
}

// 获取指定课堂号的活动列表

export function getActivity () {
  return {
    url: '/student/selectPw.dotime',
    type: 'post',
    data:
    {
      'countall': '5',
      'countprocess': '3',
      'countover': '2',
      'max': 3,
      'status': 200,
      'publishWorks': [
        {
          'activityImgSrc': '/static/img/avatar.03cb40b.png',
          'pwName': '测试1',
          'pwEnd': '2019-3-10',
          'wStringState': '未参加',
          'wBooleanState': false,
          'pwStringState': '进行中',
          'pwBooleanState': true,
          'pwId': '1111111',
          'finishedNum': 30,
          'pwScore': 100,
          'wScore': 95
        },
        {
          'activityImgSrc': '/static/img/avatar.03cb40b.png',
          'pwName': '测试2',
          'pwEnd': '2019-3-10',
          'wStringState': '已参加',
          'wBooleanState': true,
          'pwStringState': '进行中',
          'pwBooleanState': true,
          'pwId': '22222222',
          'finishedNum': 20,
          'pwScore': 80,
          'wScore': 77
        },
        {
          'activityImgSrc': '/static/img/avatar.03cb40b.png',
          'pwName': '测试4',
          'pwEnd': '2019-3-12',
          'wStringState': '已参加',
          'wBooleanState': true,
          'pwStringState': '进行中',
          'pwBooleanState': true,
          'pwId': '4444444',
          'finishedNum': 25,
          'pwScore': 100,
          'wScore': 90
        },
        {
          'activityImgSrc': '/static/img/avatar.03cb40b.png',
          'pwName': '测试3',
          'pwEnd': '2019-3-1',
          'wStringState': '已参加',
          'wBooleanState': true,
          'pwStringState': '已结束',
          'pwBooleanState': false,
          'pwId': '33333333',
          'finishedNum': 27,
          'pwScore': 95,
          'wScore': 95
        },
        {
          'activityImgSrc': '/static/img/avatar.03cb40b.png',
          'pwName': '测试6',
          'pwEnd': '2019-2-10',
          'wStringState': '未参加',
          'wBooleanState': false,
          'pwStringState': '已结束',
          'pwBooleanState': false,
          'pwId': '66666',
          'finishedNum': 10,
          'pwScore': 88,
          'wScore': 75
        }
      ]
    }
  }
}
// 获取活动建议
export function getAdvanice () {
  return {
    url: '/student/fuzzySearchWork.do',
    type: 'post',
    data:
    {
      'fuzzySearchWorks': [
        {
          'value': '1个人'
        },
        {
          'value': '1个好人'
        }
      ],
      'status': 200
    }
  }
}
// 获取活动建议后的数据
export function getAdvaniceDatea () {
  return {
    url: '/student/SearchPwByPwName.dotime',
    type: 'post',
    data:
    {
      'fuzzySearchWorks': [
        {
          'activityImgSrc': '/static/img/avatar.03cb40b.png',
          'pwName': '测试9',
          'pwEnd': '2019-2-10',
          'wStringState': '未参加',
          'wBooleanState': false,
          'pwStringState': '已结束',
          'pwBooleanState': false
        },
        {
          'activityImgSrc': '/static/img/avatar.03cb40b.png',
          'pwName': '测试10',
          'pwEnd': '2019-2-10',
          'wStringState': '未参加',
          'wBooleanState': false,
          'pwStringState': '已结束',
          'pwBooleanState': false
        }
      ],
      'status': 200
    }
  }
}
// 学生上传附件接口
export function studentUpload () {
  return {
    url: '/student/upload',
    type: 'post',
    data:
    {
      'fuzzySearchWorks': [
        {
          'value': '1个人'
        },
        {
          'value': '1个好人'
        }
      ],
      'status': 200
    }
  }
}
// 学生查看作业详情
export function getActivityInfo () {
  return {
    url: '/student/getPwDetails.do',
    type: 'post',
    data:
    {
      'studentFiles': [    // 学生上传附件集合
        {
          'name': '1.png',
          'url': '22222222',
          'sfId': 2,
          'state': true
        },
        {
          'name': '2.png',
          'url': '111111',
          'sfId': 1,
          'state': true
        }
      ],
      'studentFilesImages': [
        {
          'name': '2.png',
          'url': '/static/img/avatar.03cb40b.png',
          'sfId': 1
        },
        {
          'name': '2.png',
          'url': '/static/img/favicon.ico',
          'sfId': 1
        }
      ],
      'teacherFiles': [   // 老师上传作业发布表附件集合
        {
          'name': '1.png',
          'url': 'http://storage.xuetangx.com/public_assets/xuetangx/PDF/1.xls',
          'tfId': 5,
          'state': true
        },
        {
          'name': '2.png',
          'url': 'http://storage.xuetangx.com/public_assets/xuetangx/PDF/2.xls',
          'tfId': 6,
          'state': true
        },
        {
          'name': 'qasd阿升大三.excle',
          'url': 'ccccccc',
          'tfId': 7,
          'state': true
        }
      ],
      'teacherFilesImages': [
        {
          'name': '2.png',
          'url': '/static/img/avatar.03cb40b.png',
          'sfId': 1
        },
        {
          'name': '2.png',
          'url': '/static/img/favicon.ico',
          'sfId': 1
        }
      ],
      'publishWork': [
        {
          'pwScore': '100',   // 老师规定的该次的作业分数  老师设置分数区间[5,100]
          'pwName': '练习二',  // 发布作业名称
          'wRemark': '<ul class=" list-paddingleft-2" style="list-style-type: disc;"><li><p style="text-align: center;"><span style="text-decoration: underline;"><em><strong>做的不错</strong></em></span></p></li></ul>',  // 教师评语
          'sWState': true,
          'activityImgSrc': '/static/img/avatar.03cb40b.png',
          'wContent': '11111',  // 学生作答文字区
          'wProblem': '不会加法',  // 学生的队该次作业的提问
          'wScore': '90',   // 老师批改的分数
          'pwContent': "<p style='color: red;'>计算1-1</p>",  // 老师发布作业的  作业内容
          'pwEnd': '2019-3-10'
        }
      ],
      'status': 200
    }
  }
}
// 学生下载老师上传的附件
export function studentDownloadFile () {
  return {
    url: '/work/downloadFile',
    type: 'post',
    data:
    {
      'status': 200
    }
  }
}
// 学生删除自己上传的附件
export function studentDeleteFile () {
  return {
    url: '/work/deleteFiles',
    type: 'post',
    data:
    {
      'status': 200
    }
  }
}
// 学生提交作业
export function studentWorkOn () {
  return {
    url: '/student/workOn',
    type: 'post',
    data:
    {
      'status': 200
    }
  }
}
// 学生获取评价列表
export function getEvaluateList () {
  return {
    url: '/student/selectPE.do',
    type: 'post',
    data:
    {
      'publishEstimates': [
        {
          'cName': '本月评价',
          'epStartTime': '2020-03-19',
          'epId': '2',
          'activityImgSrc': '/static/img/avatar.03cb40b.png'
        },
        {
          'cName': '第一月评价',
          'epStartTime': '2020-03-19',
          'epId': '4',
          'activityImgSrc': '/static/img/avatar.03cb40b.png'
        },
        {
          'cName': '第二月评价',
          'epStartTime': '2020-03-19',
          'epId': '2',
          'activityImgSrc': '/static/img/avatar.03cb40b.png'
        },
        {
          'cName': '第三月评价',
          'epStartTime': '2020-03-19',
          'epId': '2',
          'activityImgSrc': '/static/img/avatar.03cb40b.png'
        },
        {
          'cName': '第四月评价',
          'epStartTime': '2020-03-19',
          'epId': '2',
          'activityImgSrc': '/static/img/avatar.03cb40b.png'
        }
      ],
      'status': 200,
      'max': 3
    }
  }
}
// 学生提交评价
export function studentReWorkOn () {
  return {
    url: '/student/remarkOn.do',
    type: 'post',
    data:
    {
      'status': 200
    }
  }
}
// 学生获取统计数据
export function studentStaWorkInfo () {
  return {
    url: '/student/staWorkInfo.do',
    type: 'post',
    data:
    {
      'submitMap': [{'name': '已提交', 'value': '4'}, {'name': '未提交', 'value': '3'}],
      'averScore': [{'value': '30.33', 'name': '我的平均分'}, {'value': '69.67', 'name': '仍需努力的分数'}],
      'listInt': [320, 332, 301, 334, 390, 330, 320, 320, 332, 301, 334, 390, 330, 320, 330, 320, 320, 332, 301, 334, 390, 330, 320, 320, 332, 301, 334, 390, 330, 320, 330, 320],
      'listString': ['周一', '周二', '周三', '周四', '周五', '周六', '周日', '周一', '周二', '周三', '周四', '周五', '周六', '周日', '周六', '周日', '周一', '周二', '周三', '周四', '周五', '周六', '周日', '周一', '周二', '周三', '周四', '周五', '周六', '周日', '周六', '周日'],
      'status': 200
    }
  }
}
