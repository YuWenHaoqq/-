<template>
    <div>模块正在开发
        <el-button @click="getStuTemplate">下载学生模板</el-button>
    </div>

</template>

<script>
    import {postFile} from "@/util/HttpUtil";
    // import {sav}
    import { saveAs } from 'file-saver';

    export default {
        name: "AddStu",
        methods:{
            getStuTemplate(){
                postFile("/api/admin/stuFile")
            }
        }
    }
</script>

<style scoped>

</style>
