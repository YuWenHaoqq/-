<template>
    <div>
        <lace ref="lace"></lace>
        <div class="interval"></div>
        <el-row type="flex" justify="center">
            <el-col :span="20">
                <p class="content" v-text="notice.content"></p>
            </el-col>
        </el-row>
        <div class="interval"></div>
        <under></under>

    </div>
</template>

<script>
    import lace from '@/components/Lace'
    import under from '@/components/Under'
    import {paramsPost} from "@/util/HttpUtil";

    export default {
        name: "NoticeTemplate",
        components: {
            'lace': lace,
            'under': under
        },
        created(){
            this.initNotice()
        },
        data() {
            return {
                notice:{
                    noticeTitle:'',
                    content:''
                }
            }
        },
        methods: {
            getMessage() {
                return this.notice.noticeTitle
            },
            initNotice(){
                let id=this.$parent.$parent.getSelectNotice();
                paramsPost("/api/notice/getNoticeContent",{id:id})
                .then(res=>{
                    this.notice=res.data
                    this.$refs.lace.setMessage(this.notice.noticeTitle)
                    this.$refs.lace.setDes(this.notice.noticeDes)
                }).catch(err=>{
                    this.$message(err.message)
                })
            }
        }
    }
</script>

<style scoped>
    .content {
        white-space: pre-line;
        /*font-size: 1.3rem;*/
    }

    .interval {
        height: 85px;
    }
</style>
