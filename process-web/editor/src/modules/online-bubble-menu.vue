<template>
  <bubble-menu v-if="editor" :editor="editor" class="bubble-menu-cl" :tippy-options="{ duration: 100 }">
    <FontFamilyEditor :editor="editor"></FontFamilyEditor>
    <el-divider direction="vertical" />
    <FontSizeEditor :editor="editor" />
    <el-divider direction="vertical" />
    <TextEditor :editor="editor" />
  </bubble-menu>
</template>

<script setup lang="ts">
import { BubbleMenu, Editor } from "@tiptap/vue-3"
import FontFamilyEditor from "./font-family-editor.vue"
import FontSizeEditor from "./font-size-editor.vue"
import TextEditor from "./text-editor.vue"

const props = defineProps<{
  editor: Editor | undefined
}>()
</script>

<style lang="scss" scoped>
.bubble-menu-cl {
  padding: 10px 20px;
  background: white;
  border: 1px solid lightgray;
  border-radius: 5px;
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>
