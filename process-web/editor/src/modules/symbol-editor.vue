<template>
  <el-dropdown-item class="dropdown-item" @click="insertSymbol()">
    <i class="ri-omega"></i>
    <span class="desc">特殊符号</span>
  </el-dropdown-item>
</template>

<script setup lang="ts">
const props = defineProps({
  insertContent: { type: Function, required: true }
})

function insertSymbol() {
  props.insertContent("<code>¥ © →</code>")
}
</script>
