var XX = 10; // 浮动层的X坐标,即左边距
var xstep = 1; // 移动步长，此参数越小，移动越平滑，最小值为1
var delay_time = 60; // 每步的时间间隔，此参数越小，移动速度越快
//以下部分请勿随意改动
var YY = 0;
var ch = 0;
var oh = 0;
var yon = 0; //设定y轴方向
var ns4 = document.layers ? 1 : 0; //判断浏览器类型是否是NS4
var ie = document.all ? 1 : 0; //判断浏览器类型是否是IE
var ns6 = document.getElementById && !document.all ? 1 : 0; //判断浏览器类型是否是NS6
if (ie) { //如果是IE
    YY = document.body.clientHeight; //由clientHeight取得页面的高度
    floatpoint.style.top = YY; //将浮动层位置调整到页面底部
} else if (ns4) { //如果是NS4
    YY = window.innerHeight; //由innerHeight取得页面的高度
    document.floatpoint.pageY = YY; //将浮动层位置调整到页面底部
    document.floatpoint.visibility = "hidden"; //将浮动层隐藏。
} else if (ns6) { //如果是NS6
    YY = window.innerHeight; //由innerHeight取得页面的高度
    /*document.getElementById('floatpoint').style.top=YY;*/ //将浮动层位置调整到页面底部
}

function reloc1() {
    if (yon == 0) {
        YY = YY - xstep;
    } //如果当前应该上移，则减小YY值
    else {
        YY = YY + xstep;
    } //否则增加YY值下移
    if (ie) { //如果是IE
        ch = document.body.clientHeight; //取页面高度
        oh = floatpoint.offsetHeight; //取浮动层的高度
    } else if (ns4) { //如果是NS4
        ch = window.innerHeight; //取页面高度
        oh = document.floatpoint.clip.height; //取浮动层的高度
    } else if (ns6) { //如果是NS6
        ch = window.innerHeight //取页面高度
        oh = document.getElementById("floatpoint").offsetHeight; //取浮动层的高度
    }
    //if(YY<0){yon=1;YY=0;} //如果浮动层超出了上界，则设定移动方向为向下；并设定层的位置为正好在上界处
    if (YY < 122) {
        yon = 1;
        YY = 122;
    }
    if (YY >= (ch - oh)) {
        yon = 0;
        YY = (ch - oh);
    } //如果浮动层超出了下界，则设定移动方向为向上；并设定层的位置为正好在下界处
    if (ie) { //如果是IE
        floatpoint.style.right = XX; //用style.left设定浮动层左边距   floatpoint.style.left=XX;
        floatpoint.style.top = YY + document.body.scrollTop; //用style.top设定浮动层上边距
    } else if (ns4) { //如果是NS4
        document.floatpoint.pageX = XX; //用.pageX设定浮动层左边距   document.floatpoint.pageX=XX;
        document.floatpoint.pageY = YY + window.pageYOffset; //用.pageY设定浮动层上边距
    } else if (ns6) { //如果是NS6
        document.getElementById("floatpoint").style.right = XX;  //document.getElementById("floatpoint").style.left=XX
        document.getElementById("floatpoint").style.top = YY + window.pageYOffset;
    }
}

function onad() {
    if (ns4) //如果是NS4
        document.floatpoint.visibility = "visible"; //设定浮动层为可见
    loopfunc(); //开始主循环，以不断改变浮动层位置
}

function loopfunc() {
    reloc1(); //调整浮动层位置
    setTimeout('loopfunc()', delay_time); //设定下一次调整的延时
}

if (ie || ns4 || ns6)
    window.onload = onad; //初始化事件触发器

//带有关闭窗口的小窗口
/* var bodyfrm = ( document.compatMode.toLowerCase()=="css1compat" ) ? document.documentElement : document.body;
 var adst = document.getElementById("floatpoint").style;
 adst.top = ( bodyfrm.clientHeight -530-22 ) + "px";
 adst.left = ( bodyfrm.clientWidth -155 ) + "px";
 function moveR() {
 adst.top = ( bodyfrm.scrollTop + bodyfrm.clientHeight - 530-22) + "px";
 adst.left = ( bodyfrm.scrollLeft + bodyfrm.clientWidth - 155 ) + "px";
 }
 setInterval("moveR();", 80);
 function closead()
 {
    adst.display='none';
 } */
        