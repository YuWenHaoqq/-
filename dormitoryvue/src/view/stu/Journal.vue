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
                                         label="日期"
                                         width="180"></el-table-column>
                        <el-table-column prop="name"
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
                                   :total="50"></el-pagination>
                </el-col>
            </el-row>


            <el-row class="smallinterval">
                <el-col :offset="2" :span="20">
                    <h4>未签到记录</h4>
                </el-col>
            </el-row>
            <el-row>
                <el-col :offset="2" :span="20">
                    <el-table :data="tableData"
                              stripe
                              class="smallinterval"
                              style="width: 100%">
                        <el-table-column prop="date"
                                         label="日期"
                                         width="180"></el-table-column>
                        <el-table-column prop="name"
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
                                   :total="50"></el-pagination>
                </el-col>
            </el-row>


            <el-row>
                <el-col :offset="2" :span="22">
                    <h4 class="smallinterval">请假记录</h4>
                </el-col>
            </el-row>
            <el-row>
                <el-col :offset="2" :span="20">
                    <el-table :data="tableData"
                              stripe
                              class="smallinterval"
                              style="width: 100%">
                        <el-table-column prop="date"
                                         label="日期"
                                         width="180"></el-table-column>
                        <el-table-column prop="name"
                                         label="名字"
                                         width="180"></el-table-column>
                        <el-table-column prop="issuccess"
                                :formatter="leaveState"
                                         label="状态"
                                         width="180"></el-table-column>
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
        <div><under></under></div>
    </div>
</template>

<script>
    import lace from '@/components/Lace'
    import under from '@/components/Under'
    import '@/static/css/global.css'

    export default {
        name: "Journal",
        components:{
            'lace':lace,
            'under':under
        },data(){
            return {
                message:'日志表',
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
                if(row.issuccess==0){
                    return '正在审批'
                }
                return row.issuccess==1?'✔':'❌';
            }
        }
    }
</script>

<style scoped>


</style>
