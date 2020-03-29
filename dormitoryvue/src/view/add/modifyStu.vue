<template>
    <div>
        <div class="content">
            <lace></lace>
            <el-row>
                <el-col :offset="2" :span="20">
                    <el-table :data="tableData.filter(data=>!search ||data.stuName.toLowerCase().includes(search.toLowerCase()))
                                                .slice((currentPage-1)*pageSize,currentPage*pageSize)"
                              style="width: 100%">
                        <el-table-column
                                label="学生学号"
                                prop="stuId"></el-table-column>
                        <el-table-column
                                label="班级"
                                prop="className"></el-table-column>
                        <el-table-column
                                label="学生名字"
                                prop="stuName"></el-table-column>
                        <el-table-column
                                label="学生性别"
                                :formatter="sexFormatter"
                                prop="stuSex"></el-table-column>
                        <el-table-column
                                label="密码"
                                prop="password"></el-table-column>
                        <el-table-column
                                label="出生年月"
                                prop="birthday"></el-table-column>
                        <el-table-column
                                label="教师名字"
                                prop="teacherName"></el-table-column>
                        <el-table-column
                                label="寝室编号"
                                prop="dormName"></el-table-column>
                        <el-table-column
                                width="150"
                                fixed="left"
                                align="right">
                            <template slot="header" slot-scope="scope">
                                <el-input
                                        v-model="search"
                                        size="mini"
                                        placeholder="输入学生姓名搜索"/>
                            </template>
                            <template slot-scope="scope">
                                <el-button
                                        size="mini"
                                        @click="clickStuForm(scope.row)">修改
                                </el-button>
                                <el-button
                                        size="mini"
                                        type="danger"
                                        @click="del(scope.row)">删除
                                </el-button>
                            </template>
                        </el-table-column>
                    </el-table>

                </el-col>
            </el-row>


        </div>
        <div>
            <el-row>
                <el-col :offset="18">
                    <div class="block">
                        <el-pagination
                                :current-page="currentPage"
                                layout="prev, pager, next"
                                @current-change="currentChange"
                                :total="total">
                        </el-pagination>
                    </div>
                </el-col>
            </el-row>
            <under></under>
        </div>
        <el-dialog title="" :visible.sync="dialogshow" v-if="dialogshow">
            <stuForm ref="stuForm"></stuForm>
        </el-dialog>

    </div>
</template>

<script>
    import lace from '@/components/Lace'
    import under from '@/components/Under'
    import stuForm from '@/components/StuForm'
    import '@/static/css/global.css'
    import {paramsPost} from "@/util/HttpUtil";

    export default {
        name: "modifyStu",
        components: {
            'lace': lace,
            'under': under,
            'stuForm': stuForm
        }, created() {
            this.getStuData()
        },
        data() {
            return {
                message: '学生表',
                dialogshow: false,
                tableData: [],
                search: '',
                pageSize: 10,
                total: 1,
                currentPage: 1,
                rowMessage:''
            }
        },
        methods: {
            del(row){
                this.$confirm('此操作将永久删除该条信息, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    paramsPost('/api/admin/delStu',{stuId:row.stuId}).then(res=>{
                        this.$message({
                            type: 'success',
                            message: res.message
                        });
                        this.getStuData()
                    }).catch(err=>{
                        this.$message({
                            type:'error',
                            message: err.message
                        });
                    })

                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            closeDialogshow(){
                this.dialogshow=false

            },
            getRowMessage(){
              return this.rowMessage
            },
            getMessage() {
                return this.message
            },
            currentChange(val) {
                this.currentPage = val

            },
            sexFormatter(row) {
                if (row.stuSex === 0) {
                    return '女'
                } else if (row.stuSex === 1) {
                    return '男'
                } else {
                    return '中性';
                }

            },
            clickStuForm(row) {
                this.rowMessage=row
                this.dialogshow = true
            },
            getStuData() {

                paramsPost('/api/admin/getAllStu').then(res => {
                    this.tableData = res.data
                    this.total = this.tableData.length
                }).catch(() => {
                    this.$message({
                        type: 'error',
                        message: '刷新页面重试'
                    })
                })

            }
        }
    }
</script>

<style scoped>

</style>
