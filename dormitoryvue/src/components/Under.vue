<template>
    <div>
        <el-row>
            <el-col :span="24">
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
        background: url("../assets/image/under.png") center repeat-x;
    }
</style>
