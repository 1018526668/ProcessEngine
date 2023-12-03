<!-- 右键菜单 -->
<template>
  <div class="right-click-menu" :style="{ left: menuLeftPos, top: menuTopPos, display: menuDisplay }">
    <div class="menu-item" @click="copyData"><span>复制</span></div>
    <div class="menu-item" style="border-bottom: none" @click="pastData"><span> 黏贴</span></div>
  </div>
</template>

<script setup lang="ts">
import { ref, watch, onMounted } from "vue"
import { useGlobalStore } from "@/store/index"
import { Editor } from "@tiptap/vue-3"
import { useClipboard } from "@vueuse/core"
const props = defineProps<{
  editor: Editor | undefined
}>()

const globalStore = useGlobalStore()
let menuLeftPos = ref("0px"),
  menuTopPos = ref("0px"),
  menuDisplay = ref("none")
let cursorTxt = ""

onMounted(() => {
  document.addEventListener("selectionchange", updateSelectedText)
})

watch(
  () => globalStore.menuInfo,
  (newVal, oldVal) => {
    menuDisplay.value = newVal.display || "none"
    menuTopPos.value = newVal.y || "0px"
    menuLeftPos.value = newVal.x || "0px"
  },
  { deep: true }
)

function copyData() {
  console.log("复制到粘贴板内容为", cursorTxt)
  const { text, copy, copied, isSupported } = useClipboard({})
  copy(cursorTxt)
}

function pastData() {
  props.editor?.commands.insertContent(cursorTxt)
}

function updateSelectedText() {
  const selection = window.getSelection()
  if (selection?.toString()) {
    cursorTxt = selection.toString()
  }
}
</script>

<style lang="scss" scoped>
.right-click-menu {
  position: fixed;
  padding: 5px;
  background: rgba(230, 230, 231, 1);
  border-radius: 5px;
  z-index: 999;
  border: 1px solid #bfbfbf;
  box-shadow: 0px 0px 3px #bfbfbf;

  .menu-item {
    cursor: pointer;
    min-width: 100px;
    border-radius: 5px;
    margin: 5px 15px;
    padding: 2px 0px;
    border-bottom: 1px solid #cccccd;

    span {
      margin-left: 5px;
    }

    &:hover {
      background: #59a2ff;
      color: white;
    }
  }
}
</style>
