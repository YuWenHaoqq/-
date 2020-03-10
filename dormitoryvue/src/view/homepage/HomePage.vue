<template>
    <div>
        <div class="content">
            <lace ref="lace"></lace>

            <el-row class="rowInterval">
                <el-col :offset="2" :span="20">
                    <div>
                        <el-row  v-for="item1 in noticeShwoList">
                            <el-col :span="8" :offset="2" v-for="item of item1">
                                <div class="notice" @click="dialogshow=true">
                                    <img :src='item.imgUrl'>
                                    <h4>{{item.noticeTitle}}</h4>
                                    <p>{{item.noticeDes}}</p>
                                </div>
                            </el-col>
                        </el-row>
                    </div>
                </el-col>
            </el-row>

        </div>
        <el-dialog title="" :visible.sync="dialogshow">
            <notice></notice>
        </el-dialog>
        <div>
            <el-row>
                <el-col :offset="16" :span="8">
                    <el-pagination
                            layout="prev, pager, next"
                            :current-page.sync="obj.page"
                            :page-size="6"
                            @current-change="getNotice"
                            :total="this.noticeTotal">
                    </el-pagination>
                </el-col>
            </el-row>
            <under></under>
        </div>


    </div>
</template>

<script>
    import lace from '@/components/Lace'
    import under from '@/components/Under'
    import notice from '@/components/NoticeTemplate'
    import '@/static/css/global.css'
    import {paramsPost} from "@/util/HttpUtil";

    export default {
        name: "HomePage",
        data() {
            return {
                message: '宿舍公告',
                imageurl1: 'http://www.jxvtc.edu.cn/UserFiles/Image/20200202/20200202131747_52.png',
                dialogshow: false,
                dialogTableVisible: false,
                noticeTotal: 0,
                obj: {
                    page: 1
                },
                noticeList: [],
                noticeShwoList: []
            }
        },
        components: {
            'lace': lace,
            'under': under,
            'notice': notice
        },
        created() {
            this.getNotice()

        },
        methods: {
            getMessage() {
                return this.message;
            },
            getNotice(){
                paramsPost('/api/notice/getNotice', this.obj).then(res => {

                    this.noticeTotal = res.data.pageNumber
                    this.noticeList = res.data.noticeVos
                    this.noticeShwoList=[]
                    for (let i =0;i<this.noticeList.length;i=i+2){
                        if ((i+1)<this.noticeList.length) {
                            this.noticeShwoList[i] = [this.noticeList[i], this.noticeList[i + 1]]
                        }else{
                            this.noticeShwoList[i] = [this.noticeList[i]]
                        }
                    }
                })

            }
        }

    }
</script>

<style scoped>
    .notice {
        width: 100%;
        cursor: pointer;
    }

    .notice img {
        width: 100%;
    }

    .notice h4 {
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
        width: 100%;
    }

    .rowInterval {
        margin-bottom: 85px;
    }

    h4 {
        text-align: center;

    }

    p {
        text-indent: 2em;
        overflow: hidden;
        text-overflow: ellipsis;
        display: -webkit-box;
        -webkit-box-orient: vertical;
        -webkit-line-clamp: 3;
    }
</style>
