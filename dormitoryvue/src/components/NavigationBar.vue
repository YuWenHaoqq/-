<template>
    <div class="patch">
        <el-row class="tac">
            <el-col :span="24">
                <h5>宿舍签到系统</h5>
                <el-menu router
                        :default-active="$route.name"
                        class="el-menu-vertical-demo">
                    <el-menu-item index="homepage">
                        <i class="el-icon-menu"></i>
                        <span slot="title">首页</span>
                    </el-menu-item>
                    <el-submenu index="2" ref="stu" :disabled="isStu()">
                        <template slot="title">
                            <i class="el-icon-user"></i>
                            <span>学生</span>
                        </template>
                        <el-menu-item-group>
                            <el-menu-item index="stuSignin">签到</el-menu-item>
                            <el-menu-item index="leave">请假</el-menu-item>
                            <el-menu-item index="journal">日志表</el-menu-item>
                        </el-menu-item-group>
                    </el-submenu>

                    <el-submenu index="3" ref="tea" :disabled="isTea()">
                        <template slot="title">
                        <i class="el-icon-user-solid"></i>
                        <span>教师</span>
                        </template>
                        <el-menu-item-group>
                            <el-menu-item index="approval">审批请假</el-menu-item>
                            <el-menu-item index="checkSign">查看签到状态</el-menu-item>
                            <el-menu-item index="updateNotice">更新公告</el-menu-item>
                        </el-menu-item-group>
                    </el-submenu>

                    <el-submenu index="4" ref="admin">
                        <template slot="title">
                            <i class="el-icon-user-solid"></i>
                            <span>管理员</span>
                        </template>
                        <el-menu-item-group>
                            <el-menu-item index="addStu">添加学生和教师</el-menu-item>
                            <el-menu-item index="modifyStu">学生表</el-menu-item>
                            <el-menu-item index="modifyTea">教师表</el-menu-item>
                        </el-menu-item-group>
                    </el-submenu>
                </el-menu>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    export default {
        name: "NavigationBar",
        data(){
            return {
                index: 'home',
            }
        },created(){
        },
        methods: {
            isStu(){
                if (sessionStorage.getItem("stuId")===null){
                    return true
                }
                return false
            },
            isTea(){
                if (sessionStorage.getItem("teaId")===null){
                    return true
                }
                return false
            },

        }
    }
</script>

<style scoped>
    .patch{
        overflow: hidden;
    }
    .title{
        font-size: 1rem;
    }

</style>
