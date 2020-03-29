<template>
    <div class="bar">
        <el-row>
            <el-col :xs="{span:3,offset:16}" :sm="{span:3,offset:16}" :md="{span:2,offset:18}"
                    :lg="{span:2,offset:18}" :xl="{span:2,offset:19}" class="text">{{name}}

            </el-col>
            <el-col :xs="{span:3,offset:1}" :sm="{span:3,offset:1}" :md="{span:2,offset:1}"
                    :lg="{span:2,offset:1}" :xl="{span:1,offset:1}" class="btnbg">
                <el-button  class="btn" type="info" @click="loginOut">登出</el-button>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    export default {
        name: "LoginBar",
        created(){
            this.initData();
        },data(){
            return{
                name:''
            }
        },methods:{
            initData(){
                this.name=sessionStorage.getItem('name')
            },
            loginOut(){
                sessionStorage.removeItem('admin')
                sessionStorage.removeItem('teaId')
                sessionStorage.removeItem('stuId')
                this.$router.push({path:'/'})

            }
        }
    }
</script>

<style scoped>
    .bar{
        height: 50px;
    }
    .btn{
        margin-top: 5px;
        height: 40px;
        width: 100%;
    }
    .text{
        display: flex;
        /*justify-content: center;*/
        align-items: center;
        /*flex-direction: column-reverse;*/
        justify-content: flex-end;
        height: 50px;
        /*border: 1px solid red;*/
    }
    .btnbg{
        display: flex;
        align-items: center;
        /*border: 1px solid red;*/
    }

</style>
