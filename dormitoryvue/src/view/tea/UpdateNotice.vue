<template>
    <div>
        <div class="content">
            <lace></lace>
            <el-row type="flex" justify="center">
                <el-col :span="20">
                    <el-form :model="noticeForm" label-width="100px" :rules="rules" ref="noticeForm"
                             label-position="right">
                        <el-form-item label="标题" prop="title">
                            <el-input style="width: 300px;" v-model="noticeForm.title"></el-input>
                        </el-form-item>
                        <el-form-item label="封面图片链接" prop="imgurl">
                            <el-input style="width: 300px;" v-model="noticeForm.imgurl"></el-input>
                        </el-form-item>
                        <el-form-item label="公告描述" prop="noticeDes">
                            <el-input class="solveView" type="textarea" style="width: 70%;" maxlength="100" show-word-limit v-model="noticeForm.noticeDes"></el-input>
                        </el-form-item>
                        <el-form-item label="文章内容" prop="noticeContent">
                            <el-input class="solveView" type="textarea" autosize style="width: 70%;" maxlength="3000" show-word-limit v-model="noticeForm.noticeContent"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="submitForm('noticeForm')">推送</el-button>
                            <el-button @click="resetForm('noticeForm')">重置</el-button>
                        </el-form-item>
                        <el-form-item></el-form-item>
                    </el-form>
                </el-col>
            </el-row>

        </div>
        <div>
            <under></under>
        </div>
    </div>
</template>

<script>
    import lace from '@/components/Lace'
    import under from '@/components/Under'
    import '@/static/css/global.css'
    import {post} from "@/util/HttpUtil";

    export default {
        name: "UpdateNotice",
        components: {
            'lace': lace,
            'under': under
        },
        data() {
            return {
                message: '推送公告',
                noticeForm: {
                    title: '',
                    imgurl: '',
                    noticeDes:'',
                    noticeContent:''
                },
                rules: {
                    title: [{required: true, message: '请输入标题', trigger: 'blur'}],
                    imgurl: [{required: false, message: '确定不输入封面理解', trigger: 'blur'}],
                    noticeDes: [{required: true, message: '请输入公告描述', trigger: 'blur'}],
                    noticeContent: [{required: true, message: '请输入文章内容', trigger: 'blur'}],
                }
            }
        },
        methods: {
            getMessage() {
                return this.message
            },
            submitForm(formName) {
                post()
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        }
    }
</script>

<style scoped>
    .solveView{
        padding-bottom: 20px;
    }

</style>
