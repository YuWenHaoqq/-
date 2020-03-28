<template>
    <div>
        <div class="content">
            <lace></lace>
            <el-row>
                <el-col :offset="2" :span="20">
                    <el-form ref="form" :model="studentQuery" :rules="rules">
                        <el-form-item label="学生学号" label-width="80px" prop="stuId">
                            <el-input class="inputWidth" v-model="studentQuery.stuId" disabled></el-input>
                        </el-form-item>
                        <el-form-item label="班级" label-width="80px" prop="className">
                            <el-input class="inputWidth" v-model="studentQuery.className"></el-input>
                        </el-form-item>
                        <el-form-item label="学生姓名" label-width="80px" prop="stuName">
                            <el-input class="inputWidth" v-model="studentQuery.stuName"></el-input>
                        </el-form-item>
                        <el-form-item label="学生性别" label-width="80px" prop="stuSex">
                            <el-select v-model="studentQuery.stuSex" placeholder="请选择性别">
                                <el-option label="男" :value="1"></el-option>
                                <el-option label="女" :value="0"></el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="密码" label-width="80px" prop="password">
                            <el-input class="inputWidth" v-model="studentQuery.password"></el-input>
                        </el-form-item>
                        <el-form-item label="出生年月" label-width="80px" prop="birthday">
                            <el-date-picker
                                    v-model="studentQuery.birthday"
                                    align="right"
                                    type="date"
                                    placeholder="选择日期"
                                    >
                            </el-date-picker>
                        </el-form-item>
                        <el-form-item label="教师名字" label-width="80px" prop="teacherName">
                            <el-input class="inputWidth" v-model="studentQuery.teacherName" @input="changeName"></el-input>
                        </el-form-item>
                        <el-form-item label-width="80px" label="教工号" prop="teaId">
                            <el-row>
                                <el-col>
                                    <el-input class="inputWidth" v-model="studentQuery.teaId"
                                              placeholder="请输入教工号" ref="teaId" :disabled=isChange></el-input>
                                </el-col>
                                <el-col>
                                </el-col>
                            </el-row>
                        </el-form-item>
                        <el-form-item label="寝室编号" label-width="80px" prop="dormName">
                            <el-input class="inputWidth" v-model="studentQuery.dormName"></el-input>
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
        name: "stuForm",
        components: {
            'lace': lace,
            'under': under,
        }, created() {
            this.getStudentVo()
        }, data() {
            return {
                message: '修改学生信息',
                isChange:true,
                rules: {
                    teaId: [{ required: false, message: '请输入对应的教师工号', trigger: 'blur' }],
                    stuId: [{ required: true, message: '请输入对应的学号', trigger: 'blur' }],
                    className: [{ required: true, message: '请输入对应的班级名称', trigger: 'blur' }],
                    stuName: [{ required: true, message: '请输入对应的学生姓名', trigger: 'blur' }],
                    stuSex: [{ required: true, message: '请输入对应的教师性别', trigger: 'blur' }],
                    password: [{ required: true, message: '请输入对应的密码', trigger: 'blur' }],
                    birthday: [{ required: true, message: '请输入对应的生日', trigger: 'blur' }],
                    teacherName: [{ required: true, message: '请输入对应的教师姓名', trigger: 'blur' }],
                    dormName: [{ required: true, message: '请输入对应的寝室编号', trigger: 'blur' }],
                },
                studentQuery: {
                    stuId: '',
                    className: '',
                    stuName: '',
                    stuSex: '',
                    password: '',
                    birthday: '',
                    teacherName: '',
                    dormName: '',
                    teaId: ''
                }

            }
        }, methods: {
            getStudentVo() {
                let stuMessage = this.$parent.$parent.getRowMessage()
                this.studentQuery.stuId = stuMessage.stuId
                this.studentQuery.className = stuMessage.className
                this.studentQuery.stuName = stuMessage.stuName
                this.studentQuery.stuSex = stuMessage.stuSex
                this.studentQuery.password = stuMessage.password
                this.studentQuery.birthday = stuMessage.birthday
                this.studentQuery.teacherName = stuMessage.teacherName
                this.studentQuery.dormName = stuMessage.dormName
            },
            onSubmit(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        post("/api/admin/modifyStu",this.studentQuery).then(res=>{
                            this.$parent.$parent.closeDialogshow()
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
