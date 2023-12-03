import { createApp } from "vue"
import App from "./App.vue"
import ElementPlus from "element-plus"
import "remixicon/fonts/remixicon.css"
import { onKeyStroke } from "@vueuse/core"
import "element-plus/dist/index.css"
import "./index.less"
import { createPinia } from 'pinia'


onKeyStroke("Tab", e => {
  e.preventDefault()
})

const app = createApp(App)
app.use(createPinia())
app.use(ElementPlus)
app.mount("#app")
