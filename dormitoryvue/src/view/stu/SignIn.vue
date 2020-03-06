<template>
    <div>
        <lace></lace>
        <el-row type="flex" justify="center">
            <el-col :span="20">
                <el-calendar v-model="value"></el-calendar>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    import lace from '@/components/Lace'
    export default {
        name: "SignIn",
        components: {
          'lace':lace
        },
        data() {
            return {
                value: new Date(),
                message: '签到页面'
            }
        },
        methods: {
            getMessage(){
                window.console.log(new Date())
                return this.message
            }
        }
    }
</script>

<style scoped>

</style>
