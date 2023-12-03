import { useGlobalStore } from "@/store/index"
import StarterKit from "@tiptap/starter-kit"
import Highlight from "@tiptap/extension-highlight"
import Underline from "@tiptap/extension-underline"
import TextAlign from "@tiptap/extension-text-align"
import Image from "@tiptap/extension-image"
import Link from "@tiptap/extension-link"
import TextStyle from "@tiptap/extension-text-style"
import FontFamily from "@tiptap/extension-font-family"
import FontSize from "tiptap-extension-font-size"
import { Color } from "@tiptap/extension-color"

import Table from "@tiptap/extension-table"
import TableHeader from "@tiptap/extension-table-header"
import TableRow from "@tiptap/extension-table-row"

import Collaboration from "@tiptap/extension-collaboration"
import { HocuspocusProvider } from "@hocuspocus/provider"
import CollaborationCursor from "@tiptap/extension-collaboration-cursor"

import ESign from "./e-sign"
import CustomCharacterCount from "./CustomCharacterCount"
import CustomFormItem from "./custom-form-item"
import RawFormItem from "./raw-form-item"
import CustomTableCell from "./CustomTableCell"
import SvgBox from "./svg-box"

import { SearchAndReplace } from "./searchAndReplace"
import ListItem from "@tiptap/extension-list-item"
import OrderedList from "@tiptap/extension-ordered-list"
import BulletList from "@tiptap/extension-bullet-list"
import TaskItem from "@tiptap/extension-task-item"
import TaskList from "@tiptap/extension-task-list"
import HorizontalRule from "@tiptap/extension-horizontal-rule"
import Indent from "@weiruo/tiptap-extension-indent"

import FormatPainter from './formPainter/index'

import BubbleMenu from '@tiptap/extension-bubble-menu'

import Blockquote from '@tiptap/extension-blockquote'
import { Annotation, AnnotationMagic } from 'tiptap-annotation-magic';

import * as Y from "yjs"

const ydoc = new Y.Doc()

const provider = new HocuspocusProvider({
  url: "ws://192.168.11.160:1234",
  name: "room",
  document: ydoc
})

//AnnotationMagic注释功能的interface
interface AnnotationData {
  name: string;
  magicNumber: number;
}

export default [
  CustomCharacterCount,
  CustomFormItem,
  RawFormItem,
  ESign,
  SvgBox,
  StarterKit,
  Highlight,
  Underline,
  TextAlign.configure({
    types: ["heading", "paragraph"]
  }),
  Image,
  Link,
  TextStyle,
  Color,
  Table.configure({
    resizable: true
  }),
  CustomTableCell,
  TableHeader,
  TableRow,
  // Collaboration.configure({
  //   document: ydoc
  // }),
  // CollaborationCursor.configure({
  //   provider,
  //   user: { name: "哈士奇", color: "#ffcc00" }
  // }),
  FontFamily,
  FontSize,
  SearchAndReplace.configure({
    searchResultClass: "search-result", // 找到结果的样式，默认给search-result
    caseSensitive: false, // 是否区分大小写
    disableRegex: false // 是否禁用Regex
  }),
  ListItem,
  OrderedList,
  BulletList,
  TaskItem,
  TaskList,
  HorizontalRule,
  Indent,
  FormatPainter,
  BubbleMenu.configure({
    element: document.querySelector('.menu') as HTMLElement,
  }),
  Blockquote,
  AnnotationMagic<AnnotationData>().configure({
    onAnnotationListChange: (annotations: Annotation<AnnotationData>[]) => {
      // annotation状态是created/deleted/updated触发
      // console.log('annotations created/deleted/updated', annotations)
    },
    onSelectionChange: (selectedAnnotations: Annotation<AnnotationData>[]) => {
      // 选中文本的改变事件
      const globalStore = useGlobalStore();
      globalStore.setAnnotationData(selectedAnnotations)
    },
    styles: {
      // CSS classes to use for different fragments
      leftFragment: 'fragment-left',
      rightFragment: 'fragment-right',
      middleFragment: 'fragment-middle',
      normal: 'annotation-normal',
    }
  })

]
