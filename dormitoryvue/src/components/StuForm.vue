<template>
    <div>
        <div class="content">
            <lace></lace>
            <el-row>
                <el-col :offset="2" :span="20">
                    <el-form ref="form" :model="studentVo" :rules="rules">
                        <el-form-item label="学生学号" label-width="80px" prop="stuId">
                            <el-input class="inputWidth" v-model="studentVo.stuId"></el-input>
                        </el-form-item>
                        <el-form-item label="班级" label-width="80px" prop="className">
                            <el-input class="inputWidth" v-model="studentVo.className"></el-input>
                        </el-form-item>
                        <el-form-item label="学生姓名" label-width="80px" prop="stuName">
                            <el-input class="inputWidth" v-model="studentVo.stuName"></el-input>
                        </el-form-item>
                        <el-form-item label="学生性别" label-width="80px" prop="stuSex">
                            <el-select v-model="studentVo.stuSex" placeholder="请选择性别">
                                <el-option label="男" :value="1"></el-option>
                                <el-option label="女" :value="0"></el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="密码" label-width="80px" prop="password">
                            <el-input class="inputWidth" v-model="studentVo.password"></el-input>
                        </el-form-item>
                        <el-form-item label="出生年月" label-width="80px" prop="birthday">
                            <el-date-picker
                                    v-model="studentVo.birthday"
                                    align="right"
                                    type="date"
                                    placeholder="选择日期"
                                    >
                            </el-date-picker>
                        </el-form-item>
                        <el-form-item label="教师名字" label-width="80px" prop="teacherName">
                            <el-input class="inputWidth" v-model="studentVo.teacherName" @input="changeName"></el-input>
                        </el-form-item>
                        <el-form-item label-width="80px" label="教工号" prop="teaId">
                            <el-row>
                                <el-col>
                                    <el-input class="inputWidth" v-model="studentVo.teaId"
                                              placeholder="请输入教工号" ref="teaId" :disabled=isChange></el-input>
                                </el-col>
                                <el-col>
                                </el-col>
                            </el-row>
                        </el-form-item>
                        <el-form-item label="寝室编号" label-width="80px" prop="dormName">
                            <el-input class="inputWidth" v-model="studentVo.dormName"></el-input>
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
                studentVo: {
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
                this.studentVo.stuId = stuMessage.stuId
                this.studentVo.className = stuMessage.className
                this.studentVo.stuName = stuMessage.stuName
                this.studentVo.stuSex = stuMessage.stuSex
                this.studentVo.password = stuMessage.password
                this.studentVo.birthday = stuMessage.birthday
                this.studentVo.teacherName = stuMessage.teacherName
                this.studentVo.dormName = stuMessage.dormName
            },
            onSubmit(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        window.console.log(123)
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
