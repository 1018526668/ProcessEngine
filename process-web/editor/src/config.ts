export const defaultContent = {
  type: "doc",
  content: [
    {
      type: "paragraph",
      attrs: {
        textAlign: "center"
      },
      content: [
        {
          type: "text",
          text: "易企签电子文档签署平台",
          marks: [
            {
              attrs: { color: "#000" },
              type: "textStyle"
            }
          ]
        }
      ]
    },
    {
      type: "paragraph",
      attrs: {
        textAlign: "left"
      },
      content: [
        {
          type: "text",
          text: "Tiptap is a headless wrapper around ProseMirror – a toolkit for building rich text WYSIWYG editors, which is already in use at many well-known companies such as New York Times, The Guardian or Atlassian.",
          marks: [
            {
              attrs: { color: "#000" },
              type: "textStyle"
            }
          ]
        }
      ]
    }
  ]
}

export const defaultOptions = [
  { label: "选项一", value: "1" },
  { label: "选项二", value: "2" }
]
