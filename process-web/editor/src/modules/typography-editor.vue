<template>
  <HeaderItem
    type="style"
    desc="左对齐"
    icon="ri-align-left"
    :active="editor?.isActive({ textAlign: 'left' })"
    @click="editor?.chain().focus().setTextAlign('left').run()"
  />
  <HeaderItem
    type="style"
    desc="居中"
    icon="ri-align-center"
    :active="editor?.isActive({ textAlign: 'center' })"
    @click="editor?.chain().focus().setTextAlign('center').run()"
  />
  <HeaderItem
    type="style"
    desc="右对齐"
    icon="ri-align-right"
    :active="editor?.isActive({ textAlign: 'right' })"
    @click="editor?.chain().focus().setTextAlign('right').run()"
  />

  <HeaderItem type="style" desc="向右缩进" icon="ri-indent-increase" @click="props.editor?.commands.indent()" />

  <HeaderItem type="style" desc="回退缩进" icon="ri-indent-decrease" @click="props.editor?.commands.outdent()" />

  <HeaderItem
    type="style"
    desc="有序列表(可通过新的一行输入1. 触发)"
    icon="ri-list-ordered"
    @click="editor?.chain().focus().toggleOrderedList().run()"
  />

  <HeaderItem
    type="style"
    desc="无序列表(可通过新的一行输入+/-触发)"
    icon="ri-list-unordered"
    @click="editor?.chain().focus().toggleBulletList().run()"
  />

  <HeaderItem
    type="style"
    desc="待办事项(可通过新的一行输入[ ] 触发)"
    icon="ri-list-check-3"
    @click="editor?.chain().focus().toggleTaskList().run()"
  />

  <HeaderItem
    type="style"
    desc="下方插入列表"
    v-if="editor?.can().splitListItem('listItem')"
    icon="ri-play-list-add-line"
    @click="editor?.chain().focus().splitListItem('listItem').run()"
  />

  <HeaderItem
    type="style"
    desc="向右缩进列表"
    v-if="editor?.can().sinkListItem('listItem')"
    icon="ri-play-list-2-fill"
    @click="editor.chain().focus().sinkListItem('listItem').run()"
  />

  <HeaderItem
    type="style"
    desc="回退"
    v-if="editor?.can().liftListItem('listItem')"
    icon="ri-loop-left-fill"
    @click="editor.chain().focus().liftListItem('listItem').run()"
  />
</template>

<script setup lang="ts">
import type { Editor } from "@tiptap/vue-3"
import HeaderItem from "@/components/header-item.vue"

const props = defineProps<{
  editor: Editor | undefined
}>()

</script>

<style lang="scss">
@for $i from 1 through 8 {
  .tt-indent-#{$i} {
    $val: $i * 3rem;
    padding-left: $val;
  }
}

ul[data-type="taskList"] {
  list-style: none;
  padding: 0;

  p {
    margin: 0;
  }

  li {
    display: flex;

    > label {
      flex: 0 0 auto;
      margin-right: 0.5rem;
      user-select: none;
    }

    > div {
      flex: 1 1 auto;
    }

    ul li,
    ol li {
      display: list-item;
    }

    ul[data-type="taskList"] > li {
      display: flex;
    }
  }
}
</style>
