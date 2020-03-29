<template>
    <div>
        <div class="content">
            <lace></lace>
            <el-row class="row">
                <el-col :offset="2" :xs="8" :sm="6" :md="4" :lg="3" :xl="2">
                    <el-button @click="getStuTemplate(1)" class="btn">下载学生模板</el-button>
                </el-col>
<!--                <el-col :offset="1" :xs="8" :sm="6" :md="4" :lg="3" :xl="2">-->
<!--                    <el-button @click="getStuTemplate(2)" class="btn">下载教师模板</el-button>-->
<!--                </el-col>-->
            </el-row>
            <el-row class="row">
                <el-col :offset="2" :xs="8" :sm="6" :md="4" :lg="3" :xl="2">
                    <el-button @click="getStuTemplate(2)" class="btn">下载教师模板</el-button>
                </el-col>
            </el-row>

            <el-row class="row">
                <el-col :offset="2" :xs="8" :sm="6" :md="4" :lg="3" :xl="2">
                    <el-upload
                            ref="stuExcel"
                            :limit="1"
                            class="upload-demo"
                            accept="xlsx,xls"
                            :on-error="errUpload"
                            :on-success='successUpload'
                            action="/api/admin/addStu">
                        <el-button size="small" type="primary">上传学生信息</el-button>
                        <div slot="tip" class="el-upload__tip">只能上传excel文件</div>
                    </el-upload>
                </el-col>
            </el-row>
            <el-row class="row">
                <el-col :offset="2" :xs="8" :sm="6" :md="4" :lg="3" :xl="2">
                    <el-upload
                            ref="stuExcel"
                            :limit="1"
                            class="upload-demo"
                            accept="xlsx,xls"
                            :on-error="errUpload"
                            :on-success='successUpload'
                            action="/api/admin/addTeaByExcel">
                        <el-button size="small" type="primary">上传教师信息</el-button>
                        <div slot="tip" class="el-upload__tip">只能上传excel文件</div>
                    </el-upload>
                </el-col>
            </el-row>
        </div>
        <div>
            <under></under>
        </div>

    </div>

</template>

<script>
    import {postFile} from "@/util/HttpUtil";
    import lace from '@/components/Lace'
    import under from '@/components/Under'
    import '@/static/css/global.css'


    export default {
        name: "AddStu",
        components: {
            'lace': lace,
            'under': under
        },
        data() {
            return {
                message: '学生教师录入'
            }
        },
        methods: {
            ff(){
                return false;
            },
            getMessage() {
                return this.message
            },
            getStuTemplate(type) {
                postFile("/api/admin/stuFile", {type: type})
            },
            successUpload(res){
                let that=this
                    if (res.code===0) {
                        this.$message({
                            type: 'success',
                            message: '上传成功'
                        })

                    }else{
                        this.$notify({
                            title:'警告',
                            message:res.message,
                            duration: 0
                        })
                    }
                setTimeout(function () {
                    that.$refs.stuExcel.clearFiles()
                },1000)
            },
            errUpload(){
                this.$message({
                    type:'error',
                    message:'上传失败,请重新上传'
                    }

                )
            }

        }
    }
</script>

<style scoped>
    .btn {
        /*border: 1px solid red;*/
        /*font-size: 13px;*/

        width: 100%;
        overflow: hidden;
        text-overflow: ellipsis;
    }

    .row{
        margin-bottom: 50px;
    }

</style>
