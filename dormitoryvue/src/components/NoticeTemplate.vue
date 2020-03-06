<template>
    <div>
        <lace></lace>
        <div class="interval"></div>
        <el-row type="flex" justify="center">
            <el-col :span="20">
                <p class="content" v-text="content"></p>
            </el-col>
        </el-row>
        <div class="interval"></div>
        <under></under>

    </div>
</template>

<script>
    import lace from '@/components/Lace'
    import under from '@/components/Under'

    export default {
        name: "NoticeTemplate",
        components: {
            'lace': lace,
            'under': under
        },
        data() {
            return {
                message: "公告标题",
                content: '那么，面对这突如其来的疫情，我们如果出现了以上心理应激状态，又不知道如何应对该怎么办呢？嘉职院大学生心理健康教育指导中心给你支个招。\n' +
                    '\n' +
                    '                    为了更好的开展新型冠状病毒疫情防控工作，疫情期间，我校专门开通在线心理辅导，为疫情防控期间因疫情产生心理压力、需要心理服务的同学们提供服务。\n' +
                    '\n' +
                    '                    服务方式：\n' +
                    '\n' +
                    '                    1、QQ咨询：1984885390\n' +
                    '\n' +
                    '                    该号提供文字及语音咨询服务。\n' +
                    '\n' +
                    '                    每天9：00-11：00、14：00-17：00有专职人员在线，加好友时请注明学院、班级、姓名，否则无法通过验证。\n' +
                    '\n' +
                    '                    2、邮件咨询：jzyxlzx@163.com\n' +
                    '\n' +
                    '                    如果感觉情况不是特别紧急，也可以将问题以邮件形式发送给我们，我们会定时查收邮件并及时回复。\n' +
                    '\n' +
                    '                    3、QQ群服务：（加QQ后进群）\n' +
                    '\n' +
                    '                    我们为有需要的同学建立了QQ群，在群里有相似问题和压力的同学可以分享感受，彼此支持，但发言由管理员进行监管，请知悉。\n' +
                    '\n' +
                    '                    备注：\n' +
                    '\n' +
                    '                    ①由于当前是疫情防控关键时期，服务范围仅限于疫情防控的心理辅导；\n' +
                    '\n' +
                    '                    ②为提高服务效率，主要通过QQ语音方式开展咨询服务；\n' +
                    '\n' +
                    '                    ③每次服务一般控制在20—30分钟，特殊情况由咨询师根据实际情况增减服务时间；\n' +
                    '\n' +
                    '                    ④心理服务严格遵守心理咨询职业伦理规范，对于需要专业精神卫生机构诊断、治疗的情形，我们将提供转诊建议，进一步的诊疗由服务对象与专业精神卫生机构对接。\n' +
                    '\n' +
                    '                    你也可以尝试拨打国家精神卫生项目办紧急汇总更新了目前以各省公立精神卫生医疗机构和精神卫生防治技术管理机构为主开通的300余条免费心理援助热线：（https://mp.weixin.qq.com/s/Cw9WrWPb_wzN999VLQ5VjA可打开链接查看）。\n'
            }
        },
        methods: {
            getMessage() {
                return this.message;
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
