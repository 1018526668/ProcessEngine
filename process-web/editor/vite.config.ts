import { defineConfig } from "vite"
import vue from "@vitejs/plugin-vue"
import { resolve } from "path"
import legacy from "@vitejs/plugin-legacy"

// https://vitejs.dev/config/
export default defineConfig({
  base: "/nocode/",
  plugins: [vue(), legacy({ targets: ["defaults"] })],
  resolve: {
    alias: {
      "@": resolve(__dirname, "src")
    }
  },
  server: {
    host: "0.0.0.0",
    port: 1234,
    open: "/nocode/" // 自动打开浏览器
  }
})
