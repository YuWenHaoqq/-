<template>
    <div>
        <el-row>
            <el-col :span="20" :offset="2">
                <div class="bg"></div>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    export default {
        name: "Under",
        data() {
            return {
                imageurl: ''
            }
        }
    }
</script>

<style scoped>
    .bg {
        height: 145px;
        /*text-align: ce;*/
        background: url("../assets/image/under.png") center no-repeat;

    }
</style>
