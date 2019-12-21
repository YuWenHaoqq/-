<template>
    <div>
        <section>
            <div class="error-page-inner">
                <div class="container-fluid">
                        <div class="text-center">
                            <div class="error-image">
                                <img class="img-responsive" src="../../assets/image/404.png" alt="">
                            </div>
                            <h2>网页丢失</h2>
                            <p>您要查找的页面的名称可能已被删除。</p>
                            <br>
                            <el-button @click="backIndex" class="btn">返回首页</el-button>
                        </div>
                </div>
            </div>
        </section>
    </div>

</template>

<script>
    import route from '@/router'

    export default {
        name: "nopage",
        methods: {
            backIndex() {
                route.push({
                    path: '/'
                })
            }

        }
    }
</script>

<style scoped>
    .container-fluid {
        text-align: center;
        background: url("../../assets/image/404-bg.jpg") repeat;
        height: 241px;
    }

    .error-page-inner {
        padding: 50px 25px;
        height: 100%;
        min-height: 100%;
        width: 100%;
        color: #686868;
        font-size: 18px;
        font-weight: 300;
        padding: 100px 0;
    }
    .img-responsive{
        margin:50px 0px;
    }

    .btn:hover{
        background-color: #A0EEE1;
        color: white;

    }



</style>
