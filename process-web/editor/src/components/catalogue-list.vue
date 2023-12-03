<template>
  <div class="catalogue-list-root">
    <div v-if="!isCollapse" class="catalogue-hint" @click="handleCollapse(true)">目录</div>
    <div class="catalogue-container" id="catalogue-container">
      <div class="list-hint">
        <div>目录</div>
        <i class="ri-close-line" @click="handleCollapse(false)"></i>
      </div>
      <el-tree
        empty-text="对文档内容应用“H1”/“H2”样式即可生成“目录”"
        default-expand-all
        :expand-on-click-node="false"
        :data="catalogueState.treeData"
        :props="catalogueProps"
        @node-click="chooseHeading"
      />
    </div>
  </div>
</template>

<script lang="ts" setup>
import { onMounted, reactive, ref, watch } from "vue"
import { Editor } from "@tiptap/vue-3"

/*---------------------初始化-------------------------*/
const props = defineProps({
  editor: { type: Editor },
  isHeadingChange: { type: Boolean }
})

const catalogueProps = {
  children: "children",
  label: "label"
}
const catalogueState = reactive({
  treeData: []
})

onMounted(() => {
  setTimeout(() => {
    getHeadingList()
  })
})

watch(
  () => props.isHeadingChange,
  () => {
    getHeadingList()
  }
)

type catalogueDataType = {
  index: number
  label: string | undefined
  children: Array<catalogueDataType>
  level: number
}
function getHeadingList() {
  let result: Array<catalogueDataType> = []
  const editorJSON = props.editor?.getJSON()
  const contentArray = editorJSON?.content || []
  contentArray.map((item, index) => {
    if (item.type === "heading" && item.attrs.level < 3) {
      if (item.attrs.level === 1) {
        result.push({ label: item.content[0].text, level: item.attrs.level, index: index, children: [] })
      } else {
        if (result.length > 0) {
          result[result.length - 1].children = [
            ...result[result.length - 1].children,
            { label: item.content[0].text, level: item.attrs.level, index: index, children: [] }
          ]
        }
      }
    }
  })
  catalogueState.treeData = result
}

/*---------------------展开和关闭-------------------------*/
const isCollapse = ref(true)

function handleCollapse(collapse: boolean) {
  setTimeout(
    () => {
      isCollapse.value = collapse
    },
    collapse ? 0 : 500
  )
  const dom = document.getElementById("catalogue-container") as HTMLElement
  dom.style.width = collapse ? "265px" : "0px"
}

/*---------------------点击editor跳转heading-------------------------*/
const emits = defineEmits(["locateHeading"])
function chooseHeading(e: object) {
  emits("locateHeading", e)
}
</script>

<style scoped>
.catalogue-list-root {
  position: relative;
  width: 100%;
  height: 100%;

  .catalogue-hint {
    position: absolute;
    top: 0px;
    bottom: 0px;
    margin: auto;
    cursor: pointer;
    /*垂直展示*/
    writing-mode: vertical-lr;
    /*兼容IE*/
    writing-mode: tb-lr;
    height: fit-content;
    padding: 4px;
    background-color: #fff;
    border-radius: 0px 4px 4px 0px;
    color: #aaa;
  }

  .el-tree {
    height: calc(100% - 46px);
    background: none;
    --el-tree-node-hover-bg-color: rgba(0, 0, 0, 0.02);
    margin: 6px 12px;
  }

  .catalogue-container {
    width: 256px;
    overflow: hidden;
    background: #fff;
    height: 100%;
    transition: 0.5s width;
    border-radius: 2px;

    .list-hint {
      color: #aaa;
      padding: 12px;
      border-bottom: 1px solid #cfcfcf;
      font-size: 16px;
      display: flex;
      justify-content: space-between;
      align-items: center;
      white-space: nowrap;

      .ri-close-line {
        cursor: pointer;
      }
    }

    .catalogue-item {
      color: #aaa;
      margin: 6px 12px;
    }
  }
}
</style>
