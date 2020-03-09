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
                <el-table :data="tableData"
                          stripe
                          class="smallinterval"
                          style="width: 100%">
                    <el-table-column prop="date"
                                     label="请假时间"
                                     width="180"></el-table-column>
                    <el-table-column prop="time"
                                     label="请假时长"
                                     width="180"></el-table-column>
                    <el-table-column prop="name"
                                     label="名字"
                                     width="180"></el-table-column>
                    <el-table-column prop="des"
                                     label="请假理由"
                                     width="180"></el-table-column>
                    <el-table-column :formatter="approvalLeave"
                                     label="状态"
                                     width="180"></el-table-column>
                    <el-table-column label="操作"
                                     fixed="left"
                                     width="180">
                        <template slot-scope="scope">
                            <el-button
                                    size="mini"
                                    @click="handleEdit(scope.$index, scope.row)">同意</el-button>
                            <el-button
                                    size="mini"
                                    type="danger"
                                    @click="handleDelete(scope.$index, scope.row)">不同意</el-button>

                        </template>
                    </el-table-column>
                </el-table>
            </el-col>
        </el-row>
        <el-row class="bigInterval">
            <el-col :offset="16" :span="8">
                <el-pagination small layout="prev, pager, next"
                               :total="50"></el-pagination>
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

    export default {
        name: "Approval",
        components:{
            'lace':lace,
            'under':under
        },
        data(){
            return{
                message:'请假审批',
                tableData: [{
                    date: '2016-05-02 12:12:12 至 2016-05-02 12:12:12',
                    name: '王小虎',
                    time: '3天',
                    issuccess: '0',
                    des:'撒娇后发酵课后发送娇后发酵课后发送娇后发酵课后发送娇后发酵课后发送娇后发酵课后发送='
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
        },
        methods:{
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
            }
        }
    }
</script>

<style scoped>

</style>
