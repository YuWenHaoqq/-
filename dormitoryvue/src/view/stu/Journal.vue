<template>
    <div>
        <div class="content">
            <lace></lace>
            <el-row>
                <el-col :offset="2" :span="20">

                </el-col>
            </el-row>
        </div>
        <div><under></under></div>
    </div>
</template>

<script>
    import lace from '@/components/Lace'
    import under from '@/components/Under'
    import '@/static/css/global.css'

    export default {
        name: "Journal",
        components:{
            'lace':lace,
            'under':under
        },data(){
            return {
                message:'日志表'
            }
        },methods:{
            getMessage(){
                return this.message
            }
        }
    }
</script>

<style scoped>

</style>
