const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  outputDir: '../main/resources/static',

  devServer: {
    port: 3000,
    proxy: {
      '/': {
        target: "http://localhost:8080",
        ws: true,
        changeOrigin: true
      }
    }
  },

  transpileDependencies: true,

  pluginOptions: {
    vuetify: {
			// https://github.com/vuetifyjs/vuetify-loader/tree/next/packages/vuetify-loader
		}
  }
})
