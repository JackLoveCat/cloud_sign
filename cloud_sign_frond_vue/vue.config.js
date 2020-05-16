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
          "^/apis": "", // rewrite path
        },
      },
    },
  },
  configureWebpack: (config) => {
    if (process.env.NODE_ENV === "production") {
      config.plugins.push(
        new TerserPlugin({
          terserOptions: {
            ecma: undefined,
            warnings: false,
            parse: {},
            compress: {
              drop_console: true,
              drop_debugger: false,
              pure_funcs: ["console.log"], // 移除console
            },
          },
        })
      );
    }
  },
};
