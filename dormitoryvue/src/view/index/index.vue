<template>
<div>
    <el-row>
        <el-col :span="3">
            <navigation></navigation>
        </el-col>
        <el-col :span="21">
            <router-view></router-view>
        </el-col>
    </el-row>
</div>
</template>

<script>
    import navigation from '@/components/NavigationBar'

    export default {
        name: "index",
        components: {
            "navigation": navigation
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

</style>
