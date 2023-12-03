import { defineStore } from 'pinia';
interface MenuInfoInterface {
    display: string,
    x: string,
    y: string
}
export const useGlobalStore = defineStore('globalStore', {
    state: () => {
        return {
            annotationData: [],  //勾选的时候注释工具获取到的勾选数据
            menuInfo: {     //右键菜单的信息
                display: 'none',
                x: '0px',
                y: '0px'
            }
        }
    },

    actions: {
        setAnnotationData(value: any) {
            this.annotationData = value
        },

        setMenuInfo(value: MenuInfoInterface) {
            this.menuInfo = value;
        },

        setMenuInfoColumn(key: 'display' | 'x' | 'y', value: string) {
            this.menuInfo[key] = value;
        }
    }
})