<template>
    <div>
        <div class="content">
            <lace ref="lace"></lace>
            <el-row class="rowInterval">
                <el-col :offset="2" :span="20">
                    <div>
                        <el-row type="flex" justify="space-around">
                            <el-col :span="8">
                                <div class="notice" @click="dialogshow=true">
                                    <el-image class="noticeImg" :src="imageurl1"></el-image>
                                    <h4>我校新冠肺炎心理服务上线</h4>
                                    <p>
                                        新型冠状病毒感染的疫情正冲击着我们，很多人会感到害怕、担忧、恐慌、焦虑，还有些人会有身体上的不适感，如胸闷、头痛、失眠、食欲差、不明原因的身体疼痛等，请大家不要担心，这是人们遭遇重大危机事件时的应激反应，是正常的反应。</p>
                                </div>
                            </el-col>


                            <el-col :span="8">
                                <div class="notice">
                                    <el-image class="noticeImg" :src="imageurl1"></el-image>
                                    <h4>我校新冠肺炎心理服务上线</h4>
                                    <p>
                                        新型冠状病毒感染的疫情正冲击着我们，很多人会感到害怕、担忧、恐慌、焦虑，还有些人会有身体上的不适感，如胸闷、头痛、失眠、食欲差、不明原因的身体疼痛等，请大家不要担心，这是人们遭遇重大危机事件时的应激反应，是正常的反应。</p>
                                </div>
                            </el-col>
                        </el-row>
                    </div>
                </el-col>
            </el-row>
<!--            <el-row>-->
<!--                <el-col :offset="2" :span="20">-->
<!--                    <div>-->
<!--                        <el-row type="flex" justify="space-around">-->
<!--                            <el-col :span="8">-->
<!--                                <div class="notice" @click="dialogshow=true">-->
<!--                                    <el-image class="noticeImg" :src="imageurl1"></el-image>-->
<!--                                    <h4>我校新冠肺炎心理服务上线</h4>-->
<!--                                    <p>-->
<!--                                        新型冠状病毒感染的疫情正冲击着我们，很多人会感到害怕、担忧、恐慌、焦虑，还有些人会有身体上的不适感，如胸闷、头痛、失眠、食欲差、不明原因的身体疼痛等，请大家不要担心，这是人们遭遇重大危机事件时的应激反应，是正常的反应。</p>-->
<!--                                </div>-->
<!--                            </el-col>-->


<!--                            <el-col :span="8">-->
<!--                                <div class="notice">-->
<!--                                    <el-image class="noticeImg" :src="imageurl1"></el-image>-->
<!--                                    <h4>我校新冠肺炎心理服务上线</h4>-->
<!--                                    <p>-->
<!--                                        新型冠状病毒感染的疫情正冲击着我们，很多人会感到害怕、担忧、恐慌、焦虑，还有些人会有身体上的不适感，如胸闷、头痛、失眠、食欲差、不明原因的身体疼痛等，请大家不要担心，这是人们遭遇重大危机事件时的应激反应，是正常的反应。</p>-->
<!--                                </div>-->
<!--                            </el-col>-->
<!--                        </el-row>-->
<!--                    </div>-->
<!--                </el-col>-->
<!--            </el-row>-->

        </div>
        <!--        <el-footer>456</el-footer>-->
        <div>
            <el-row>
                <el-col :offset="16" :span="8">
                    <el-pagination small layout="prev, pager, next"
                                   :total="50"></el-pagination>
                </el-col>
            </el-row>
            <under></under>
        </div>


        <el-dialog :visible.sync="dialogshow">
            <notice></notice>
        </el-dialog>
    </div>
</template>

<script>
    import lace from '@/components/Lace'
    import under from '@/components/Under'
    import notice from '@/components/NoticeTemplate'
    import '@/static/css/global.css'

    export default {
        name: "HomePage",
        data() {
            return {
                message: '宿舍公告',
                imageurl1: 'http://www.jxvtc.edu.cn/UserFiles/Image/20200202/20200202131747_52.png',
                dialogshow: false
            }
        },
        components: {
            'lace': lace,
            'under': under,
            'notice': notice
        },
        created() {
        },
        methods: {
            getMessage() {
                return this.message;
            }
        }

    }
</script>

<style scoped>
    .notice {
        width: 100%;
        cursor: pointer;
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
