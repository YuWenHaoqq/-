<template>
    <div>
        <div class="content">
            <lace></lace>
        </div>
        <div><under></under></div>
    </div>
</template>

<script>
    import lace from '@/components/Lace'
    import under from '@/components/Under'
    import '@/static/css/global.css'
    export default {
        name: "modifyTea",
        components:{
            'lace':lace,
            'under':under
        },
        data(){
            return{
                message:'教师表'
            }
        },
        methods:{
            getMessage(){
                return this.message
            }
        }
    }
</script>

<style scoped>

</style>
