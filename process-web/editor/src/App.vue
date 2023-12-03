<template>
  <div class="container">
    <!-- 左侧目录 -->
    <div class="catalogue">
      <CatalogueList
        :editor="editor"
        :isHeadingChange="isHeadingChange"
        @locate-heading="locateHeading"
      ></CatalogueList>
    </div>

    <!-- 编辑区域 -->
    <div class="container-position">
      <div class="container-center">
        <TitleHeader />
        <div class="header">
          <HeaderItem
            type="action"
            desc="撤销"
            icon="ri-arrow-go-back-line"
            @click="editor?.chain().focus().undo().run()"
            :disabled="!editor?.can().chain().focus().undo().run()"
          />
          <HeaderItem
            type="action"
            desc="重做"
            icon="ri-arrow-go-forward-line"
            @click="editor?.chain().focus().redo().run()"
            :disabled="!editor?.can().chain().focus().redo().run()"
          />

          <el-divider direction="vertical" />

          <HeadingEditor :editor="editor" />
          <TextEditor :editor="editor" />
          <TableEditor :editor="editor" />
          <FontFamilyEditor :editor="editor" />
          <FontSizeEditor :editor="editor" />

          <el-divider direction="vertical" />

          <TypographyEditor :editor="editor" />

          <el-divider direction="vertical" />

          <el-dropdown>
            <div style="outline: none; cursor: pointer; font-size: 14px">
              <span>插入</span>
              <i class="ri-arrow-drop-down-line"></i>
            </div>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item :icon="Picture" class="dropdown-item">
                  <input type="file" id="file" class="select-file" title="" accept="image/*" @change="uploadImage" />
                  <span class="desc">图片</span>
                </el-dropdown-item>
                <el-dropdown-item :icon="LinkIcon" class="dropdown-item">
                  <span class="desc">链接</span>
                </el-dropdown-item>
                <el-dropdown-item class="dropdown-item" @click="editor?.chain().focus().setHorizontalRule().run()">
                  <i class="ri-separator"></i>
                  <span class="desc">分割线</span>
                </el-dropdown-item>
                <el-dropdown-item
                  class="dropdown-item"
                  @click="insertContent({ type: 'RawFormItem', attrs: { type: 'input' } })"
                >
                  <i class="ri-keyboard-line"></i>
                  <span class="desc">输入框</span>
                </el-dropdown-item>
                <el-dropdown-item
                  :icon="Calendar"
                  class="dropdown-item"
                  @click="insertContent({ type: 'RawFormItem', attrs: { type: 'date' } })"
                >
                  <span class="desc">日期</span>
                </el-dropdown-item>
                <el-dropdown-item
                  class="dropdown-item"
                  @click="
                    insertContent({
                      type: 'RawFormItem',
                      attrs: {
                        type: 'radio-group',
                        options: defaultOptions
                      }
                    })
                  "
                >
                  <i class="ri-radio-button-line"></i>
                  <span class="desc">单选</span>
                </el-dropdown-item>
                <el-dropdown-item
                  class="dropdown-item"
                  @click="
                    insertContent({
                      type: 'RawFormItem',
                      attrs: { type: 'select', options: defaultOptions }
                    })
                  "
                >
                  <i class="ri-dropdown-list"></i>
                  <span class="desc">下拉</span>
                </el-dropdown-item>
                <el-dropdown-item :icon="EditPen" class="dropdown-item" @click="insertContent({ type: 'ESign' })">
                  <span class="desc">电子签名</span>
                </el-dropdown-item>
                <el-dropdown-item :icon="Discount" class="dropdown-item" @click="insertContent({ type: 'SvgBox' })">
                  <span class="desc">svg绘制</span>
                </el-dropdown-item>
                <SymbolEditor :insert-content="insertContent" />
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </div>

        <div class="header">
          <SearchReplace :editor="editor"></SearchReplace>

          <HeaderItem
            type="action"
            desc="注释"
            icon="ri-brackets-line"
            @click="editor?.chain().focus().addAnnotation({}).run()"
            :disabled="!editor?.can().addAnnotation()"
          />

          <HeaderItem
            type="action"
            desc="取消注释"
            icon="ri-file-line"
            @click="cancelAnnotation()"
            :disabled="!editor?.can().addAnnotation()"
          />

          <HeaderItem
            type="action"
            desc="块引用"
            icon="ri-double-quotes-l"
            @click="editor?.chain().focus().setBlockquote().run()"
            :disabled="!editor?.can().setBlockquote()"
          />

          <HeaderItem
            type="action"
            desc="取消块引用"
            icon="ri-file-line"
            @click="editor?.chain().focus().unsetBlockquote().run()"
            :disabled="!editor?.can().unsetBlockquote()"
          />
        </div>

        <OnlineBubbleMenu :editor="editor"></OnlineBubbleMenu>

        <RightClickMenu :editor="editor" />

        <div class="editor" id="editor">
          <editor-content :editor="editor" />
        </div>

        <div class="statistics">
          {{
            `文字统计: 共${editor?.storage.characterCount.characters()}个字符，${editor?.storage.characterCount.words()}个字`
          }}
        </div>
      </div>
    </div>

    <!-- 右侧按钮区域 -->
    <div class="test-button-group">
      <el-button type="primary" plain @click="handleTest()">测试</el-button>
      <el-button type="primary" @click="console.log(editor?.getJSON())">输出JSON</el-button>
      <el-button type="warning" @click="console.log(editor?.getHTML())">输出HTML</el-button>
      <HtmlToPdf />
      <DocxToHtml :editor="editor" />
      <el-button type="danger" @click="handleSave()">保存</el-button>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from "vue"
