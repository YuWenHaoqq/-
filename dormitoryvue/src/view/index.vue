<template>
    <div class="bg">
        <div class="filter">
        </div>
        <div class="formbg">
            <el-form class="formcontent" ref="form" v-model="form">
                <div class="title">登录</div>
                <br>
                <div>
                    <el-form-item class="input" label="账号" prop="account">
                        <el-input v-model='form.account' placeholder="请输入学号" clearable></el-input>
                    </el-form-item>
                    <br>
                    <el-form-item class="input" label="密码" prop="password">
                        <el-input placeholder="请输入密码" v-model='form.password' show-password clearable></el-input>
                    </el-form-item>
                    <br>
                    <div class="submit">
                        <el-button type="primary" @click="submit">登录</el-button>
                        <el-button type="warning" @click="reset">清空</el-button>
                    </div>
                </div>
            </el-form>

        </div>
    </div>
</template>

<script>
    import {post} from "@/util/HttpUtil";
    import { Message} from "element-ui";


    export default {
        name: "index",
        created() {
        },
        data() {
            return {
                form: {
                    account: '',
                    password: ''
                }
            }
        },
        methods: {
            submit(){
                // window.console.log(this.form.account)
                // window.console.log(this.form.password)
                post('/api/student/login',this.form).then(res=>{
                    Message({
                        type:"success",
                        message:res.message
                    })
                })
                .catch()
            },
            reset(){
                this.form.account=''
                this.form.password=''
            }
        }
    }
</script>

<style scoped>
    .title{
        color: #409EFF;
        font-size: 35px;
        /*border: 1px solid red;*/
        text-align: center;
    }
    .formcontent{
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%,-50%);
    }
    .submit{
        display: flex;
        justify-content: center;
    }
    .input{
        color: #409EFF;
        font-size: 22px;
        display: flex;
        justify-content: center;
        width: 350px;
    }
    .input *:first-child{
        margin-right: 20px;
    }
    .bg {
        background: url("../assets/image/loginBack.jpg")  no-repeat fixed center;
        background-size: cover;
        width: 100%;
        height: 100%;
        display: flex;
        justify-content: center;
    }

    .formbg {
        width: 400px;
        height: 400px;
        background: rgba(255, 255, 255, 0.2);
        /*filter: blur(20px);*/
        border-radius: 10%;

        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%,-50%);

        display: flex;
        justify-content: center;
    }

    .filter{
        width: 450px;
        height: 450px;
        background: rgba(255, 255, 255, 0.6);
        filter: blur(20px);

        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%,-50%);
    }

</style>
