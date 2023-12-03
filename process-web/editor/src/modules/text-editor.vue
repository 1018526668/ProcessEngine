<template>
  <HeaderItem
    type="style"
    desc="加粗"
    icon="ri-bold"
    :active="editor?.isActive('bold')"
    @click="editor?.chain().focus().toggleBold().run()"
    :disabled="!editor?.can().chain().focus().toggleBold().run()"
  />
  <HeaderItem
    type="style"
    desc="倾斜"
    icon="ri-italic"
    :active="editor?.isActive('italic')"
    @click="editor?.chain().focus().toggleItalic().run()"
    :disabled="!editor?.can().chain().focus().toggleItalic().run()"
  />
  <HeaderItem
    type="style"
    desc="删除线"
    icon="ri-strikethrough"
    :active="editor?.isActive('strike')"
    @click="editor?.chain().focus().toggleStrike().run()"
    :disabled="!editor?.can().chain().focus().toggleStrike().run()"
  />
  <HeaderItem
    type="style"
    desc="下划线"
    icon="ri-underline"
    :active="editor?.isActive('underline')"
    @click="editor?.chain().focus().toggleUnderline().run()"
  />

  <el-color-picker
    :model-value="editor?.getAttributes('textStyle').color || '#000'"
    :predefine="predefinedColors"
    @active-change="changeColor"
  />
  <HeaderItem
    type="style"
    desc="高亮"
    icon="ri-palette-line"
    :active="editor?.isActive('highlight')"
    @click="editor?.chain().focus().toggleHighlight().run()"
    :disabled="!editor?.can().chain().focus().toggleStrike().run()"
  />
  <HeaderItem
    type="style"
    desc="清除样式"
    icon="ri-eraser-fill"
    @click="editor?.chain().focus().unsetAllMarks().run()"
  />
</template>

<script setup lang="ts">
import type { Editor } from "@tiptap/vue-3"
import HeaderItem from "@/components/header-item.vue"

const predefinedColors = ["#000", "#ff4500", "#ff8c00", "#ffd700", "#90ee90", "#00ced1", "#1e90ff", "#c71585"]

const props = defineProps<{
  editor: Editor | undefined
}>()

function changeColor(nextColor: string | null) {
  if (nextColor) props.editor?.chain().focus().setColor(nextColor).run()
}
</script>
