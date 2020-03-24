<template>
    <div>

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
    </div>
</template>

<script>
    import {get} from "@/util/HttpUtil";

    export default {
        name: "SignTable",
        created() {
            this.initSign()
        },
        data() {
            return {
                signTotal: 0,
                signCurrent: 1,
                signData: [],
                signUrl:'',
                signParams:{}
            }
        },
        methods: {
            initSign(){
                let params=this.$parent.getSignParams()
                this.signUrl=params.url
                this.signParams=params.params
                this.getSignMessage()
            },
            getSignMessage() {
                this.signParams.page=this.signCurrent
                get(this.signUrl, this.signParams)
                    .then(res => {
                        this.signTotal = res.data.total
                        this.signData = res.data.list
                    })

            }, signSuccess() {
                return '✔';

            },
        }
    }
</script>

<style scoped>

</style>
