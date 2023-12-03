<template>
  <node-view-wrapper>
    <div class="content" data-drag-handle>点击签名</div>
  </node-view-wrapper>
</template>

<script lang="ts" setup>
import { NodeViewWrapper } from "@tiptap/vue-3"

defineProps(["updateAttributes", "node"])
</script>

<style lang="less">
.content {
  height: 200px;
  background-color: rgba(100, 100, 100, 0.1);
  display: flex;
  justify-content: center;
  align-items: center;
  color: #409eff;
  margin: 12px 0;
}
</style>
