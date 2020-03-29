<template>
    <div>
        <div class="content">
            <lace></lace>
            <el-row>
                <el-col :offset="2" :span="20">
                    <el-form ref="form" :model="teacherVo" :rules="rules">
                        <el-form-item label="教工号" label-width="80px" prop="teaId">
                            <el-input class="inputWidth" v-model="teacherVo.teaId" disabled></el-input>
                        </el-form-item>
                        <el-form-item label="班级" label-width="80px" prop="className">
                            <el-input class="inputWidth" v-model="teacherVo.className"></el-input>
                        </el-form-item>
                        <el-form-item label="学生姓名" label-width="80px" prop="name">
                            <el-input class="inputWidth" v-model="teacherVo.name"></el-input>
                        </el-form-item>
                        <el-form-item label="学生性别" label-width="80px" prop="sex">
                            <el-select v-model="teacherVo.sex" placeholder="请选择性别">
                                <el-option label="男" :value="1"></el-option>
                                <el-option label="女" :value="0"></el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="密码" label-width="80px" prop="password">
                            <el-input class="inputWidth" v-model="teacherVo.password"></el-input>
                        </el-form-item>
                        <el-form-item label="出生年月" label-width="80px" prop="birthday">
                            <el-date-picker
                                    v-model="teacherVo.birthday"
                                    align="right"
                                    type="date"
                                    value-format="yyyy-MM-dd"
                                    placeholder="选择日期"
                            >
                            </el-date-picker>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="onSubmit('form')">修改</el-button>
                        </el-form-item>
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
    import lace from "@/components/Lace";
    import under from "@/components/Under";
    import {post} from "@/util/HttpUtil";

    export default {
        name: "TeaForm",
        components: {
            'lace': lace,
            'under': under,
        }, created() {
            this.getStudentVo()
        }, data() {
            return {
                message: '修改教师信息',
                isChange:true,
                rules: {
                    teaId: [{ required: true, message: '请输入对应的教师工号', trigger: 'blur' }],
                    className: [{ required: true, message: '请输入班级名称', trigger: 'blur' }],
                    name: [{ required: true, message: '请输入对应的教师姓名', trigger: 'blur' }],
                    password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
                    sex: [{ required: true, message: '请选择性别', trigger: 'blur' }],
                    birthday: [{ required: true, message: '请输入出生日期', trigger: 'blur' }],
                },
                teacherVo: {
                    teaId: '',
                    className: '',
                    name: '',
                    password: '',
                    sex: '',
                    birthday: '',
                }

            }
        }, methods: {
            getStudentVo() {
                let teaMessage = this.$parent.$parent.getRowMessage()
                window.console.log(teaMessage)
                this.teacherVo.teaId = teaMessage.teaId
                this.teacherVo.className = teaMessage.className
                this.teacherVo.name = teaMessage.name
                this.teacherVo.password = teaMessage.password
                this.teacherVo.sex = teaMessage.sex
                this.teacherVo.birthday = teaMessage.birthday
            },
            onSubmit(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        post("/api/admin/modifyTea",this.teacherVo).then(res=>{
                            this.$parent.$parent.closeDialogshow()
                            this.$parent.$parent.getStuData()
                            this.$message({
                                type:'success',
                                message:res.message
                            })
                        }).catch(err=>{
                            this.$message({
                                type:'warning',
                                message:err.message
                            })
                        })
                    } else {
                        return false;
                    }
                });

            }, getMessage() {
                return this.message
            },
            changeName(){
                this.isChange=false
            },
        }
    }
</script>

<style scoped>
    .inputWidth {
        width: 70%;
    }

</style>
