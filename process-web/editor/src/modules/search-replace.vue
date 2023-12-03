<template>
  <div class="search-replace">
    <el-input @input="searchValueChange" v-model="searchValue" size="small" placeholder="查询值" />
    <el-input @input="replaceValueChange" class="replace-cl" v-model="replaceValue" size="small" placeholder="替换值" />
    <el-button-group class="sr-btn-group">
      <el-button size="small" :icon="Delete" @click="clearValue"></el-button>
      <el-button size="small" :icon="Check" @click="replace"></el-button>
      <el-button size="small" :icon="Finished" @click="replaceAll"></el-button>
    </el-button-group>
  </div>
</template>

<script setup lang="ts">
import { ref } from "vue"
import type { Editor } from "@tiptap/vue-3"
import { Delete, Check, Finished } from "@element-plus/icons-vue"

const props = defineProps<{
  editor: Editor | undefined
}>()
let searchValue = ref("")
let replaceValue = ref("")

function searchValueChange(e: any) {
  props.editor?.commands.setSearchTerm(searchValue.value)
}

function replaceValueChange(e: any) {
  props.editor?.commands.setReplaceTerm(replaceValue.value)
}

function replace() {
  props.editor?.commands.replace()
}

function replaceAll() {
  props.editor?.commands.replaceAll()
}

function clearValue() {
  searchValue.value = ""
  replaceValue.value = ""
  props.editor?.commands.setSearchTerm("")
  props.editor?.commands.setReplaceTerm("")
}
</script>

<style lang="less" scoped>
.search-replace {
  display: flex;
  align-items: center;

  .replace-cl {
    margin-left: 5px;
  }

  .sr-btn-group {
    display: flex;
    align-items: center;
    margin-left: 5px;
  }
}
</style>
