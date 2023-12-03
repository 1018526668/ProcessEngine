<template>
  <div class="title-header">
    <span class="cal-title" id="cal-title"></span>
    <input
      type="text"
      class="title"
      :value="titleState.title"
      :style="{ width: `${titleState.width}px` }"
      @input="titleInput"
      @blur="titleBlur"
    />
  </div>
</template>

<script setup lang="ts">
import { onMounted, reactive } from "vue"

// 初始化标题 改变fontSize即可改变整体计算值
const defaultTitle = "无标题文档"
let initTitle = localStorage.getItem("title")
const titleState = reactive({
  title: initTitle || defaultTitle,
  width: 0
})
onMounted(() => {
  calWidth(titleState.title)
})
// 用隐藏的dom来获取当前input的长度
// 注意先调用该函数同步了titleState的width值以后再同步titleState里的title值，否则width获取的是上一次的值
function calWidth(title: string) {
  const dom = document.getElementById("cal-title") as HTMLElement
  dom.innerText = title
  titleState.width = dom.clientWidth
}
// 标题输入时改变长度
function titleInput(e: Event) {
  const target = e.target as HTMLInputElement
  const value = target.value
  calWidth(value)
  titleState.title = value
}
// 失焦时保存到localStorage
function titleBlur(e: Event) {
  const target = e.target as HTMLInputElement
  let value = target.value
  if (!value) {
    value = defaultTitle
    calWidth(value)
    titleState.title = value
  }
  localStorage.setItem("title", value)
}
</script>

<style lang="less">
.title-header {
  height: 50px;
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  position: relative;

  .cal-title {
    position: absolute;
    z-index: 1;
    opacity: 0;
    font-size: 20px;
    width: fit-content;
    line-height: 1;
    padding: 5px;
    max-width: 794px;
  }

  .title {
    position: absolute;
    z-index: 2;
    line-height: 1;
    padding: 5px;
    border: none;
    background: none;
    outline: none;
    border-radius: 4px;
    font-size: 20px;
    text-align: center;
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
  }

  .title:hover,
  .title:focus,
  .title:active,
  .title:focus-visible {
    border: 1px solid rgba(0, 0, 0, 0.2);
  }
}
</style>
