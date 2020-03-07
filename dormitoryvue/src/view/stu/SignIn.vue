<template>
    <div>
        <div class="content">
            <lace></lace>
            <el-row type="flex" justify="center">
                <el-col :span="20">
                    <el-calendar>
                        <template slot="dateCell"
                                  slot-scope="{date,data}">
                            <p :class="data.isSelected ? 'is-selected' : ''">
                                {{parseInt(data.day.split('-').slice(2))}} {{
                                signRanage.indexOf(data.day.split('-').slice(1).map(Number).join("-"))!==-1 ? '✔️' :
                                ''}}
                            </p>
                        </template>
                    </el-calendar>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="2" :offset="18">
                    <el-button class="btn">打卡</el-button>
                </el-col>
            </el-row>
        </div>
        <div class="font">
                    因为技术原因只显示本月的签到结果
            <under></under>
        </div>
    </div>
</template>

<script>
    import lace from '@/components/Lace'
    import under from '@/components/Under'
    import '@/static/css/global.css'

    export default {
        name: "SignIn",
        components: {
            'lace': lace,
            'under': under
        },
        data() {
            return {
                // value: new Date(),
                message: '签到页面',
                signRanage: ['3-1', '3-2', '3-3', '3-4', '3-6']
            }
        },
        methods: {
            getMessage() {
                return this.message
            }
        }
    }
</script>

<style scoped>
    .btn {
        width: 100%;
    }

    .font{
        font-size: 12px;
        text-align: right;
        color: #8CC7B5;
    }
</style>
