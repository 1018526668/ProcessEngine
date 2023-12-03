<template>
  <el-tooltip :content="desc" placement="top-start" effect="dark" :show-after="500">
    <i
      @click="$emit('click')"
      :disabled="disabled"
      :class="{ [icon]: true, 'is-active': type === 'action' ? !disabled : active }"
    ></i>
  </el-tooltip>
</template>

<script setup lang="ts">
withDefaults(
  defineProps<{
    type: "action" | "style"
    desc: string
    disabled?: boolean
    icon: string
    active?: boolean
  }>(),
  {
    disabled: false,
    active: false
  }
)
defineEmits(["click"])
</script>
