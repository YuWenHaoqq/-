// 实现代理
module.exports = {
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
