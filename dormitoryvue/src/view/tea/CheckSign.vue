<template>
    <div>
        <div class="content">
            <lace></lace>
            <el-row class="smallinterval">
                <el-col :offset="2" :span="20">
                    <h4>请假记录</h4>
                </el-col>
            </el-row>
            <el-row>
                <el-col :offset="2" :span="22">
                    <div class="search">
                        <el-input class="input" placeholder="请输入学生名" v-model="leaveText"></el-input>
                        <el-button type="info" icon="el-icon-search" circle @click="searchLeave"></el-button>
                    </div>
                </el-col>
            </el-row>
            <leaveTable ref="leave"></leaveTable>

            <el-row class="smallinterval">
                <el-col :offset="2" :span="20">
                    <h4>未签到记录</h4>
                </el-col>
            </el-row>
            <el-row>
                <el-col :offset="2" :span="22">
                    <div class="search">
                        <el-input class="input" placeholder="请输入学生名" v-model="unsignText"></el-input>
                        <el-button type="info" icon="el-icon-search" circle @click="searchUnsign"></el-button>
                    </div>
                </el-col>
            </el-row>
            <unsignTable ref="unsign"></unsignTable>

            <el-row>
                <el-col :offset="2" :span="22">
                    <h4 class="smallinterval">签到记录</h4>
                </el-col>
            </el-row>
            <el-row>
                <el-col :offset="2" :span="22">
                    <div class="search">
                        <el-input class="input" placeholder="请输入学生名" v-model="signText"></el-input>
                        <el-button type="info" icon="el-icon-search" circle @click="searchSign"></el-button>
                    </div>
                </el-col>
            </el-row>
            <signTable ref="sign"></signTable>
        </div>

        <div>
            <under></under>
        </div>
    </div>
</template>

<script>
    import lace from '@/components/Lace'
    import UnsignTable from "@/components/table/UnsignTable";
    import LeaveTable from "@/components/table/LeaveTable";
    import SignTable from "@/components/table/SignTable";
    import under from '@/components/Under'
    import '@/static/css/global.css'
    export default {
        name: "CheckSign",
        components:{
            'lace':lace,
            'under':under,
            'unsignTable':UnsignTable,
            'leaveTable':LeaveTable,
            'signTable':SignTable,

        },
        data(){
            return{
                message:'查询签到状况',
                leaveData:[],
                leaveText:'',
                unsignText:'',
                signText:''
            }
        },
        methods:{
            getMessage(){
                return this.message
            },
            getLeaveParams(){
                return {
                    url:'/api/teacher/getLeaveByTeacher',
                    params:{
                        page: this.signCurrent, size: 5, stuId: this.leaveText,teaId:sessionStorage.getItem("teaId")
                    }
                }
            },
            getUnsignParams() {
                return {
                    url:'/api/teacher/getUnSignByTeacher',
                    params:{
                        page: this.signCurrent, size: 5, stuId: this.unsignText,teaId:sessionStorage.getItem("teaId")
                    }
                };
            },
            getSignParams() {
                return {
                    url:'/api/teacher/getSignByTeacher',
                    params:{
                        page: this.signCurrent, size: 5, stuId: this.signText,teaId:sessionStorage.getItem("teaId")
                    }
                };
            },
            searchLeave(){
                this.$refs.leave.initTable()
            },
            searchUnsign(){
                this.$refs.unsign.initUnsign()
            },
            searchSign(){
                this.$refs.sign.initSign()
            }
        }
    }
</script>

<style scoped>
    .search{
        display: flex;
    }
    .input{
        width: 150px;
        margin-right: 10px;
    }

</style>
