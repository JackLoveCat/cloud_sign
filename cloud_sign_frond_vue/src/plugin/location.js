/* eslint-disable */
/*
 * @Author: Jack(yebin.xm@gmail.com)
 * @Date: 2020-07-03 22:55:54
 * @LastEditors: Jack(yebin.xm@gmail.com)
 * @LastEditTime: 2020-07-03 23:33:58
 */ 
/**
 * @description 定位
 */
export const location = {
    /**
    * @description IP | GPS 定位
    */
    getCurrentCityPosition(BMap) {
        return new Promise(resolve => {
            // GPS 定位
            const geolocation = new BMap.Geolocation()
            const geocoder = new BMap.Geocoder();
            geolocation.getCurrentPosition(function (browserResult) {
                if (this.getStatus() == 0) {
                    geocoder.getLocation(browserResult.point, function (result) {
                        resolve(result.addressComponents.city)
                    })
                } else {
                    // IP 定位
                    const local = new BMap.LocalCity()
                    local.get(ipResult => {
                        if (ipResult.name != '全国') {
                            resolve(ipResult.name)
                        }
                    })
                }
            })
        })
    },
    /**
     * @description 通过经纬度获取地址信息
     */
    geocodingReverse(BMap, coor) {
        return new Promise(resolve => {
            const point = new BMap.Point(coor.lng, coor.lat)
            const geoc = new BMap.Geocoder()

            geoc.getLocation(point, result => {
                resolve(result)
            })
        })
    },
    /**
    * @description 非百度坐标转换为百度坐标
    */
    coordinateTransformation(BMap, coor) {
        return new Promise(resolve => {
            const bMapPoint = new BMap.Point(coor.lng, coor.lat);
            const convertor = new BMap.Convertor();

            convertor.translate([bMapPoint], 1, 5, data => {
                if (data.status === 0) {
                    resolve(data.points[0])
                }
            })
        })

    }
}