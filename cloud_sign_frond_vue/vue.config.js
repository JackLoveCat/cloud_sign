const TerserPlugin = require("terser-webpack-plugin");
// vue.config.js
module.exports = {
  devServer: {
    port: 8080,
    proxy: {
      "/apis": {
        target: "http://123.206.49.117:8080/", // target host
        ws: true, // proxy websockets
        changeOrigin: true, // needed for virtual hosted sites
        pathRewrite: {
          "^/apis": "" // rewrite path
        }
      }
    }
  },
  publicPath: process.env.NODE_ENV === "production" ? "/mobile" : "/"
};
