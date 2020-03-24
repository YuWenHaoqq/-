<template>
    <div>

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
    </div>
</template>

<script>
    import {get} from "@/util/HttpUtil";

    export default {
        name: "UnsignTable",
        data() {
            return {
                unsignTotal: 0,
                unsignCurrent: 1,
                unsignData: [],
                unsignUrl: '',
                unsignParams: {}
            }
        }, created() {
            this.initUnsign()
        },
        methods: {
            initUnsign(){
                let params=this.$parent.getUnsignParams()
                this.unsignUrl=params.url
                this.unsignParams=params.params
                this.getUnsignMessage()
            },
            getUnsignMessage() {
                this.unsignParams.page = this.unsignCurrent
                get(this.unsignUrl, this.unsignParams)
                    .then(res => {
                        this.unsignTotal = res.data.total;
                        this.unsignData = res.data.list
                    })
            },
            signFailed() {
                return '❌'
            },
        }
    }
</script>

<style scoped>

</style>
