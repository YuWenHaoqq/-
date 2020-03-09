<template>
    <div>
        <el-row>
            <el-col :offset="2" :span="20">
                <h4>签到成功日志</h4>
            </el-col>
        </el-row>
        <el-row>
            <el-col :offset="2" :span="20">
                <el-table :data="tableData"
                          stripe
                          style="width: 100%">
                    <el-table-column prop="date"
                                     label="日期"
                                     width="180"></el-table-column>
                    <el-table-column prop="name"
                                     label="名字"
                                     width="180"></el-table-column>
                    <el-table-column prop="issuccess"
                                     :formatter="formatter"
                                     label="状态"
                                     width="180"></el-table-column>

                </el-table>

            </el-col>
        </el-row>
        <el-row>
            <el-col :offset="16" :span="8">
                <el-pagination small layout="prev, pager, next"
                               :total="50"></el-pagination>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    export default {
        name: "Table",
        data(){
            return{
                tableData: [{
                    date: '2016-05-02',
                    name: '王小虎',
                    issuccess: '1'
                }, {
                    date: '2016-05-02',
                    name: '王小虎',
                    issuccess: '1'
                }, {
                    date: '2016-05-02',
                    name: '王小虎',
                    issuccess: '1'
                }, {
                    date: '2016-05-02',
                    name: '王小虎',
                    issuccess: '1'
                }]
            }
        }
    }

</script>

<style scoped>

</style>
