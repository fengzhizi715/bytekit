# bytekit

[![@Tony沈哲 on weibo](https://img.shields.io/badge/weibo-%40Tony%E6%B2%88%E5%93%B2-blue.svg)](http://www.weibo.com/fengzhizi715)
[![License](https://img.shields.io/badge/license-Apache%202-lightgrey.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)

# 功能特点：

* 支持多种方式创建 Bytes：byte数组、字符串、File、InputStream
* 支持字节数组、ByteBuffer 的操作
* 支持 Immutable 对象：ByteArrayBytes、ByteBufferBytes
* 支持 Transformer: 内置 copy、contact、reverse、xor、and、or、not，也支持自定义 Transformer
* 支持 Hash: 内置 md5、sha1、sha256
* 支持 Hmac加密: 内置 HmacMD5、HmacSHA1、HmacSHA256
* 支持转换成十六进制字符串、十六进制字符串转换成二进制字节数组
* 支持 mmap 常用读写操作：readByte/writeByte、readBytes/writeBytes、readInt/writeInt、readLong/writeLong、readDouble/writeDouble、readObject/writeObject
* 支持对象的序列化、反序列化、深拷贝
* 支持将 Bytes 写入到输出流、文件中
* core、mmap 模块不依赖于任何第三方库

# 最新版本

模块|最新版本
---|:-------------:
bytekit-core|[ ![Download](https://api.bintray.com/packages/fengzhizi715/maven/bytekit-core/images/download.svg) ](https://bintray.com/fengzhizi715/maven/bytekit-core/_latestVersion)|
bytekit-mmap|[ ![Download](https://api.bintray.com/packages/fengzhizi715/maven/bytekit-mmap/images/download.svg) ](https://bintray.com/fengzhizi715/maven/bytekit-mmap/_latestVersion)|

# 下载：


bytekit-core

```groovy
implementation 'com.safframework.bytekit:bytekit-core:1.2.6'
```

bytekit-mmap

```groovy
implementation 'com.safframework.bytekit:bytekit-mmap:1.2.6'
```

详见：https://www.jianshu.com/p/411462b481b7


联系方式
===

Wechat：fengzhizi715


> Java与Android技术栈：每周更新推送原创技术文章，欢迎扫描下方的公众号二维码并关注，期待与您的共同成长和进步。

![](https://user-gold-cdn.xitu.io/2018/7/24/164cc729c7c69ac1?w=344&h=344&f=jpeg&s=9082)

License
-------

    Copyright (C) 2017 - present, Tony Shen.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.