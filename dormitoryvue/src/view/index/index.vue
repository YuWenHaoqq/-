<template>
<div>
    <el-row>
        <el-col :xs="8" :sm="6" :md="5" :lg="4" :xl="3">
            <navigation></navigation>
        </el-col>
        <el-col :xs="16" :sm="18" :md="19" :lg="20" :xl="21">
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