import { ElMessage } from "element-plus"
import { Picture, Link as LinkIcon, EditPen, Calendar, Discount } from "@element-plus/icons-vue"
import { useEditor, EditorContent, JSONContent } from "@tiptap/vue-3"
import {
  TextEditor,
  TableEditor,
  FontFamilyEditor,
  FontSizeEditor,
  TypographyEditor,
  SearchReplace,
  HeadingEditor,
  SymbolEditor,
  OnlineBubbleMenu,
  
  RightClickMenu
} from "./modules"
import { DocxToHtml, HtmlToPdf } from "./functions"
import { HeaderItem, CatalogueList, TitleHeader } from "./components"
import extensions from "./extensions"
import { defaultContent, defaultOptions } from "./config"
import { useGlobalStore } from "@/store/index"
import { useEventListener } from "@vueuse/core"

let initContent = null

try {
  initContent = JSON.parse(localStorage.getItem("content") || "")
} catch (error) {
  initContent = null
}
const editor = useEditor({
  content: initContent || defaultContent,
  onUpdate: () => {
    changeHeading()
  },
  extensions
})

onMounted(() => {
  useEventListener(document, "contextmenu", e => {
    e.preventDefault()
    const globalStore = useGlobalStore()
    globalStore.setMenuInfo({
      display: "block",
      x: e.x + "px", //offsetX,pageX,screenX,clientX
      y: e.y + "px"
    })
  })

  useEventListener(document, "click", e => {
    const globalStore = useGlobalStore()
    globalStore.setMenuInfoColumn("display", "none")
  })
})

function handleTest() {
  editor.value?.commands.insertContent({ type: "RawFormItem", attrs: { type: "number" } })
}

// 保存到local storage
function handleSave() {
  let content = ""
  try {
    content = JSON.stringify(editor.value?.getJSON())
  } catch (error) {
    content = ""
  }
  localStorage.setItem("content", content)
  ElMessage.success("保存成功")
}

// 插入任意内容
function insertContent(nodeContent: JSONContent | string) {
  editor.value?.commands.insertContent(nodeContent)
}

// 插入图片
function uploadImage(e: any) {
  const file = e?.target?.files[0]
  if (!file) return
  const fileReader = new FileReader()
  fileReader.readAsDataURL(file)
  fileReader.onload = function () {
    editor.value?.commands.insertContent({
      type: "image",
      attrs: { src: fileReader.result }
    })
  }
}

// heading是否发生变化 true false只是改变标志，不是发生/未发生改变
const isHeadingChange = ref(false)
function changeHeading() {
  isHeadingChange.value = !isHeadingChange.value
}

//取消注释
function cancelAnnotation() {
  const globalStore = useGlobalStore()
  let selections = globalStore.annotationData,
    id
  if (selections.length > 0) {
    selections.forEach(item => {
      id = item.id
      editor.value?.commands.deleteAnnotation(id)
    })
  }
}

