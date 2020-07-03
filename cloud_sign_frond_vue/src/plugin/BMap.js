/* eslint-disable */
/*
 * @Author: Jack(yebin.xm@gmail.com)
 * @Date: 2020-07-03 22:45:54
 * @LastEditors: Jack(yebin.xm@gmail.com)
 * @LastEditTime: 2020-07-03 23:27:40
 */
export default {
    init: function () {
        //console.log("初始化百度地图脚本...");
        const AK = "y84GpWeyqqBkMvuuqRKPg1ctBLarzrdy";
        const bMap_URL = "https://api.map.baidu.com/api?v=3.0&ak=" + AK + "&s=1&callback=onBMapCallback";
        return new Promise((resolve, reject) => {
            // 如果已加载直接返回
            if (typeof BMap !== "undefined") {
                return resolve(BMap);
            }
            // 百度地图异步加载回调处理
            window.onBMapCallback = function () {
                // console.log("百度地图脚本初始化成功...");
                resolve(BMap);
            };

            // 插入script脚本
            let scriptNode = document.createElement("script");
            scriptNode.setAttribute("type", "text/javascript");
            scriptNode.setAttribute("src", bMap_URL);
            document.body.appendChild(scriptNode);
        });
    }
} 