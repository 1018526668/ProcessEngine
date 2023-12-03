<template>
  <el-button type="success" @click="htmlToPdf('ProseMirror', 'test.pdf')">输出PDF</el-button>
</template>

<script setup lang="ts">
import { jsPDF } from "jspdf"
import html2canvas from "html2canvas"

let glFileName: string = ""

/**
 * @param pageClassName 待转换html块的类名
 * @param fileName 输出文件名称（带后缀）
 */
function htmlToPdf(pageClassName: string, fileName: string) {
  glFileName = fileName
  //解决页面带滚动条的时候截图不全问题
  window.scrollY = 0
  document.documentElement.scrollTop = 0
  document.body.scrollTop = 0
  const canvas = document.createElement("canvas")
  const context = canvas.getContext("2d")
  //需要转换的html元素
  const _articleHtml = document.getElementsByClassName(pageClassName)[0]
  const _w = _articleHtml.clientWidth
  const _h = _articleHtml.clientHeight

  //解决生成的pdf不清晰的问题 先放大4倍----后面再缩小4倍
  const scale = 2
  canvas.width = _w * scale
  canvas.height = _h * scale
  context?.scale(scale, scale)

  const opts = {
    scale: 1,
    width: _w,
    height: _h,
    canvas: canvas,
    useCORS: true
  }
  html2canvas(_articleHtml as HTMLElement, opts).then(canvas => {
    createPdf(canvas, scale)
  })
}

// 图片->pdf
function createPdf(canvas: Record<string, any>, scale: number) {
  //-----------宽高缩小4倍---------------------
  const contentWidth = canvas.width / scale
  const contentHeight = canvas.height / scale
  //--------------------
  let orientation: "p" | "l" | undefined = "p"
  // 在 jspdf 源码里，如果是 orientation = 'p' 且 width > height 时， 会把 width 和height 值交换，
  // 类似于 把 orientation 的值修改为 'l' , 反之亦同。
  if (contentWidth > contentHeight) {
    orientation = "l"
  }

  const pdf = new jsPDF(orientation, "pt", [contentWidth, contentHeight])
  const pageData = canvas.toDataURL("image/jpeg", 1.0)

  //这里只生成了一页的pdf，并未截断，需要截断的话在此处操作
  pdf.addImage(pageData, "JPEG", 0, 0, contentWidth, contentHeight)

  //将pdf的流文件---》file文件
  const dataUrl = pdf.output("dataurlstring")
  const result = dataURLtoFile(dataUrl)
  if (result) fileDownload(result, {})
}

function dataURLtoFile(dataUrl: string) {
  const arr = dataUrl.split(",")
  const matchedResult = arr[0].match(/:(.*?);/)
  if (!matchedResult || matchedResult?.length < 2) return
  const mime = matchedResult[1]
  const bstr = atob(arr[1])
  let n = bstr.length
  const u8arr = new Uint8Array(n)
  while (n--) {
    u8arr[n] = bstr.charCodeAt(n)
  }
  //转换成file对象
  return new File([u8arr], glFileName, { type: mime })
}

// 内容转化为文件下载
function fileDownload(file: File, options: Record<string, any>) {
  // 创建隐藏的可下载链接
  const eleLink = document.createElement("a")
  eleLink.download = glFileName
  eleLink.style.display = "none"
  // 字符内容转变成blob地址
  const blob = options ? new Blob([file], options) : new Blob([file])
  eleLink.href = URL.createObjectURL(blob)
  // 触发点击
  document.body.appendChild(eleLink)
  eleLink.click()
  // 然后移除
  document.body.removeChild(eleLink)
}
</script>
