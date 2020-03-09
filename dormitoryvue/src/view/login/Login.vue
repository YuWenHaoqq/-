<template>
    <div ref="silder" class="home-slider">
        <div class="main-slider">
            <img ref="hill" src="@/assets/image/hill.png" class="slider-hill" alt="slider image">
            <img ref="house" src="@/assets/image/house.png" class="slider-house" alt="slider image">
            <img ref="sun" src="@/assets/image/sun.png" class="slider-sun" alt="slider image">
            <img ref="birds1" src="@/assets/image/birds1.png" class="slider-birds1" alt="slider image">
            <img ref="birds2" src="@/assets/image/birds2.png" class="slider-birds2" alt="slider image">
        </div>
        <div ref="form" class="formbg">
            <el-form>
                <div>
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
                        <el-form-item class="input" prop="type">
                            <el-radio-group v-model="form.identity">
                                <el-radio :label="1">学生</el-radio>
                                <el-radio :label="2">教师</el-radio>
                            </el-radio-group>
                        </el-form-item>
                        <div class="submit">
                            <el-button type="primary" @click="submit">登录</el-button>
                            <el-button type="warning" @click="reset">清空</el-button>
                        </div>
                    </div>
                </div>
            </el-form>
        </div>
    </div>
</template>

<script>
    import {aesPost} from "@/util/HttpUtil";
    import {Message} from "element-ui";

    export default {
        name: "Login",
        components: {},
        data() {
            return {
                form: {
                    account: '',
                    password: '',
                    identity: 1
                }
            }
        },
        created() {
            // getRSAKey()
        },
        mounted() {
            this.animation()
        },
        methods: {
            animation() {
                let that = this
                setTimeout(function () {
                    that.$refs.hill.style.transform = 'translateY(0vh)'
                }, 0)
                setTimeout(function () {
                    that.$refs.house.style.transform = 'translateY(0vh)';
                    that.$refs.sun.style.transform = 'translateY(0vh)'
                }, 1000);
                setTimeout(function () {
                    that.$refs.birds1.style.transform = 'translateX(0)';
                    that.$refs.birds1.style.opacity = '1';
                    that.$refs.birds2.style.transform = 'translateX(0)';
                    that.$refs.birds2.style.opacity = '1';
                }, 2500)
                setTimeout(function () {
                    that.$refs.form.style.right = '10%';
                    that.$refs.form.style.opacity = '1';
                }, 0)
            },
            submit() {
                let  that=this
                aesPost('/api/student/login', this.form, {ase: true}).then(res => {
                    Message({
                        type: "success",
                        message: res.message
                    })
                    sessionStorage.setItem('token', res.data.token)
                    sessionStorage.setItem('stuId', res.data.stuId)
                    that.$router.push({path:'/index/homepage'})
                    // window.console.log("token:",sessionStorage.getItem('token'))
                    // window.console.log("token:", rsaEncryption(sessionStorage.getItem('token')))
                    // window.console.log("stuid:", sessionStorage.getItem('stuId'))
                    // window.console.log("stuid:", rsaEncryption(sessionStorage.getItem('stuId')))
                    // window.console.log("aes:", rsaEncryption(sessionStorage.getItem("aes")))
                })
                    .catch(err => {
                        Message({
                            message: err.message,
                            type: "error"
                        })
                    })
            },
            reset() {
                this.form.account = ''
                this.form.password = ''
            }
        }
    }
</script>

<style scoped>
    .input {
        color: #409EFF;
        font-size: 22px;
        display: flex;
        justify-content: center;
        width: 350px;
    }

    .input *:first-child {
        margin-right: 20px;
    }

    .title {
        color: #DCDFE6;
        font-size: 35px;
        text-align: center;
    }

    .formbg {
        z-index: 999;
        width: 20%;
        height: 400px;
        margin-bottom: 0px;
        text-align: center;
        position: absolute;
        bottom: 0px;
        right: 0%;
        display: flex;
        align-items: center;
        justify-content: center;

        /*transition: 1s;*/
        /*opacity: 0;*/
    }

    .home-slider {
        background: url("../../assets/image/slider-bg.png") repeat-x bottom;
        width: 100%;
        height: 273px;
        position: relative;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        /*border: 1px solid red;*/
    }

    .slider-hill {
        transition: 1s;
        transform: translateY(-100vh);
        position: absolute;
        left: 5%;
        bottom: 0vh;
        width: 35%;
    }

    .slider-house {
        transition: 1s;
        transform: translateY(-100vh);
        width: 18%;
        position: absolute;
        left: 15%;
        bottom: -10%;
    }

    .slider-sun {
        transition: 1.5s;
        transform: translateY(-100vh);
        width: 5%;
        position: absolute;
        left: 15%;
        bottom: 40%;
    }

    .slider-birds1 {
        transition: 1s;
        transform: translateX(-20%);
        opacity: 0%;

        width: 5%;
        position: absolute;
        left: 7%;
        bottom: 40%;
    }

    .slider-birds2 {
        transition: 1s;
        transform: translateX(20%);
        width: 5%;
        opacity: 0%;

        position: absolute;
        left: 30%;
        bottom: 50%;
    }

    @media (max-width: 1200px) {
        .slider-house {
            transition: 0.5s;
            width: 18%;
            position: absolute;
            left: 15%;
            bottom: -5%;
        }

        .slider-sun {
            transition: 0.5s;
            width: 5%;
            position: absolute;
            left: 15%;
            bottom: 20%;
        }

        .slider-birds1 {
            transition: 0.5s;
            width: 5%;
            position: absolute;
            left: 9%;
            bottom: 30%;
        }

        .slider-birds2 {
            transition: 0.5s;
            width: 5%;
            position: absolute;
            left: 30%;
            bottom: 30%;
        }

    }


</style>