// 点击菜单跳转heading
function locateHeading(clickHeading: Record<string, any>) {
  const editorContainerDom = document.getElementById("editor")
  const editorDom = editor.value?.options.element
  const proseMirrorDom = editorDom?.children[0]
  const children = proseMirrorDom?.childNodes ?? []
  const target = [...children].find((item, index) => clickHeading.index === index) as HTMLElement | undefined
  if (target && editorContainerDom) {
    const top = target.offsetTop - 10
    editorContainerDom.scrollTop = top
  }
}
</script>

<style lang="less">
@media screen and (max-width: 1340px) {
  .container-position {
    margin-left: 275px;
    align-items: flex-start !important;
  }
}

.container {
  position: relative;
  .catalogue {
    position: absolute;
    width: 265px;
    left: 0px;
    bottom: 77px;
    margin: auto;
    height: 600px;
    border: none;
  }

  blockquote {
    padding-left: 1rem;
    border-left: 3px solid rgba(#0d0d0d, 0.1);
  }

  .annotation-base {
    display: inline;
    position: relative;
    padding: 1px;
    background-color: none !important;
  }

  .fragment-middle {
    border-top: 2px solid #000000;
    border-bottom: 2px solid #000000;
    margin-left: -2px;
    margin-right: -2px;
  }

  .fragment-left {
    border-top: 2px solid #000000;
    border-bottom: 2px solid #000000;
    border-left: 2px solid #000000;
    border-radius: 5px 0px 0px 5px;
    -webkit-border-radius: 5px 0px 0px 5px;
    -moz-border-radius: 5px 0px 0px 5px;
    margin-right: -2px;
  }

  .fragment-right {
    border-top: 2px solid #000000;
    border-bottom: 2px solid #000000;
    border-right: 2px solid #000000;
    border-radius: 0px 5px 5px 0px;
    -webkit-border-radius: 0px 5px 5px 0px;
    -moz-border-radius: 0px 5px 5px 0px;
    margin-left: -2px;
  }

  .annotation-normal {
    border: 2px solid #000000 !important;
    border-radius: 5px 5px 5px 5px;
    -webkit-border-radius: 5px 5px 5px 5px;
    -moz-border-radius: 5px 5px 5px 5px;
    z-index: 10;
  }
}

.container-position {
  min-height: 700px;
  display: flex;
  flex-direction: column;
  align-items: center;
  .container-center {
    display: flex;
    flex-direction: column;
    align-items: center;
  }

  .search-result {
    background-color: #ffd90080;
  }

  .header {
    display: flex;
    align-items: center;
    gap: 8px;
    margin-bottom: 4px;
    .el-color-picker__trigger {
      padding: 0;
      width: 16px;
      height: 16px;
      padding-top: -4px;
    }
    i {
      cursor: pointer;
      color: #aaa;
    }
    .is-active {
      color: #222;
    }
  }
  .statistics {
    display: flex;
    justify-content: flex-start;
    align-items: center;
    color: #aaa;
  }
  .editor {
    height: 600px;
    overflow-y: auto;
  }
}

.test-button-group {
  position: fixed;
  top: 100px;
  right: 0;
  display: flex;
  flex-direction: column;
  row-gap: 20px;
  background-color: white;
  padding: 24px;
  border-radius: 4px;

  .el-button {
    margin-left: 0 !important;
  }
}

.dropdown-item {
  position: relative;
  width: 100px;
  .select-file {
    opacity: 0;
    position: absolute;
  }
  .desc {
    margin-left: 8px;
  }
}

.collaboration-cursor__caret {
  border-left: 1px solid #0d0d0d;
  border-right: 1px solid #0d0d0d;
  margin-left: -1px;
  margin-right: -1px;
  pointer-events: none;
  position: relative;
  word-break: normal;
}

.collaboration-cursor__label {
  border-radius: 3px 3px 3px 0;
  color: #0d0d0d;
  font-size: 12px;
  font-style: normal;
  font-weight: 600;
  left: -1px;
  line-height: normal;
  padding: 0.1rem 0.3rem;
  position: absolute;
  top: -1.4em;
  user-select: none;
  white-space: nowrap;
}
</style>
