<template>
  <el-upload accept=".docx" :auto-upload="false" :show-file-list="false" :on-change="handleConvert">
    <el-button type="primary">导入docx文件</el-button>
  </el-upload>
</template>

<script setup lang="ts">
import type { UploadFile } from "element-plus"
import type { Editor } from "@tiptap/vue-3"
import mammoth from "mammoth"

const props = defineProps<{
  editor: Editor | undefined
}>()

// docx转为html
function handleConvert(uploadFile: UploadFile) {
  if (!uploadFile.raw) return
  let reader = new FileReader()
  reader.readAsArrayBuffer(uploadFile.raw)
  reader.onload = function () {
    //读取完成
    mammoth.convertToHtml({ arrayBuffer: reader.result as ArrayBuffer }).then(resultObject => {
      console.log("mammoth转换出来的code为：", resultObject.value)
      props.editor?.commands.insertContent(resultObject.value)
    })
  }
}
</script>
