<template>
    <div>
        <div class="content">
            <lace></lace>

            <el-row>
                <el-col :span="14" :offset="2">
                    <el-form :model="leaveForm" ref="leaveForm" :rules="rules">
                        <el-form-item label="请假类型" prop="leaveType" >
                            <el-select v-model="leaveForm.leaveType" placeholder="请选择请假的类型">
                                <el-option label="事假" value="1"></el-option>
                                <el-option label="病假" value="2"></el-option>
                                <el-option label="公假" value="3"></el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="请假时间" prop="leaveDate" >
                            <el-date-picker
                                    v-model="leaveForm.leaveDate"
                                    type="datetimerange"
                                    range-separator="至"
                                    start-placeholder="开始日期"
                                    end-placeholder="结束日期"></el-date-picker>
                        </el-form-item>
                        <el-form-item label="请假时长" prop="leaveTime" >
                            <el-input v-model="leaveForm.leaveTime" maxlength="5" show-word-limit style="width: 500px"></el-input>
                        </el-form-item>
                        <el-form-item label="请假原因" prop="leaveDes">
                            <el-input type="textarea" v-model="leaveForm.leaveDes" maxlength="30" show-word-limit style="width: 500px"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-row >
                                <el-col :xs="10" :sm="6" :md="4" :lg="3" :xl="2"><el-button class="btn" type="primary" @click="submitForm">确认</el-button></el-col>
                                <el-col :xs="{span: 10, offset: 2}" :sm="{span: 6, offset: 2}" :md="{span: 4, offset: 1}" :lg="{span: 3, offset: 1}" :xl="{span: 2, offset: 1}">
                                    <el-button class="btn" @click="resetForm('leaveForm')">清空</el-button></el-col>
                            </el-row>

                        </el-form-item>
                    </el-form>
                </el-col>
            </el-row>
        </div>
        <div><under></under></div>
    </div>
</template>

<script>
    import lace from '@/components/Lace'
    import under from '@/components/Under'
    import '@/static/css/global.css'
    import {post} from "@/util/HttpUtil";

    export default {
        name: "Leave",
        components: {
            'lace': lace,
            'under':under
        },
        data() {
            return {
                message: "请假",
                leaveForm: {
                    leaveType: '',
                    leaveDate: [],
                    leaveTime:'',
                    leaveDes:'',
                    stuNumber:sessionStorage.getItem('stuId')
                },
                rules:{
                    leaveType:[
                        {required: true, message: '请选择请假类型', trigger: 'blur'}
                    ],
                    leaveDate:[
                        {required: true, message: '请选择请假时间', trigger: 'blur'}
                    ],
                    leaveTime:[
                        {required: true, message: '请输入请假时长', trigger: 'blur'}
                    ],
                    leaveDes:[
                        {required: true, message: '请输入请假原因', trigger: 'blur'}
                    ]
                }
            }
        },
        methods: {
            getMessage() {
                return this.message;
            },
            submitForm(){
                post('/api//student/stuLeave',this.leaveForm).then(res=>{
                    this.$message({
                        message: res.message,
                        type: 'success'
                    });

                }).catch(err=>{
                    this.$message.error(err.message)
                })

            },
            resetForm(leaveForm){
                this.$refs[leaveForm].resetFields();
            }
        }
    }
</script>

<style scoped>
.btn{
    width: 100%;
}
</style>
