<template>
    <div>
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
</template>

<script>
    import {get} from "@/util/HttpUtil";

    export default {
        name: "LeaveTable",
        data(){
            return{
                leaveTotal:0,
                leaveCurrent:1,
                leaveData:[],
                leaveUrl:'',
                leaveParams:{}
            }
        },
        created(){
            this.initTable()

        },
        methods:{
            initTable(){
                let params=this.$parent.getLeaveParams()
                this.leaveUrl=params.url
                this.leaveParams=params.params
                this.getLeaveMessage()
            },
            getLeaveMessage(){
                this.leaveParams.page=this.leaveCurrent
                get(this.leaveUrl,this.leaveParams)
                    .then(res=>{
                        this.leaveTotal=res.data.total
                        this.leaveData=res.data.list
                    })
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
