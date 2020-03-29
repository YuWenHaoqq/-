<template>
<div>
    <el-row>
        <el-col :sm="5" :md="4" :lg="4" :xl="3">
            <navigation></navigation>
        </el-col>
        <el-col :sm="19" :md="20" :lg="20" :xl="21">
            <login-bar></login-bar>
            <div class="hid">
            <router-view></router-view>
            </div>
        </el-col>
    </el-row>
</div>
</template>

<script>
    import navigation from '@/components/NavigationBar'
    import loginBar from '@/components/LoginBar'

    export default {
        name: "index",
        components: {
            "navigation": navigation,
            "loginBar":loginBar
        },
        created(){
            this.checkLogin()

        },
        methods:{
            checkLogin(){
                if (sessionStorage.getItem('token')===null){
                    this.$router.push({path:'/'})
                }
            }
        }
    }
</script>

<style scoped>

    .hid{
        overflow: hidden;
    }
</style>
