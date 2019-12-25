export const myLocalStorage = {
    Cache: {
        /**
         * 总容量5M
         * 存入缓存，支持字符串类型、json对象的存储
         * 页面关闭后依然有效 ie7+都有效
         * @param key 缓存key
         * @param stringVal
         * @time 数字 缓存有效时间（秒） 默认60s
         * 注：localStorage 方法存储的数据没有时间限制。第二天、第二周或下一年之后，数据依然可用。不能控制缓存时间，故此扩展
         * */
        put:function (key,stringVal,time) {
            try{
                if (!localStorage){
                    return false
                }
                if (!time||isNaN(time)){time=60}
                let cacheExpireDate=(new Date()-1)+time*1000
                let cacheVal={val:stringVal,exp:cacheExpireDate}
                //存入缓存值
                localStorage.setItem(key,JSON.stringify(cacheVal))
            }catch (e) {}
        },
    //    获取缓存
        get:function (key) {
            try {
                if (!localStorage){return false}
                let cacheVal=localStorage.getItem(key)
                let result=JSON.parse(cacheVal)
                let now =new Date()-1
                //缓存不存在
                if(!result){return null}
                //缓存过期
                if(now>result.exp){
                    this.remove(key)
                    return ""
                }
                return result.val
            }catch (e) {
                this.remove(key)
                return null
            }

        },
        remove:function (key) {
            if (!localStorage){return false}
            localStorage.removeItem(key)
        },
        clear:function () {
            if (!localStorage){return false}
            localStorage.clear()
        }
    }
}
