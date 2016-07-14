
# staticfileserver
staticfileserver,是一个用Java写的静态文件服务器。关于静态文件服务器，我觉得[博文共赏：Node.js静态文件服务器实战](http://www.infoq.com/cn/news/2011/11/tyq-nodejs-static-file-server)写的不错，简单易懂，思路清晰，不过使用Nodejs写的。我本人更熟悉Java,所以就用Java写了一个简易版本。
此版本主要实现以下功能：
* 对于请求的资源,若处理成功，返回200
* 对于请求的资源,若服务器上不存在，返回404
* 对于请求的资源,若服务器上处理出错，返回500
* 多线程处理浏览器发出的请求
* MIME类型支持

## License
Apache 






