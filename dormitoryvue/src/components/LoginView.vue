<template>
    <div>
        <section ref="silder" class="home-slider">
            <div class="main-slider">
                <img ref="hill" src="@/assets/image/hill.png" class="slider-hill" alt="slider image">
                <img ref="house" src="@/assets/image/house.png" class="slider-house" alt="slider image">
                <img ref="sun" src="@/assets/image/sun.png" class="slider-sun" alt="slider image">
                <img ref="birds1" src="@/assets/image/birds1.png" class="slider-birds1" alt="slider image">
                <img ref="birds2" src="@/assets/image/birds2.png" class="slider-birds2" alt="slider image">
            </div>
        </section>
    </div>
</template>

<script>

    export default {
        name: "LoginView",
        mounted() {
            // window.console.log(this.$refs.hill)
            // this.$refs.hill.style.bottom=0
            // document.getElementById('hill').style.bottom=0
            this.animation()
        },
        methods: {
            animation() {
                let that = this
                setTimeout(function () {
                    that.$refs.hill.style.transform = 'translateY(0vh)'
                },0)
                setTimeout(function () {
                    that.$refs.house.style.transform = 'translateY(0vh)';
                    that.$refs.sun.style.transform = 'translateY(0vh)'
                }, 1000);
                setTimeout(function () {
                    that.$refs.birds1.style.transform = 'translateX(0)';
                    that.$refs.birds1.style.opacity = '1';
                    that.$refs.birds2.style.transform = 'translateX(0)';
                    that.$refs.birds2.style.opacity = '1';
                },2500)
                // that.$refs.birds1.style.transform = 'translateX(0)';

            }
        }
    }
</script>

<style scoped>
    .home-slider {
        background: url("../assets/image/slider-bg.png") repeat-x bottom;
        width: 100%;
        height: 273px;
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
    }

    .slider-hill {
        transition: 1s;
        transform: translateY(-100vh);
        position: absolute;
        left: 5%;
        bottom: 0vh;
        width: 35%;
    }

    .slider-house {
        transition: 1s;
        transform: translateY(-100vh);
        width: 18%;
        position: absolute;
        left: 15%;
        bottom: -10%;
    }

    .slider-sun {
        transition: 1.5s;
        transform: translateY(-100vh);
        width: 5%;
        position: absolute;
        left: 15%;
        bottom: 40%;
    }

    .slider-birds1 {
        transition: 1s;
        transform: translateX(-20%);
        opacity:0%;

        width: 5%;
        position: absolute;
        left: 7%;
        bottom: 40%;
    }

    .slider-birds2 {
        transition: 1s;
        transform: translateX(20%);
        width: 5%;
        opacity:0%;

        position: absolute;
        left: 30%;
        bottom: 50%;
    }

    @media (max-width: 1200px) {
        .slider-house {
            transition: 0.5s;
            width: 18%;
            position: absolute;
            left: 15%;
            bottom: -5%;
        }

        .slider-sun {
            transition: 0.5s;
            width: 5%;
            position: absolute;
            left: 15%;
            bottom: 20%;
        }

        .slider-birds1 {
            transition: 0.5s;
            width: 5%;
            position: absolute;
            left: 9%;
            bottom: 30%;
        }

        .slider-birds2 {
            transition: 0.5s;
            width: 5%;
            position: absolute;
            left: 30%;
            bottom: 30%;
        }


    }

</style>
