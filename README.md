# Json_image_parse
In this project, <br>How to parse Image in listView from json ?<br>

For Api, i am using Free Api provide
<br> http://www.mocky.io/<br>

with refrence of above website, i create below REST api<br>

http://www.mocky.io/v2/56ea55dc10000007036c18f2

in which you can create your own REST api.

For images i am using, below website : <br>
http://wallpaperhd.in/

For image downloading i am using Picasso,<br>

https://github.com/square/picasso

In your gradle you should add, below code<br>
<code>
compile 'com.squareup.picasso:picasso:2.5.2'
</code>
<br><br><br>
![device-2016-03-17-190300](https://cloud.githubusercontent.com/assets/17099115/13847677/3ffe263a-ec74-11e5-9390-3413075fe33d.png)
<br>
Very Important, For http connection you should add below code in your gradle file<br>
<code>
    useLibrary 'org.apache.http.legacy'
</code>

