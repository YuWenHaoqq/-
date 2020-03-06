<template>
    <div class="bg">
        <el-row class="title">
            <el-col :span="22" :offset='2'>
<!--                <div style="border: 1px solid red">-->
                    <h1 class="font">{{message}}</h1>
                    <p>{{des}}</p>
<!--                </div>-->

            </el-col>
        </el-row>
    </div>
</template>

<script>
    export default {
        name: "Lace",
        data() {
            return{
                message: '',
                des: ''
            }
        },
        methods: {
        },
        created() {
            this.message=this.$parent.getMessage();
            // this.des=this.$parent.getDes();
        }
    }
</script>

<style scoped>
    .bg {
        display: flex;
        align-items: center;
        background: url("../assets/image/tour-bg.png") repeat-x;
        height: 155px;
    }

    .font {
        color: #404040;
        font-weight: 300;
        /*line-height: 155px;*/
    }
    .title{
        width: 100%;
         }
</style>
