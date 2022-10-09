const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({

  outputDir: '../main/resources/static',
  indexPath: "../static/index.html"

  devServer: {
    port: 3000,
    proxy: {
      '/home': {
        targer: "http://localhost:8080",
        ws: true,
        changeOrigin: true
      }
    }
  },

  transpileDependencies: true
})
