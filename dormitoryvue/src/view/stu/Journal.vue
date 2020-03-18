<template>
    <div>
        <div class="content">
            <lace></lace>
            <el-row>
                <el-col :offset="2" :span="22">
                    <h4 class="smallinterval">签到记录</h4>
                </el-col>
            </el-row>
            <el-row>
                <el-col :offset="2" :span="20">
                    <el-table :data="signData"
                              stripe
                              class="smallinterval"
                              style="width: 100%">
                        <el-table-column prop="createTime"
                                         label="日期"
                                         width="180"></el-table-column>
                        <el-table-column prop="stuName"
                                         label="名字"
                                         width="180"></el-table-column>
                        <el-table-column :formatter="signSuccess"
                                         label="状态"
                                         width="180"></el-table-column>
                    </el-table>
                </el-col>
            </el-row>
            <el-row class="bigInterval">
                <el-col :offset="16" :span="8">
                    <el-pagination small layout="prev, pager, next"
                                   :page-size="5"
                                   :current-page.sync="signCurrent"
                                   @current-change="getSignMessage"
                                   :total="signTotal"></el-pagination>
                </el-col>
            </el-row>


            <el-row class="smallinterval">
                <el-col :offset="2" :span="20">
                    <h4>未签到记录</h4>
                </el-col>
            </el-row>
            <el-row>
                <el-col :offset="2" :span="20">
                    <el-table :data="unsignData"
                              stripe
                              class="smallinterval"
                              style="width: 100%">
                        <el-table-column prop="createTime"
                                         label="日期"
                                         width="180"></el-table-column>
                        <el-table-column prop="stuName"
                                         label="名字"
                                         width="180"></el-table-column>
                        <el-table-column :formatter="signFailed"
                                         label="状态"
                                         width="180"></el-table-column>
                    </el-table>

                </el-col>
            </el-row>
            <el-row class="bigInterval">
                <el-col :offset="16" :span="8">
                    <el-pagination small layout="prev, pager, next"
                                   :page-size="5"
                                   :current-page.sync="unsignCurrent"
                                   @current-change="getUnsignMessage"
                                   :total="unsignTotal"></el-pagination>
                </el-col>
            </el-row>


            <el-row>
                <el-col :offset="2" :span="22">
                    <h4 class="smallinterval">请假记录</h4>
                </el-col>
            </el-row>
            <el-row>
                <el-col :offset="2" :span="20">
                    <el-table :data="leaveData"
                              stripe
                              class="smallinterval"
                              style="width: 100%">
                        <el-table-column prop="startTime"
                                         label="开始时间"
                                         width="180"></el-table-column>
                        <el-table-column prop="endTime"
                                         label="结束时间"
                                         width="180"></el-table-column>
                        <el-table-column prop="stuName"
                                         label="名字"
                                         width="180"></el-table-column>
                        <el-table-column prop="success"
                                :formatter="leaveState"
                                         label="状态"
                                         width="180"></el-table-column>
                    </el-table>
                </el-col>
            </el-row>
            <el-row class="bigInterval">
                <el-col :offset="16" :span="8">
                    <el-pagination small layout="prev, pager, next"
                                   :page-size="5"
                                   :current-page.sync="leaveCurrent"
                                   @current-change="getLeaveMessage"
                                   :total="leaveTotal"></el-pagination>
                </el-col>
            </el-row>
        </div>
        <div><under></under></div>
    </div>
</template>

<script>
    import lace from '@/components/Lace'
    import under from '@/components/Under'
    import '@/static/css/global.css'
    import {get} from "@/util/HttpUtil";

    export default {
        name: "Journal",
        components:{
            'lace':lace,
            'under':under
        },
        created(){
            this.getSignMessage()
            this.getUnsignMessage()
            this.getLeaveMessage()
        }
        ,data(){
            return {
                message:'日志表',
                signTotal:0,
                signCurrent:1,
                signData:[],
                unsignTotal:0,
                unsignCurrent:1,
                unsignData:[],
                leaveTotal:0,
                leaveCurrent:1,
                leaveData:[],
                tableData: [{
                    date: '2016-05-02',
                    name: '王小虎',
                    issuccess: '0'
                }, {
                    date: '2016-05-02',
                    name: '王小虎',
                    issuccess: '1'
                }, {
                    date: '2016-05-02',
                    name: '王小虎',
                    issuccess: '2'
                }, {
                    date: '2016-05-02',
                    name: '王小虎',
                    issuccess: '1'
                }]
            }
        },methods:{
            getSignMessage(){
                let stuId=sessionStorage.getItem("stuId")
                get('/api/student/getSignMessage',{page:this.signCurrent,size:5,stuId:stuId})
                    .then(res=>{
                    this.signTotal=res.data.total
                    this.signData=res.data.list
                })

            },
            getUnsignMessage(){
              let stuId=sessionStorage.getItem("stuId")
                get('/api/student/getUnSignMessage',{page:this.unsignCurrent,size:5,stuId:stuId})
                    .then(res=>{
                    this.unsignTotal=res.data.total;
                    this.unsignData=res.data.list
                })
            },
            getLeaveMessage(){
                let stuId=sessionStorage.getItem("stuId")
                get('/api/student/getLeave',{page:this.leaveCurrent,size:5,stuId:stuId})
                .then(res=>{
                    this.leaveTotal=res.data.total
                    this.leaveData=res.data.list
                })
            },
            getMessage(){
                return this.message
            },
            signSuccess(){
                return '✔';

            },
            signFailed(){
                return '❌'
            },
            leaveState(row){
                if(row.success===0){
                    return '正在审批'
                }
                return row.success===1?'✔':'❌';
            }
        }
    }
</script>

<style scoped>


</style>
