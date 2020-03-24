<template>
    <div>
        <div class="content">
            <lace></lace>

            <signTable></signTable>

            <unsignTable></unsignTable>

            <leaveTable></leaveTable>
        </div>
        <div><under></under></div>
    </div>
</template>

<script>
    import lace from '@/components/Lace'
    import under from '@/components/Under'
    import '@/static/css/global.css'
    import leaveTable from "@/components/table/LeaveTable";
    import SignTable from "@/components/table/SignTable";
    import UnsignTable from "@/components/table/UnsignTable";

    export default {
        name: "Journal",
        components:{
            'lace':lace,
            'under':under,
            'leaveTable':leaveTable,
            'signTable':SignTable,
            'unsignTable':UnsignTable
        },
        created(){
        }
        ,data(){
            return {
                message:'日志表',


            }
        },methods:{
            getMessage(){
                return this.message
            },
            getLeaveParams(){
                return {
                    url:'/api/student/getLeave',
                    params:{
                        page: this.signCurrent, size: 5, stuId: sessionStorage.getItem("stuId")
                    }
                }
            },
            getSignParams(){
                return {
                    url:'/api/student/getSignMessage',
                    params:{
                        page: this.signCurrent, size: 5, stuId: sessionStorage.getItem("stuId")
                    }
                }
            },
            getUnsignParams(){
                return {
                    url:'/api/student/getUnSignMessage',
                    params:{
                    page: this.signCurrent, size: 5, stuId: sessionStorage.getItem("stuId")
                    }
                }
            },



        }
    }
</script>

<style scoped>


</style>
