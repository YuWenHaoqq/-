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
                <el-col :xs="{span: 6, offset: 14}" :sm="{span: 5, offset: 17}" :md="{span: 3, offset: 17}" :lg="{span: 2, offset: 18}" :xl="{span: 2, offset: 18}">
                    <el-button class="btn" @click="signin">打卡</el-button>
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
    import {paramsPost} from "@/util/HttpUtil";

    export default {
        name: "SignIn",
        components: {
            'lace': lace,
            'under': under
        },
        created(){
            this.signMouth()
        },
        data() {
            return {
                // value: new Date(),
                message: '签到页面',
                signRanage: ["3-9", "3-10", "3-10", "3-10"]
            }
        },
        methods: {
            getMessage() {
                return this.message
            },
            signin(){
                paramsPost('/api/student/signin',{stuId:sessionStorage.getItem('stuId')}).then(res=>{
                    this.$message({
                        message: res.message,
                        type: 'success'
                    });
                    this.signMouth()
                }).catch(err=>{
                    this.$message(err.message);
                })
            },
            signMouth(){
                paramsPost('/api/student/signMonth',{stuId:sessionStorage.getItem('stuId')}).then(res=>{
                    this.signRanage=res.data
                })
                    .catch(err=>{
                    this.$message(err.message);
                })

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
