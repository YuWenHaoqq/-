// 实现代理
module.exports = {
    // css:{
    //     loaderOptions:{
    //         postcss:{
    //         //    这是rem适配的配置 注意:remUnit在这里要根据lib_flexible的规则来配置
    //             plugin:[
    //                 require("postcss-px2rem")({
    //                     remUnit:200
    //                 })
    //             ]
    //         }
    //     }
    // },
//    build输出目录
    outputDir: 'dist',
    //静态资源目录(js,css,image)
    assetsDir: 'assets',
//    是否开启eslint
    lintOnSave: false,
    devServer: {
        //是否开启自动弹出浏览器页面
        open: false,
        host: 'localhost',
        port: '8080',
        https: false,
        //是否开启热更新
        hotOnly: false,
        proxy: {
            '/api': {
                //API服务器地址
                target: 'http://localhost:9000',
                // target: 'http://49.235.53.74:9000',
                //    代理websockets
                ws: true,
                //    虚拟站点需要更改origin
                changeOrigin: true,
                pathRewrite: {
                    '^/api': ''
                }
            }
        }
    }
}
