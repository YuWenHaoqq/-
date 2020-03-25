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
                <el-table :data="approvalLeaveData"
                          stripe
                          @selection-change="selectChange"
                          class="smallinterval"
                          style="width: 100%">
                    <el-table-column prop="startTime"
                                     label="开始时间"
                                     width="180"></el-table-column>
                    <el-table-column prop="endTime"
                                     label="结束时间"
                                     width="180"></el-table-column>
                    <el-table-column prop="leaveTime"
                                     label="请假时长"
                                     width="180"></el-table-column>
                    <el-table-column prop="stuName"
                                     label="名字"
                                     width="180"></el-table-column>
                    <el-table-column prop="leaveDes"
                                     label="请假理由"
                                     width="180"></el-table-column>
                    <el-table-column :formatter="approvalLeave"
                                     label="状态"
                                     width="180"></el-table-column>
                    <el-table-column fixed="left"
                                     width="55"
                                     type="selection"></el-table-column>
                </el-table>
            </el-col>
        </el-row>
        <el-row>
            <el-col :offset="2" :span="2">
                <el-button type="success" class="btn" @click="updateLeave(1)">同意</el-button>
            </el-col>
            <el-col :offset="1" :span="2">
                <el-button type="danger" class="btn" @click="updateLeave(2)">不同意</el-button>
            </el-col>

        </el-row>
    </div>
    <div>
        <under></under>
    </div>

</div>
</template>

<script>
    import lace from '@/components/Lace'
    import under from '@/components/Under'
    import '@/static/css/global.css'
    import {get} from "@/util/HttpUtil";
    import {post} from "@/util/HttpUtil";

    export default {
        name: "Approval",
        components:{
            'lace':lace,
            'under':under
        },
        created(){
            this.getApprovalLeave()
        },
        data(){
            return{
                message:'请假审批',
                page:1,
                total:0,
                approvalLeaveData:[],
                multipleSelection:[]
            }
        },
        methods:{
            selectChange(val){
                this.multipleSelection=[]
                let that=this
                val.forEach(function (item) {
                    that.multipleSelection.push(item.leaveHistoryId)
                })
            },
            getMessage(){
                return this.message
            },
            approvalLeave(){
                return '正在审批'
            },
            handleEdit(index, row) {
                window.console.log(index, row);
            },
            handleDelete(index, row) {
                window.console.log(index, row);
            },
            getApprovalLeave(){
                get("/api/teacher/leaveByTea",{teaId:sessionStorage.getItem("teaId")})
                .then(res=>{
                    this.approvalLeaveData=res.data
                })
            },
            updateLeave(type){
                post("/api/teacher/approvalLeave",{historyId:this.multipleSelection,type:type})
                .then(res=>{
                    this.$notify({
                        title:"消息",
                        message:res.message
                    })
                    this.getApprovalLeave()
                }).catch(err=>{
                    this.$notify({
                        title:"消息",
                        message:err.message
                    })
                })
            }
        }
    }
</script>

<style scoped>
    .btn{
        width: 100%;
        text-align: center;
    }

</style>
